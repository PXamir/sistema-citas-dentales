import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServicioService {

  private apiUrl = 'http://localhost:8085/api/servicios';

  constructor(private http: HttpClient) { }

  // Obtener todos los servicios disponibles
  obtenerServicios(): Observable<any> {
    return this.http.get(`${this.apiUrl}/todos`);
  }

  // Obtener un servicio por ID
  obtenerServicioPorId(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }
}

