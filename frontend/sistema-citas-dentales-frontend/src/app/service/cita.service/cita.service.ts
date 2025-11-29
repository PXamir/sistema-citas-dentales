import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CitasService {
  // Tu base URL. Asegúrate de que esta URL sea correcta.
  private apiUrl = 'http://localhost:8085/';

  constructor(private http: HttpClient) { }

  // 1. Obtener lista de médicos
  getMedicos(): Observable<any> {
    return this.http.get(this.apiUrl + 'medicos');
  }

  // 2. Obtener lista de servicios dentales
  getServicios(): Observable<any> {
    return this.http.get(this.apiUrl + 'servicios');
  }

  // 3. Enviar la cita al backend
  // NOTA: 'cita' debería tener un tipo de interfaz definido para mejor práctica.
  registrarCita(cita: any): Observable<any> {
    // La URL de tu endpoint para registrar citas
    return this.http.post(this.apiUrl + 'citas', cita);
  }
  getCitasPorUsuario(idUsuario: number): Observable<any[]> {
    // CORRECCIÓN: Se reemplazan los paréntesis/comillas por backticks (`` ` ``)
    return this.http.get<any[]>(`${this.apiUrl}citas/usuario/${idUsuario}`); 
  }
}