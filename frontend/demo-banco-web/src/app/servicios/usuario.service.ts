import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../modelo/usuario';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class UsuarioService {

  private usuarioUrl: string;

  constructor(private http: HttpClient) {
    this.usuarioUrl = 'http://localhost:8080/usuarios';

  }

  public findAll(): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(this.usuarioUrl);
  }

  public save(user: Usuario) {
    return this.http.post<Usuario>(this.usuarioUrl, user);
  }
}