import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CitaService } from '../service/cita.service/cita.service';
import { MedicoService } from '../service/medico.service';
import { ServicioService } from '../service/servicio.service';

@Component({
  selector: 'app-citas',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './citas.html',
  styleUrl: './citas.css',
})
export class Citas implements OnInit {

  form: FormGroup;
  servicios: any[] = [];
  medicos: any[] = [];
  medicosFiltrados: any[] = [];
  horariosDisponibles: string[] = [];
  
  msg: string | null = null;
  msgSuccess: string | null = null;
  cargando = false;
  verificandoDisponibilidad = false;
  
  usuario: any = null;

  // Horarios disponibles comunes (puedes ajustar según tu backend)
  horariosBase = [
    '08:00', '08:30', '09:00', '09:30', '10:00', '10:30',
    '11:00', '11:30', '14:00', '14:30', '15:00', '15:30',
    '16:00', '16:30', '17:00', '17:30', '18:00', '18:30'
  ];

  constructor(
    private fb: FormBuilder,
    private citaService: CitaService,
    private medicoService: MedicoService,
    private servicioService: ServicioService,
    public router: Router
  ) {
    this.form = this.fb.group({
      id_servicio: ['', Validators.required],
      id_medico: ['', Validators.required],
      fech_cita: ['', [Validators.required, this.fechaFuturaValidator]],
      hora_cita: ['', Validators.required],
      notas: ['']
    });
  }

  ngOnInit(): void {
    // Obtener usuario del localStorage
    const usuarioData = localStorage.getItem('usuario');
    if (!usuarioData) {
      this.router.navigate(['/login']);
      return;
    }
    this.usuario = JSON.parse(usuarioData);

    // Cargar servicios y médicos
    this.cargarServicios();
    this.cargarMedicos();

    // Escuchar cambios en el servicio para filtrar médicos
    this.form.get('id_servicio')?.valueChanges.subscribe(servicioId => {
      if (servicioId) {
        this.filtrarMedicosPorServicio(servicioId);
        // Resetear médico y hora cuando cambia el servicio
        this.form.patchValue({ id_medico: '', hora_cita: '' });
        this.horariosDisponibles = [];
      }
    });

    // Escuchar cambios en médico y fecha para verificar disponibilidad
    this.form.get('id_medico')?.valueChanges.subscribe(() => {
      this.verificarHorariosDisponibles();
    });

    this.form.get('fech_cita')?.valueChanges.subscribe(() => {
      this.verificarHorariosDisponibles();
    });
  }

  cargarServicios(): void {
    this.servicioService.obtenerServicios().subscribe({
      next: (data) => {
        this.servicios = Array.isArray(data) ? data : [];
      },
      error: (err) => {
        console.error('Error al cargar servicios:', err);
        this.msg = 'Error al cargar los servicios disponibles';
      }
    });
  }

  cargarMedicos(): void {
    this.medicoService.obtenerMedicos().subscribe({
      next: (data) => {
        this.medicos = Array.isArray(data) ? data : [];
        this.medicosFiltrados = [...this.medicos];
      },
      error: (err) => {
        console.error('Error al cargar médicos:', err);
        this.msg = 'Error al cargar los médicos disponibles';
      }
    });
  }

  filtrarMedicosPorServicio(idServicio: number): void {
    if (!idServicio) {
      this.medicosFiltrados = [...this.medicos];
      return;
    }

    this.medicoService.obtenerMedicosPorServicio(idServicio).subscribe({
      next: (data) => {
        this.medicosFiltrados = Array.isArray(data) ? data : [];
        if (this.medicosFiltrados.length === 0) {
          this.msg = 'No hay médicos disponibles para este servicio';
        }
      },
      error: (err) => {
        console.error('Error al filtrar médicos:', err);
        // Si falla, usar todos los médicos
        this.medicosFiltrados = [...this.medicos];
      }
    });
  }

  verificarHorariosDisponibles(): void {
    const idMedico = this.form.get('id_medico')?.value;
    const fecha = this.form.get('fech_cita')?.value;

    if (!idMedico || !fecha) {
      this.horariosDisponibles = [];
      return;
    }

    this.verificandoDisponibilidad = true;
    this.citaService.obtenerHorariosDisponibles(idMedico, fecha).subscribe({
      next: (data) => {
        // Si el backend devuelve horarios disponibles, usarlos
        if (Array.isArray(data) && data.length > 0) {
          this.horariosDisponibles = data;
        } else {
          // Si no hay respuesta del backend, usar horarios base
          this.horariosDisponibles = [...this.horariosBase];
        }
        this.verificandoDisponibilidad = false;
      },
      error: (err) => {
        console.error('Error al verificar horarios:', err);
        // En caso de error, usar horarios base
        this.horariosDisponibles = [...this.horariosBase];
        this.verificandoDisponibilidad = false;
      }
    });
  }

  verificarDisponibilidadAntesDeEnviar(): void {
    const idMedico = this.form.get('id_medico')?.value;
    const fecha = this.form.get('fech_cita')?.value;
    const hora = this.form.get('hora_cita')?.value;

    if (!idMedico || !fecha || !hora) {
      return;
    }

    this.verificandoDisponibilidad = true;
    this.citaService.verificarDisponibilidad(idMedico, fecha, hora).subscribe({
      next: (data) => {
        this.verificandoDisponibilidad = false;
        // Si el backend confirma disponibilidad, proceder con el envío
        if (data.disponible !== false) {
          this.onSubmit();
        } else {
          this.msg = 'El horario seleccionado ya no está disponible. Por favor, selecciona otro horario.';
          this.form.get('hora_cita')?.setValue('');
        }
      },
      error: (err) => {
        this.verificandoDisponibilidad = false;
        // Si hay error, intentar enviar de todas formas (el backend validará)
        console.warn('No se pudo verificar disponibilidad, intentando crear cita:', err);
        this.onSubmit();
      }
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.msg = 'Por favor, completa todos los campos requeridos';
      return;
    }

    this.cargando = true;
    this.msg = null;
    this.msgSuccess = null;

    const formValue = this.form.value;
    const citaData = {
      id_usuario: this.usuario.id,
      id_medico: formValue.id_medico,
      id_servicio: formValue.id_servicio,
      fech_cita: formValue.fech_cita,
      hora_cita: formValue.hora_cita,
      notas: formValue.notas || '',
      estado: 'PENDIENTE' // Estado inicial
    };

    this.citaService.crearCita(citaData).subscribe({
      next: (res) => {
        console.log('Cita creada exitosamente:', res);
        this.msgSuccess = '¡Cita reservada exitosamente!';
        this.cargando = false;
        this.form.reset();
        this.horariosDisponibles = [];
        
        // Redirigir al home después de 2 segundos
        setTimeout(() => {
          this.router.navigate(['/home']);
        }, 2000);
      },
      error: (err) => {
        console.error('Error al crear cita:', err);
        this.cargando = false;
        
        if (err.status === 409 || err.status === 400) {
          this.msg = err.error?.message || 'El horario seleccionado ya está ocupado. Por favor, selecciona otro horario.';
        } else if (err.status === 0) {
          this.msg = 'Error de conexión. Verifica que el backend esté ejecutándose.';
        } else {
          this.msg = err.error?.message || 'Error al reservar la cita. Intenta nuevamente.';
        }
      }
    });
  }

  // Validador personalizado para fechas futuras
  fechaFuturaValidator(control: any) {
    if (!control.value) {
      return null;
    }
    const fechaSeleccionada = new Date(control.value);
    const hoy = new Date();
    hoy.setHours(0, 0, 0, 0);
    
    if (fechaSeleccionada < hoy) {
      return { fechaPasada: true };
    }
    return null;
  }

  // Getters para acceder a los controles del formulario
  get idServicioCtrl() {
    return this.form.get('id_servicio');
  }

  get idMedicoCtrl() {
    return this.form.get('id_medico');
  }

  get fechCitaCtrl() {
    return this.form.get('fech_cita');
  }

  get horaCitaCtrl() {
    return this.form.get('hora_cita');
  }

  get notasCtrl() {
    return this.form.get('notas');
  }

  // Obtener la fecha mínima (hoy) para el input de fecha
  getMinDate(): string {
    const hoy = new Date();
    return hoy.toISOString().split('T')[0];
  }
}
