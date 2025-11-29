import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CitasService } from '../service/cita.service/cita.service';

@Component({
  selector: 'app-agendar-cita',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './agendar-cita.html',
  styleUrls: ['./agendar-cita.css']
})
export class AgendarCita implements OnInit {
  form: FormGroup;
  medicos: any[] = [];
  servicios: any[] = [];
  usuarioId: number | null = null;
  msg: string | null = null;

  constructor(
    private fb: FormBuilder,
    private citasService: CitasService,
    private router: Router
  ) {
    this.form = this.fb.group({
      fecha: ['', Validators.required],
      hora: ['', Validators.required],
      idMedico: ['', Validators.required],  // Enviaremos el ID
      idServicio: ['', Validators.required], // Enviaremos el ID
      notas: ['']
    });
  }

  ngOnInit() {
    // 1. Obtener el ID del usuario logueado
    const sesion = localStorage.getItem('usuarioSesion');
    if (sesion) {
      const user = JSON.parse(sesion);
      this.usuarioId = user.id; // IMPORTANTE: Tu JSON debe tener la propiedad .id
    } else {
      this.router.navigate(['/login']);
    }

    // 2. Cargar listas desde el backend
    this.cargarListas();
  }

  cargarListas() {
    this.citasService.getMedicos().subscribe({
      next: (data) => this.medicos = data,
      error: () => console.warn('Error cargando médicos. ¿El backend está encendido?')
    });

    this.citasService.getServicios().subscribe({
      next: (data) => this.servicios = data,
      error: () => console.warn('Error cargando servicios.')
    });
  }

  onSubmit() {
    if (this.form.invalid || !this.usuarioId) return;

    // Armamos el objeto final
    const citaNueva = {
      ...this.form.value,
      idUsuario: this.usuarioId
    };

    this.citasService.registrarCita(citaNueva).subscribe({
      next: () => {
        alert('✅ ¡Cita reservada con éxito!');
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error(err);
        this.msg = 'Error al agendar. Verifica los datos.';
      }
    });
  }

  volver() {
    this.router.navigate(['/home']);
  }
}