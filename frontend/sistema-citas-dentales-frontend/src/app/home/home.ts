import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './home.html',
  styleUrls: ['./home.css'],
})
export class Home {

  email: string | null = null;
  roles: string[] = [];

  esPaciente = false;
  esAdmin = false;

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    // 1. Leer token
    const token = localStorage.getItem('token');
    if (!token) {
      this.router.navigate(['/login']);
      return;
    }

    // 2. Leer email (opcional)
    this.email = localStorage.getItem('email');

    // 3. Leer roles
    this.roles = this.authService.getRoles();

    // 4. Determinar qué mostrar
    this.esPaciente = this.roles.includes('PACIENTE');
    this.esAdmin = this.roles.includes('ADMIN');
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
