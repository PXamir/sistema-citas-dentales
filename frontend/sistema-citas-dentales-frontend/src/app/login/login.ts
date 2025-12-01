import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute,RouterLink } from '@angular/router';
import { AuthService } from '../service/auth.service'; // Asegúrate de importar el nuevo servicio

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule,RouterLink],
  templateUrl: './login.html',
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
  
    this.authService.login(this.form.value).subscribe({
      next: (res) => {
        console.log("Login OK:", res);
  
        // Guardamos el token si existe, sino un valor por defecto
        const token = res.token || res.accessToken || 'authenticated';
        localStorage.setItem('token', token);
        
        // La respuesta del backend es directamente el objeto del usuario
        // Guardamos en ambas claves para compatibilidad
        localStorage.setItem('usuarioSesion', JSON.stringify(res));
        localStorage.setItem('usuario', JSON.stringify(res)); // Home busca 'usuario'
        console.log('Usuario guardado en localStorage:', res);
  
        // Redirigir al home
        console.log('Intentando navegar a /home...');
        this.router.navigate(['/home']).then(
          (success) => {
            console.log('Resultado de navegación:', success);
            if (!success) {
              console.error('Error al navegar a /home - la navegación falló');
              this.msg = "Error al redirigir. Intenta nuevamente.";
            } else {
              console.log('Navegación exitosa a /home');
            }
          }
        ).catch((error) => {
          console.error('Error en la navegación:', error);
          this.msg = "Error al redirigir. Intenta nuevamente.";
        });
      },
      error: (err) => {
        console.error("Error en login:", err);
        if (err.status === 401 || err.status === 403) {
          this.msg = "Usuario o contraseña incorrectos";
        } else if (err.status === 0) {
          this.msg = "Error de conexión. Verifica que el backend esté ejecutándose.";
        } else {
          this.msg = "Error al iniciar sesión. Intenta nuevamente.";
        }
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
