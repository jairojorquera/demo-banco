import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { ListaTransaccionesComponent } from './lista-transacciones/lista-transacciones.component';
import { LoginComponent } from './login/login.component';
import { RegistrarUsuarioComponent } from './registrar-usuario/registrar-usuario.component';
import { DepositosComponent } from './depositos/depositos.component';
import { RetirosComponent } from './retiros/retiros.component';
import { MisTransaccionesComponent } from './mis-transacciones/mis-transacciones.component';



const routes: Routes = [
    { path: '', redirectTo: '/misTransacciones', pathMatch: 'full' },
    { path: 'usuarios', component: ListaUsuariosComponent },
    { path: 'transacciones', component: ListaTransaccionesComponent },
    { path: 'login', component: LoginComponent },
    { path: 'depositos', component: DepositosComponent },
    { path: 'retiros', component: RetirosComponent },
    { path: 'misTransacciones', component: MisTransaccionesComponent },    
    { path: 'registrarUsuario', component: RegistrarUsuarioComponent }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
