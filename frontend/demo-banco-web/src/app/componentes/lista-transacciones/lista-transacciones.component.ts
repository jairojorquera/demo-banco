import { Component, OnInit } from '@angular/core';
import { Transaccion } from '../../modelo/transaccion';
import { TransaccionesService } from '../../servicios/transacciones.service';

@Component({
  selector: 'app-lista-transacciones',
  templateUrl: './lista-transacciones.component.html',
  styleUrls: ['./lista-transacciones.component.css']
})
export class ListaTransaccionesComponent implements OnInit {
  transacciones!: Transaccion[];
  constructor(private transaccionesService: TransaccionesService) { }

  ngOnInit(): void {
    this.transaccionesService.findAll().subscribe(data => {
      this.transacciones = data;
    });
  }

}
