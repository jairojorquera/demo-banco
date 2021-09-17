import { Component, OnInit } from '@angular/core';
import { Transaccion } from '../../modelo/transaccion';
import { TransaccionesService } from '../../servicios/transacciones.service';
import { StorageService } from '../../servicios/storage.service';

@Component({
  selector: 'app-mis-transacciones',
  templateUrl: './mis-transacciones.component.html',
  styleUrls: ['./mis-transacciones.component.css']
})
export class MisTransaccionesComponent implements OnInit {

  transacciones!: Transaccion[];
  ingresos: number = 0;
  egresos: number = 0;
  total: number = 0;

  constructor(private transaccionesService: TransaccionesService, private storageService: StorageService) { }

  ngOnInit(): void {

    let sesion = this.storageService.getSesion();
    this.transaccionesService.find(sesion.usuario.rut).subscribe(data => {

      this.transacciones = data.map(x => Object.assign(new Transaccion(), x));

      this.ingresos = this.calcularTotalIngresos();
      this.egresos = this.calcularTotalEgresos();
      this.total = this.ingresos - this.egresos;

    });
  }

  calcularTotalEgresos(): number {
    return this.transacciones.reduce(function (sum, record) {
      return (record.tipo !== 0) ? sum : sum + record.monto;
    }, 0);
  }
  calcularTotalIngresos(): number {
    return this.transacciones.reduce(function (sum, record) {
      return (record.tipo !== 1) ? sum : sum + record.monto;
    }, 0);
  }


}