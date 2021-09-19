import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Credenciales } from '../modelo/credenciales';
import { Resultado } from '../modelo/resultado';
import { Sesion } from '../modelo/sesion';
import { Usuario } from '../modelo/usuario';

@Injectable({
  providedIn: 'root'
})
export class SesionService {
  url: string;

  constructor(private http: HttpClient) {
    this.url = 'http://localhost:8080/sesiones';

  }

  public login(usuario: string, password: string): Observable<Resultado<Sesion>> {
    let credenciales: Credenciales = new Credenciales(usuario, password);
    return this.http.post<Resultado<Sesion>>(this.url, credenciales);
  }

}
