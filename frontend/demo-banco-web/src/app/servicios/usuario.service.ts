import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Usuario } from '../modelo/usuario';
import { Observable } from 'rxjs';
import { Resultado } from '../modelo/resultado';
import { URL_USUARIOS } from '../utils/app.constantes';


@Injectable({ providedIn: 'root' })
export class UsuarioService {

  constructor(private http: HttpClient) {
  
  }

  public save(user: Usuario): Observable<Resultado<Usuario>> {
    return this.http.post<Resultado<Usuario>>(URL_USUARIOS, user);
  }

 

}
