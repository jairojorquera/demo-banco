import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Transaccion } from '../modelo/transaccion';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TransaccionesService {

  private transaccionUrl: string;

  constructor(private http: HttpClient) {
    this.transaccionUrl = 'http://localhost:8080/transacciones';
  }

  public findAll(): Observable<Transaccion[]> {
    return this.http.get<Transaccion[]>(this.transaccionUrl);
  }

  public save(transaccion: Transaccion) {
    return this.http.post<Transaccion>(this.transaccionUrl, transaccion);
  }
}
