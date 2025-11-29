import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute,RouterLink } from '@angular/router';
import { AuthService } from '../service/auth.service'; // Asegúrate de importar el nuevo servicio

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule,RouterLink],
  templateUrl: './login.html', // Ojo: Tendrás que editar tu HTML también
  styleUrls: ['./login.css'],
})
export class Login {

  form: FormGroup;
  msg: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService, // Inyectamos el servicio real
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.form = this.fb.group({
      // CAMBIO IMPORTANTE: El backend pide 'email', no 'username'
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(3)]],
    });

    this.route.queryParamMap.subscribe((map) => {
      this.msg = map.get('msg');
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    // Enviamos los datos al backend
    this.authService.login(this.form.value).subscribe({
      next: (usuario) => {
        console.log('Login exitoso:', usuario);
        localStorage.setItem('usuarioSesion', JSON.stringify(usuario));
        this.router.navigate(['/home']);
      },
      error: (error) => {
        console.error('Error login:', error);
        this.msg = 'Usuario o contraseña incorrectos';
      }
    });
  }

  // Getters actualizados
  get emailCtrl() {
    return this.form.get('email');
  }
  get passwordCtrl() {
    return this.form.get('password');
  }
}
