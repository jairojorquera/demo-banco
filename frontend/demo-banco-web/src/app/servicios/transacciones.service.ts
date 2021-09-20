import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Transaccion } from '../modelo/transaccion';
import { Resultado } from '../modelo/resultado';
import { Observable } from 'rxjs';
import { URL_TRANSACCIONES } from '../utils/app.constantes';


@Injectable({
  providedIn: 'root'
})
export class TransaccionesService {

  constructor(private http: HttpClient) {

  }

  public findAll(): Observable<Transaccion[]> {
    return this.http.get<Transaccion[]>(URL_TRANSACCIONES);
  }

  public find(rut: string): Observable<Transaccion[]> {
    let url = URL_TRANSACCIONES + "/" + rut;
    return this.http.get<Transaccion[]>(url);
  }

  public save(transaccion: Transaccion) {
    return this.http.post<Resultado<Transaccion>>(URL_TRANSACCIONES, transaccion);
  }
}
