import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Sesion } from "../modelo/sesion";



@Injectable({
  providedIn: 'root'
})
export class StorageService {

  private sesionActiva: Boolean = false;
  private sesion!: Sesion;

  constructor(private router: Router) {    

  }

  setSesion(sesion: Sesion): void {
    sessionStorage.setItem('currentUser', JSON.stringify(sesion));
  }

  borrarSesion(): void {
    sessionStorage.removeItem('currentUser');
    window.location.replace("/misTransacciones");
  }

  getSesion(): Sesion {   
    this.loadSessionData(); 
    return this.sesion;
  }

  loadSessionData() {
    var sessionStr = sessionStorage.getItem('currentUser');
    if (sessionStr) {
      try {
        this.sesion = JSON.parse(sessionStorage.getItem('currentUser')!) as Sesion;
        this.sesionActiva = true;        
      } catch (error) {
        this.sesionActiva = false;
        this.borrarSesion();
        this.router.navigate(['/', 'login']);
      }
    } else {
      this.sesionActiva = false;
      this.router.navigate(['/', 'login']);
    }
  };





}

