import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usuario ="Jairo";
  password ="";

  constructor() { }

  ngOnInit(): void {

  }

  onLogin(event?: MouseEvent) {
    alert(this.usuario);


    if (event) { event.stopPropagation(); }
  }
}
