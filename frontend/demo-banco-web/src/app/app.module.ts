import { NgModule } from '@angular/core';
import { LOCALE_ID } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { ListaTransaccionesComponent } from './componentes/lista-transacciones/lista-transacciones.component';
import { LoginComponent } from './componentes/login/login.component';
import { RegistrarUsuarioComponent } from './componentes/registrar-usuario/registrar-usuario.component';
import { DepositosComponent } from './componentes/depositos/depositos.component';
import { RetirosComponent } from './componentes/retiros/retiros.component';
import { MisTransaccionesComponent } from './componentes/mis-transacciones/mis-transacciones.component';

import localeEs from '@angular/common/locales/es';
import { registerLocaleData } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TransferenciaComponent } from './componentes/transferencia/transferencia.component';
import { AuthInterceptor } from './interceptores/auth-interceptor';

registerLocaleData(localeEs, 'es');

@NgModule({
  declarations: [
    AppComponent,
    
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
    NgbModule,
    ReactiveFormsModule

  ],
  providers: [{ provide: LOCALE_ID, useValue: 'es-CL' }
    , { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
