import { Transaccion } from "./transaccion";

export class Resultado{
    status!: string;
    data!: Transaccion;
    messages!: string[];    
}
