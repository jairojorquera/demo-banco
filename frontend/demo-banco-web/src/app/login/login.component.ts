import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';

import { Sesion } from '../modelo/sesion';
import { UsuarioService } from '../servicios/usuario.service';
import { StorageService } from '../servicios/storage.service';

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


  constructor(private userService: UsuarioService, private storageService: StorageService, private router: Router) { }

  ngOnInit(): void {

  }

  onLogin(event?: MouseEvent) {


    this.userService.login(this.usuario)
      //TODO: generar token o recibir de api de sesion
      .subscribe(data => {
        this.sesion = {
          token: "123", usuario: data
        };

        this.storageService.setSesion(this.sesion);
        
        Swal.fire({
          title: 'Bienvenido',
          text: 'Bienvenido' + data.nombre,
          icon: 'info',
          timer: 2000

        })

        this.router.navigate(['/', 'misTransacciones']);

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
