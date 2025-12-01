import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedicoService {

  private apiUrl = 'http://localhost:8085/api/medicos';

  constructor(private http: HttpClient) { }

  // Obtener todos los médicos disponibles
  obtenerMedicos(): Observable<any> {
    return this.http.get(`${this.apiUrl}/todos`);
  }

  // Obtener un médico por ID
  obtenerMedicoPorId(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  // Obtener médicos por especialidad o servicio
  obtenerMedicosPorServicio(idServicio: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/servicio/${idServicio}`);
  }
}

