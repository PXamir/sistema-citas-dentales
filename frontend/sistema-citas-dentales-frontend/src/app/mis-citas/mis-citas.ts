import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { Router } from '@angular/router';
import { CitasService } from '../service/cita.service/cita.service';

@Component({
  selector: 'app-mis-citas',
  standalone: true,
  imports: [CommonModule, DatePipe],
  templateUrl: './mis-citas.html',
  styleUrls: ['./mis-citas.css']
})
export class MisCitas implements OnInit {
  citas: any[] = [];
  loading: boolean = true;

  constructor(
    private citasService: CitasService,
    private router: Router
  ) {}

  ngOnInit() {
    // 1. Obtener usuario de sesión
    const sesion = localStorage.getItem('usuarioSesion');
    if (!sesion) {
      this.router.navigate(['/login']);
      return;
    }
    const user = JSON.parse(sesion);

    // 2. Pedir citas al backend
    this.citasService.getCitasPorUsuario(user.id).subscribe({
      next: (data) => {
        this.citas = data;
        this.loading = false;

        // Opcional: Ordenar para que la más reciente salga primero
        this.citas.sort((a, b) => new Date(b.fechaCita).getTime() - new Date(a.fechaCita).getTime());
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
      }
    });
  }

  // Función para dar color al estado
  getClassEstado(estado: string): string {
    switch (estado) {
      case 'CONFIRMADA': return 'badge-blue';
      case 'PENDIENTE': return 'badge-orange';
      case 'FINALIZADA': return 'badge-green';
      case 'CANCELADA': return 'badge-red';
      default: return 'badge-gray';
    }
  }

  volver() {
    this.router.navigate(['/home']);
  }
}