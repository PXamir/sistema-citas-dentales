import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home implements OnInit {

  usuario: any = null;

  esPaciente = false;
  esAdmin = false;
  esMedico = false;

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Intentar leer usuario guardado
    const data = localStorage.getItem('usuario');

    if (!data) {
      this.router.navigate(['/login']);
      return;
    }

    this.usuario = JSON.parse(data);

    // Determinar rol
    const rol = this.usuario.rol;

    this.esPaciente = rol === 'PACIENTE';
    this.esAdmin   = rol === 'ADMIN';
    this.esMedico  = rol === 'MEDICO';
  }

  logout() {
    localStorage.removeItem('usuario');
    localStorage.removeItem('usuarioSesion');
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
