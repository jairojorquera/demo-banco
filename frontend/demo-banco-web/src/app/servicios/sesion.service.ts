import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Credenciales } from '../modelo/credenciales';
import { Resultado } from '../modelo/resultado';
import { Sesion } from '../modelo/sesion';
import { URL_SESIONES } from '../utils/app.constantes';
import { StorageService } from './storage.service';


@Injectable({
  providedIn: 'root'
})
export class SesionService {

  constructor(private http: HttpClient, private storageService: StorageService) {
  }

  public login(usuario: string, password: string): Observable<Resultado<Sesion>> {
    let credenciales: Credenciales = new Credenciales(usuario, password);
    return this.http.post<Resultado<Sesion>>(URL_SESIONES, credenciales);
  }

  public cerrarSesion() {
    let sesion: Sesion = this.storageService.getSesion();
    return this.http.delete(URL_SESIONES + "/" + sesion.usuario.rut + "/" + sesion.token);
  }


}
