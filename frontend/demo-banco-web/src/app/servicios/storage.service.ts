import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Sesion } from "../modelo/sesion";
import { Usuario } from "../modelo/usuario";


@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private sesionActiva: Boolean = false;
  private sesion!: Sesion;

  constructor(private router: Router) {
    this.loadSessionData();

  }

  setSesion(sesion: Sesion): void {
    localStorage.setItem('currentUser', JSON.stringify(sesion));
  }

  borrarSesion(): void {
    localStorage.removeItem('currentUser');
  }

  getSesion(): Sesion {
    return this.sesion;
  }

  loadSessionData() {
    var sessionStr = localStorage.getItem('currentUser');
    if (sessionStr) {
      try {
        this.sesion = JSON.parse(localStorage.getItem('currentUser')!) as Sesion;
        this.sesionActiva = true;
      } catch (error) {
        this.sesionActiva = false;
        this.borrarSesion();
      }
    } else {
      this.sesionActiva = false;
    }
  };





}

