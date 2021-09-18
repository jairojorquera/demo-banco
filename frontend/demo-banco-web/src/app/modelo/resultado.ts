import { Transaccion } from "./transaccion";

export class Resultado<T>{
    status!: string;
    data!: T;
    messages!: string[];    
}
