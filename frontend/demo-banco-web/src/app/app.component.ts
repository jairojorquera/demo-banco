import { Component } from '@angular/core';
import { Sesion } from './modelo/sesion';
import { StorageService } from './servicios/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Demo Banco';
  sesion: Sesion = new Sesion;



  constructor(private storageService: StorageService) { }

  ngOnInit(): void {
    this.sesion = this.storageService.getSesion();
  }

  onCerrarSesion(): void {
    this.storageService.borrarSesion();

  }
}
