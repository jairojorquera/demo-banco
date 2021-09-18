import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registrar-usuario',
  templateUrl: './registrar-usuario.component.html',
  styleUrls: ['./registrar-usuario.component.css']
})
export class RegistrarUsuarioComponent implements OnInit {

  registered = false;
  submitted = false;
  userForm!: FormGroup;


  constructor(private formBuilder: FormBuilder) { }



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
      return;
    }
    else {
      this.registered = true;
    }
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
