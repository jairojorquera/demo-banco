import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';

import { Sesion } from '../../modelo/sesion';
import { UsuarioService } from '../../servicios/usuario.service';
import { StorageService } from '../../servicios/storage.service';
import { SesionService } from '../../servicios/sesion.service';
import { Mensajes } from '../../utils/mensajes.utils';

import Swal from 'sweetalert2';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario = "";
  password = "";
  sesion!: Sesion;


  constructor(private userService: UsuarioService, private storageService: StorageService, private sesionService: SesionService,
    private router: Router) { }

  ngOnInit(): void {

  }

  onLogin(event?: MouseEvent) {


    this.sesionService.login(this.usuario, this.password)
      .subscribe(resultado => {

        if (resultado.status != "SUCCESS") {
          new Mensajes().errorOperacion(resultado.messages);
          return;
        }

        let data = resultado.data;

        this.sesion = data;        

        this.storageService.setSesion(this.sesion);
        console.log(JSON.stringify(data));
        Swal.fire({
          title: 'Bienvenido/a',
          text: 'Bienvenido/a ' + data.usuario.nombre,
          icon: 'info',
          timer: 2000

        }).then((result) => {
          window.location.replace("/misTransacciones");
        })




      }, (error: HttpErrorResponse) => {
        if (error.status == 404) {
          Swal.fire({
            title: 'Error!',
            text: 'Usuario o contraseña inválida',
            icon: 'error',
            timer: 3000

          })

        }
      });



    if (event) { event.stopPropagation(); }
  }
}
