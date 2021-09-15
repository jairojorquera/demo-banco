import { NgModule } from '@angular/core';
import { LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { ListaTransaccionesComponent } from './lista-transacciones/lista-transacciones.component';
import { LoginComponent } from './login/login.component';
import { RegistrarUsuarioComponent } from './registrar-usuario/registrar-usuario.component';
import { DepositosComponent } from './depositos/depositos.component';
import { RetirosComponent } from './retiros/retiros.component';
import { MisTransaccionesComponent } from './mis-transacciones/mis-transacciones.component';

import localeEs from '@angular/common/locales/es';
import  { registerLocaleData } from '@angular/common';
registerLocaleData(localeEs, 'es');

@NgModule({
  declarations: [
    AppComponent,
    ListaUsuariosComponent,
    ListaTransaccionesComponent,
    LoginComponent,
    RegistrarUsuarioComponent,
    DepositosComponent,
    RetirosComponent,
    MisTransaccionesComponent
  ],
  imports: [
    BrowserModule,     
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  
  ],
  providers: [ { provide: LOCALE_ID, useValue: 'es-CL' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
