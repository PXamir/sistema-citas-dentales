import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root' // Esto hace que el servicio esté disponible en toda la app
})
export class AuthService {

  // URL de tu Backend Spring Boot
  private apiUrl = 'http://localhost:8085/auth';

  constructor(private http: HttpClient) { }


  // Método para Login
  login(credenciales: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credenciales);
  }

  // Método para Registro
  register(datos: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, datos);
  }
}
