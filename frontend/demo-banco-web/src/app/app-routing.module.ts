import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListaUsuariosComponent } from './componentes/lista-usuarios/lista-usuarios.component';
import { ListaTransaccionesComponent } from './componentes/lista-transacciones/lista-transacciones.component';
import { LoginComponent } from './componentes/login/login.component';
import { RegistrarUsuarioComponent } from './componentes/registrar-usuario/registrar-usuario.component';
import { DepositosComponent } from './componentes/depositos/depositos.component';
import { RetirosComponent } from './componentes/retiros/retiros.component';
import { MisTransaccionesComponent } from './componentes/mis-transacciones/mis-transacciones.component';



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
