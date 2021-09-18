import { Component, OnInit } from '@angular/core';
import { Transaccion } from '../../modelo/transaccion';
import { StorageService } from '../../servicios/storage.service';
import { TransaccionesService } from '../../servicios/transacciones.service';

import Swal from 'sweetalert2';
import { Mensajes } from 'src/app/utils/mensajes.utils';

@Component({
  selector: 'app-depositos',
  templateUrl: './depositos.component.html',
  styleUrls: ['./depositos.component.css']
})

export class DepositosComponent implements OnInit {
  monto: number = 0;

  constructor(private storageService: StorageService, private transaccionesService: TransaccionesService) { }

  ngOnInit(): void {
    let sesion = this.storageService.getSesion();
  }
  onConfirmar() {
    if (this.monto > 0) {
      Swal.fire({
        title: 'Confirmar deposito',
        icon: 'warning',
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Realizar deposito'
      }).then((result) => {
        if (result.isConfirmed) {
          this.onDeposito();
        }
      })
    }
  }
  onDeposito(event?: MouseEvent) {

    if (this.monto > 0) {
      let sesion = this.storageService.getSesion();

      let transaccion = new Transaccion();
      transaccion.monto = this.monto;
      transaccion.rut = sesion.usuario.rut;
      transaccion.tipo = 1;

      var formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'CLP',

        // These options are needed to round to whole numbers if that's what you want.
        //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
        //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
      });




      this.transaccionesService.save(transaccion).subscribe(resultado => {

        if (resultado.status != "SUCCESS") {
          new Mensajes(resultado.messages).errorOperacion();
          return;
        }

        let data: Transaccion = resultado.data;

        Swal.fire({
          title: 'Depósito exitoso',
          icon: 'success',
          timer: 20000,
          showDenyButton: true,
          confirmButtonText: 'Imprimir',
          denyButtonText: 'Hacer otro depósito',
          html: '<table style="width:100%; text-align: left">' +
            '<tr><th>ID</th><td>' + data.id + '</td></tr>' +
            '<tr><th>Fecha</th><td>' + new Date().toLocaleDateString() + '</td></tr>' +
            '<tr><th>Monto</th><td>' + formatter.format(data.monto) + '</td></tr>' +
            '</table>'
        }).then((result) => {
          /* Read more about isConfirmed, isDenied below */
          if (result.isConfirmed) {
            window.print()
          }
          this.monto = 0;
        })


      });

    }


  }

}
