import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CitaService {

  private apiUrl = 'http://localhost:8085/api/citas';

  constructor(private http: HttpClient) { }

  // Crear una nueva cita
  crearCita(cita: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/crear`, cita);
  }

  // Obtener todas las citas de un usuario
  obtenerCitasPorUsuario(idUsuario: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/usuario/${idUsuario}`);
  }

  // Obtener todas las citas
  obtenerTodasLasCitas(): Observable<any> {
    return this.http.get(`${this.apiUrl}/todas`);
  }

  // Verificar disponibilidad de horario para un médico
  verificarDisponibilidad(idMedico: number, fecha: string, hora: string): Observable<any> {
    const params = new HttpParams()
      .set('idMedico', idMedico.toString())
      .set('fecha', fecha)
      .set('hora', hora);
    
    return this.http.get(`${this.apiUrl}/disponibilidad`, { params });
  }

  // Obtener horarios disponibles de un médico en una fecha específica
  obtenerHorariosDisponibles(idMedico: number, fecha: string): Observable<any> {
    const params = new HttpParams()
      .set('idMedico', idMedico.toString())
      .set('fecha', fecha);
    
    return this.http.get(`${this.apiUrl}/horarios-disponibles`, { params });
  }

  // Actualizar una cita
  actualizarCita(idCita: number, cita: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${idCita}`, cita);
  }

  // Cancelar una cita
  cancelarCita(idCita: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${idCita}`);
  }
}

