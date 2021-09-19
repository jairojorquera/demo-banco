import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaTransaccionesComponent } from './componentes/lista-transacciones/lista-transacciones.component';
import { LoginComponent } from './componentes/login/login.component';
import { RegistrarUsuarioComponent } from './componentes/registrar-usuario/registrar-usuario.component';
import { DepositosComponent } from './componentes/depositos/depositos.component';
import { RetirosComponent } from './componentes/retiros/retiros.component';
import { MisTransaccionesComponent } from './componentes/mis-transacciones/mis-transacciones.component';
import { TransferenciaComponent } from './componentes/transferencia/transferencia.component';



const routes: Routes = [
    { path: '', redirectTo: '/misTransacciones', pathMatch: 'full' },    
    { path: 'transacciones', component: ListaTransaccionesComponent },
    { path: 'login', component: LoginComponent },
    { path: 'depositos', component: DepositosComponent },
    { path: 'retiros', component: RetirosComponent },
    { path: 'misTransacciones', component: MisTransaccionesComponent },    
    { path: 'registrarUsuario', component: RegistrarUsuarioComponent },
    { path: 'transferencias', component: TransferenciaComponent }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
