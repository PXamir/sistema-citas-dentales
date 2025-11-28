import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, RouterLink } from '@angular/router'; // Importamos RouterLink para el botón "Volver"
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink], // Importante: RouterLink aquí
  templateUrl: './register.html',
  styleUrls: ['./register.css'],
})
export class Register {

  form: FormGroup;
  msg: string | null = null;
  exito: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    // Los nombres de los campos DEBEN coincidir con tu RegistroRequest de Java
    this.form = this.fb.group({
      nombre: ['', [Validators.required]],
      apellido: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(3)]],
      telefono: ['', [Validators.required]],
      fechaNacimiento: ['', [Validators.required]], // Input tipo date
      genero: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.form.invalid) return;

    this.authService.register(this.form.value).subscribe({
      next: (usuario: any) => {
        console.log('Registro exitoso:', usuario);
        this.exito = true;
        this.msg = '¡Cuenta creada con éxito! Redirigiendo al login...';

        // Esperamos 2 segundos y lo mandamos al login
        setTimeout(() => {
            this.router.navigate(['/login']);
        }, 2000);
      },
      error: (error: any) => {
        console.error('Error registro:', error);
        // Intentamos mostrar el mensaje que viene del backend o uno genérico
        this.msg = error.error || 'Error al registrar el usuario. Intenta con otro correo.';
        this.exito = false;
      }
    });
  }
}
