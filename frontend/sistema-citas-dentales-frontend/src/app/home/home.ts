import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule], // No necesitamos RouterLink aquí porque usamos código
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home {
   user: any = null;

  constructor(private router: Router) {
    const usuarioGuardado = localStorage.getItem('usuarioSesion');
    if (usuarioGuardado) {
      this.user = JSON.parse(usuarioGuardado);
    } else {
      this.router.navigate(['/login']);
    }
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
