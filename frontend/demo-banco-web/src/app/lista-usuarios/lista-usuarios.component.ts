import { Component, OnInit } from '@angular/core';
import { Usuario } from '../modelo/usuario';
import { UsuarioService } from '../servicios/usuario.service';

@Component({
  selector: 'app-lista-usuarios',
  templateUrl: './lista-usuarios.component.html',
  styleUrls: ['./lista-usuarios.component.css']
})
export class ListaUsuariosComponent implements OnInit {
  usuarios!: Usuario[];
  constructor(private userService: UsuarioService) { }

  ngOnInit(): void {
    this.userService.findAll().subscribe(data => {
      this.usuarios = data;
    });
  }

}
