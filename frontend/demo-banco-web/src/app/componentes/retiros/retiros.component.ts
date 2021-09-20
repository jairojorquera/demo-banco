import { Component, OnInit } from '@angular/core';
import { Transaccion } from '../../modelo/transaccion';
import { StorageService } from '../../servicios/storage.service';
import { TransaccionesService } from '../../servicios/transacciones.service';
import Swal from 'sweetalert2';
import { Mensajes } from 'src/app/utils/mensajes.utils';

@Component({
  selector: 'app-retiros',
  templateUrl: './retiros.component.html',
  styleUrls: ['./retiros.component.css']
})
export class RetirosComponent implements OnInit {
  monto: number = 0;


  constructor(private storageService: StorageService, private transaccionesService: TransaccionesService) { }

  ngOnInit(): void {
    let sesion = this.storageService.getSesion();
  }

  onConfirmar() {
    if (this.monto > 0) {
      Swal.fire({
        title: 'Confirmar retiro',
        icon: 'warning',
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        confirmButtonColor: '#28a745',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Realizar retiro'
      }).then((result) => {
        if (result.isConfirmed) {
          this.onRetiro();
        }
      })
    }
  }



  onRetiro() {
    if (this.monto > 0) {
      let sesion = this.storageService.getSesion();
      let transaccion = new Transaccion();
      transaccion.monto = this.monto;
      transaccion.rut = sesion.usuario.rut;
      transaccion.tipo = 0;

      var formatter = new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'CLP',

        // These options are needed to round to whole numbers if that's what you want.
        //minimumFractionDigits: 0, // (this suffices for whole numbers, but will print 2500.10 as $2,500.1)
        //maximumFractionDigits: 0, // (causes 2500.99 to be printed as $2,501)
      });
      Mensajes.loading();

      this.transaccionesService.save(transaccion).subscribe(
        resultado => {

          if (resultado.status != "SUCCESS") {
            Mensajes.warningOperacion(resultado.messages);
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
          Mensajes.errorOperacion( ["Se presentó un error al intentar registrar el retiro. Por favor revise su cuenta e inténtelo en unos minutos"]);

        });

    }

  }
}
