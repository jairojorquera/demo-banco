import { Usuario } from "./usuario";

export class Sesion {
    token!: string;
    usuario!: Usuario;
    activa: Boolean = false;

}

