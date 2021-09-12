import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListaUsuariosComponent } from './lista-usuarios/lista-usuarios.component';
import { ListaTransaccionesComponent } from './lista-transacciones/lista-transacciones.component';



const routes: Routes = [
    { path: '', redirectTo: '/usuarios', pathMatch: 'full' },
    { path: 'usuarios', component: ListaUsuariosComponent },
    { path: 'transacciones', component: ListaTransaccionesComponent }

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
