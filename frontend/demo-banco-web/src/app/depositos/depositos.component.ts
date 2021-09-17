import { Component, OnInit } from '@angular/core';
import { Transaccion } from '../modelo/transaccion';
import { StorageService } from '../servicios/storage.service';
import { TransaccionesService } from '../servicios/transacciones.service';

import Swal from 'sweetalert2';

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

  onDeposito(event?: MouseEvent) {

    if (this.monto > 0) {
      let sesion = this.storageService.getSesion();

      let transaccion = new Transaccion;
      transaccion.monto = this.monto;
      transaccion.rut = sesion.usuario.rut;
      transaccion.tipo = 1;

      this.transaccionesService.save(transaccion).subscribe(data => {
        
        Swal.fire({
          title: 'Deposito exitoso',          
          icon: 'success',
          timer: 20000,
          showDenyButton: true,
          confirmButtonText: 'Imprimir',
          denyButtonText: 'Cerrar',
          html:     '<table style="width:100%; text-align: left">'+
          '<tr><th>ID</th><td>' + data.id +'</td></tr>'+
          '<tr><th>Fecha</th><td>' + data.fecha.toLocaleDateString() +'</td></tr>'+
          '<tr><th>Monto</th><td>' + data.monto +'</td></tr>'+
          '</table>'
         

        }).then((result) => {
          /* Read more about isConfirmed, isDenied below */
          if (result.isConfirmed) {
            window.print()
          } 
        })
//{"id":17,"fecha":"2021-09-17T01:55:02.44","monto":2,"tipo":1,"rut":"16349750-6","rutRelacionado":null}

      });


    }


  }

}
