import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    // 1. Preguntamos: ¿Existe 'usuarioSesion' en la memoria?
    const usuario = localStorage.getItem('usuarioSesion');

    if (usuario) {
      // Si existe, ¡Pase adelante!
      return true;
    } else {
      // Si no existe, lo mandamos al Login
      this.router.navigate(['/login']);
      return false;
    }
  }
}
