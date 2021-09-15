import { Component, OnInit } from '@angular/core';
import { StorageService } from '../servicios/storage.service';

@Component({
  selector: 'app-depositos',
  templateUrl: './depositos.component.html',
  styleUrls: ['./depositos.component.css']
})
export class DepositosComponent implements OnInit {
  monto = 0;


  constructor(private storageService: StorageService) { }

  ngOnInit(): void {
    let sesion = this.storageService.getSesion();
  }
  onDeposito(event?: MouseEvent) { 
    if(this.monto < 0){
      alert("Depositando " + this.monto);

    }
    

  }

}
