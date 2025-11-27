import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home {
   // Cambiamos a 'any' para poder acceder a .nombre, .email, etc.
   user: any = null;

  constructor(private router: Router) {
    // 1. Intentamos recuperar al usuario de la memoria del navegador
    const usuarioGuardado = localStorage.getItem('usuarioSesion');

    if (usuarioGuardado) {
      // Convertimos el texto JSON a un objeto real
      this.user = JSON.parse(usuarioGuardado);
    } else {
      // Si no hay nadie logueado, lo devolvemos al login
      this.router.navigate(['/login']);
    }
  }

  logout() {
    // 2. Borramos la sesión de la memoria
    localStorage.removeItem('usuarioSesion');
    this.router.navigate(['/login']);
  }
}
