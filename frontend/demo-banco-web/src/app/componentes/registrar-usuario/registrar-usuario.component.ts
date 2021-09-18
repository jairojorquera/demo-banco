import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Usuario } from 'src/app/modelo/usuario';
import { UsuarioService } from 'src/app/servicios/usuario.service';
import { Mensajes } from 'src/app/utils/mensajes.utils';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css']
})
export class RegistrarUsuarioComponent implements OnInit {

  registered = false;
  submitted = false;
  userForm!: FormGroup;


  constructor(private formBuilder: FormBuilder, private usuarioService: UsuarioService, private router: Router) { }

  ngOnInit(): void {
    this.userForm = this.formBuilder.group({
      nombre: ['', Validators.required],
      rut: ['', [Validators.required, Validators.minLength(9), Validators.pattern('^[0-9]+-[0-9kK]{1}$')]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(5), Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$')]],
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.userForm.invalid == true) {
      new Mensajes().validacionesPendientes();
      return;
    }

    let usuario = new Usuario();
    usuario.nombre = this.userForm.controls.nombre.value;
    usuario.rut = this.userForm.controls.rut.value;
    usuario.email = this.userForm.controls.email.value;
    usuario.password = this.userForm.controls.password.value;

    this.usuarioService.save(usuario)
      .subscribe(resultado => {
        if (resultado.status != "SUCCESS") {
          new Mensajes().errorOperacion(resultado.messages);
          return;
        }

        let data: Usuario = resultado.data;

        Swal.fire({
          title: 'Registro exitoso',
          icon: 'success',
          timer: 20000,
          showDenyButton: true,
          confirmButtonText: 'Ir a iniciar sesión',
          text: 'Cuenta creada'
        }).then((result) => {
          this.router.navigate(['/', 'login']);
        })
      },
        error => {
          new Mensajes().errorOperacion(["Error al intentar conectarse al servidor. Por favor intentelo más tarde."]);
        });


    this.registered = true;



  }

  isInvalidNombre() {
    return (this.submitted && this.userForm.controls.nombre.errors != null);
  }

  isInvalidRut() {
    return (this.submitted && this.userForm.controls.rut.errors != null);
  }

  isInvalidEmail() {
    return (this.submitted && this.userForm.controls.email.errors != null);
  }

  isInvalidPassword() {
    return (this.submitted && this.userForm.controls.password.errors != null);
  }

}
