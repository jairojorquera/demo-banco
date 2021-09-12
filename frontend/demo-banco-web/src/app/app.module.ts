import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { ListaTransaccionesComponent } from './lista-transacciones/lista-transacciones.component';



@NgModule({
  declarations: [
    AppComponent,
    ListaUsuariosComponent,
    ListaTransaccionesComponent
  ],
  imports: [
    BrowserModule,     
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
