import { NgModule } from '@angular/core';
import { LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { ListaUsuariosComponent } from './componentes/lista-usuarios/lista-usuarios.component';
import { ListaTransaccionesComponent } from './componentes/lista-transacciones/lista-transacciones.component';
import { LoginComponent } from './componentes/login/login.component';
import { RegistrarUsuarioComponent } from './componentes/registrar-usuario/registrar-usuario.component';
import { DepositosComponent } from './componentes/depositos/depositos.component';
import { RetirosComponent } from './componentes/retiros/retiros.component';
import { MisTransaccionesComponent } from './componentes/mis-transacciones/mis-transacciones.component';

import localeEs from '@angular/common/locales/es';
import  { registerLocaleData } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TransferenciaComponent } from './componentes/transferencia/transferencia.component';

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
    MisTransaccionesComponent,
    TransferenciaComponent
  ],
  imports: [
    BrowserModule,     
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  
  ],
  providers: [ { provide: LOCALE_ID, useValue: 'es-CL' }],
  bootstrap: [AppComponent]
})
export class AppModule { }
