import { Component } from '@angular/core';
import { Sesion } from './modelo/sesion';
import { SesionService } from './servicios/sesion.service';
import { StorageService } from './servicios/storage.service';
import { Mensajes } from './utils/mensajes.utils';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Demo Banco';
  sesion: Sesion = new Sesion;



  constructor(private storageService: StorageService, private sesionService: SesionService) { }

  ngOnInit(): void {
    this.sesion = this.storageService.getSesion();
  }

  onCerrarSesion(): void {
    Mensajes.loading();
    this.sesionService.cerrarSesion().subscribe(data => {
      this.storageService.borrarSesion();
    });

  }
}
