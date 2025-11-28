import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CitaService {
  private apiUrl = 'http://localhost:8085/'; // Tu base URL

  constructor(private http: HttpClient) { }

  // 1. Obtener lista de médicos
  getMedicos(): Observable<any[]> {
    return this.http.get<any[]>(${this.apiUrl}/medicos);
  }

  // 2. Obtener lista de servicios dentales
  getServicios(): Observable<any[]> {
    return this.http.get<any[]>(${this.apiUrl}/servicios);
  }

  // 3. Enviar la cita al backend
  registrarCita(cita: any): Observable<any> {
    return this.http.post(${this.apiUrl}/citas, cita);
  }
}