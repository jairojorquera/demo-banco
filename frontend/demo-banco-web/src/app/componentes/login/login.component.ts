import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { UsuarioService } from '../../servicios/usuario.service';
import { StorageService } from '../../servicios/storage.service';
import { SesionService } from '../../servicios/sesion.service';

import { Sesion } from '../../modelo/sesion';
import { Mensajes } from '../../utils/mensajes.utils';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  registered = false;
  submitted = false;
  userForm!: FormGroup;

  sesion!: Sesion;


  constructor(private userService: UsuarioService, private storageService: StorageService, private sesionService: SesionService,
    private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      usuario: ['', [Validators.required, Validators.minLength(9), Validators.pattern('^[0-9]+-[0-9kK]{1}$')]],
      password: ['', Validators.required],
    });
  }

  onLogin(event?: MouseEvent) {
    this.submitted = true;
    if (this.userForm.invalid == true) {
      new Mensajes().validacionesPendientes();
      return;
    }

    Mensajes.loading();


    this.sesionService.login(
      this.userForm.controls.usuario.value
      , this.userForm.controls.password.value
    ).subscribe(resultado => {

      if (resultado.status != "SUCCESS") {
        Mensajes.warningOperacion(resultado.messages);
        return;
      }

      let data = resultado.data;

      this.sesion = data;

      this.storageService.setSesion(this.sesion);
      Swal.fire({
        title: 'Bienvenido/a',
        text: 'Estimado/a ' + data.usuario.nombre,
        icon: 'info',
        timer: 2000,
        showConfirmButton: false,
        showLoaderOnConfirm: true
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

  isInvalidRut() {
    return (this.submitted && this.userForm.controls.usuario.errors != null);
  }

  isInvalidPassword() {
    return (this.submitted && this.userForm.controls.password.errors != null);
  }
}
