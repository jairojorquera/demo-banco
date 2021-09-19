import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../modelo/usuario';
import { Observable } from 'rxjs';
import { Resultado } from '../modelo/resultado';


@Injectable({ providedIn: 'root' })
export class UsuarioService {

  private usuarioUrl: string;

  constructor(private http: HttpClient) {
    this.usuarioUrl = 'http://localhost:8080/usuarios';

  }

  public save(user: Usuario): Observable<Resultado<Usuario>> {
    return this.http.post<Resultado<Usuario>>(this.usuarioUrl, user);
  }

 

}
