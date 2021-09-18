import { Component, OnInit } from '@angular/core';
import { Transaccion } from '../../modelo/transaccion';
import { TransaccionesService } from '../../servicios/transacciones.service';
import { StorageService } from '../../servicios/storage.service';
import { Mensajes } from 'src/app/utils/mensajes.utils';

const FILTER_PAG_REGEX = /[^0-9]/g;

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
  page: number = 1;
  pageSize: number = 10;
  collectionSize = 0;

  constructor(private transaccionesService: TransaccionesService, private storageService: StorageService) { }

  ngOnInit(): void {

    let sesion = this.storageService.getSesion();
    this.transaccionesService.find(sesion.usuario.rut).subscribe(
      data => {
        this.transacciones = data.map(x => Object.assign(new Transaccion(), x));
        this.ingresos = this.calcularTotalIngresos();
        this.egresos = this.calcularTotalEgresos();
        this.total = this.ingresos - this.egresos;
        this.collectionSize = this.transacciones.length;
      },
      error => {
        new Mensajes().errorOperacion(["Error al intentar conectarse al servidor. Por favor intentelo más tarde."]);

      });
  }

  selectPage(page: string) {
    this.page = parseInt(page, 10) || 1;
  }
  formatInput(input: HTMLInputElement) {
    input.value = input.value.replace(FILTER_PAG_REGEX, '');
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
