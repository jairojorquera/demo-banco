import { Component, OnInit } from '@angular/core';
import { Transaccion } from 'src/app/modelo/transaccion';
import { StorageService } from 'src/app/servicios/storage.service';
import { TransaccionesService } from 'src/app/servicios/transacciones.service';
import { Mensajes } from 'src/app/utils/mensajes.utils';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.css']
})
export class TransferenciaComponent implements OnInit {

  monto: number = 0;
  rut!: string;

  constructor(private storageService: StorageService, private transaccionesService: TransaccionesService) { }

  ngOnInit(): void {
    let sesion = this.storageService.getSesion();
  }

  onConfirmar() {
    if (this.monto > 0) {
      Swal.fire({
        title: 'Confirmar transferencia',
        icon: 'warning',
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        confirmButtonColor: '#28a745',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Realizar transferencia'
      }).then((result) => {
        if (result.isConfirmed) {
          this.onTransferencia();
        }
      })
    }
  }



  onTransferencia() {
    if (this.monto > 0) {
      let sesion = this.storageService.getSesion();
      let transaccion = new Transaccion();
      transaccion.monto = this.monto;
      transaccion.rut = sesion.usuario.rut;
      transaccion.rutRelacionado = this.rut;
      transaccion.tipo = 0;

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
          title: 'Retiro exitoso',
          icon: 'success',
          timer: 20000,
          showDenyButton: true,
          confirmButtonText: 'Imprimir',
          confirmButtonColor: '#3085d6',
          denyButtonColor: '#28a745',
          denyButtonText: 'Hacer otro retiro',
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


      },
      error => {
        new Mensajes(["Error al intentar conectarse al servidor. Por favor intentelo más tarde."]).errorOperacion();

      });

    }

  }

}
