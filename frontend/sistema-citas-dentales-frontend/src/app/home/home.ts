import { Component } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import{ CitasService } from '../service/cita.service/cita.service';

import { NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule,DatePipe], // No necesitamos RouterLink aquí porque usamos código
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home {
   user: any = null;
   totalCitas: number = 0;
  citasCompletadas: number = 0;
  proximaCita: any = null;

  constructor(private router: Router, private citaService: CitasService) {
    const usuarioGuardado = localStorage.getItem('usuarioSesion');
    if (usuarioGuardado) {
      this.user = JSON.parse(usuarioGuardado);
    } else {
      this.router.navigate(['/login']);
    }
    this.router.events.pipe(
    filter(event => event instanceof NavigationEnd)
  ).subscribe(() => {
    if (this.user) {
      this.cargarDatosDashboard();
    }
  });
  }
  

  cargarDatosDashboard() {
    console.log('🔄 Cargando datos para usuario ID:', this.user.id);

    this.citaService.getCitasPorUsuario(this.user.id).subscribe({
      next: (citas) => {
        console.log('📦 Citas recibidas del backend:', citas); // <--- MIRA ESTO EN CONSOLA
        
        // 1. Total de Citas
        this.totalCitas = citas.length;

        // 2. Citas Completadas
        this.citasCompletadas = citas.filter(c => c.estado === 'FINALIZADA').length;

        // 3. Calcular Próxima Cita (CON CORRECCIÓN DE FECHA)
        const ahora = new Date();
        // Ponemos la hora en 0 para comparar solo fechas sin que afecte la hora actual
        ahora.setHours(0, 0, 0, 0); 

        const citasPendientes = citas.filter(c => {
            // TRUCO: Agregamos 'T00:00:00' para que JS no reste un día por la zona horaria
            // Si c.fechaCita viene como "2025-11-28", lo forzamos a local
            const fechaString = c.fechaCita.toString().split('T')[0]; // Aseguramos formato YYYY-MM-DD
            const fechaCita = new Date(fechaString + 'T00:00:00'); // Forzamos media noche local

            return (c.estado === 'PENDIENTE' || c.estado === 'CONFIRMADA') && 
                   fechaCita >= ahora;
        });

        console.log('📅 Citas pendientes futuras encontradas:', citasPendientes);

        // Ordenamos por fecha
        citasPendientes.sort((a, b) => {
            const fechaA = new Date(a.fechaCita + 'T' + (a.horaCita || '00:00'));
            const fechaB = new Date(b.fechaCita + 'T' + (b.horaCita || '00:00'));
            return fechaA.getTime() - fechaB.getTime();
        });

        if (citasPendientes.length > 0) {
            this.proximaCita = citasPendientes[0];
            console.log('🏆 Próxima cita ganadora:', this.proximaCita);
        } else {
            this.proximaCita = null;
        }
      },
      error: (err) => console.error('Error cargando citas', err)
    });
  }
  // Función para ir a Mis Citas
  irMisCitas() {
    this.router.navigate(['/mis-citas']);
  }
  // Función para cerrar sesión
  logout() {
    localStorage.removeItem('usuarioSesion');
    this.router.navigate(['/login']);
  }

  // Función para ir a reservar (Nuevo botón azul)
  irAgendar() {
      // Navegamos a la ruta 'agendar' que definimos antes
      this.router.navigate(['/agendar']);
  }
}
