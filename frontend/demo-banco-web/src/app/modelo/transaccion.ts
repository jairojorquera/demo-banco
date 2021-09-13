export class Transaccion {
    rut!: string;
    rutRelacionado!: string;
    id!: number;
    monto!: number;
    tipo!: number;
    fecha!: Date;

    isRetiro(): boolean {
        return this.tipo === 0 && !this.rutRelacionado;
    }
    isDeposito(): boolean {
        return this.tipo === 1 && !this.rutRelacionado;
    }
    isTransferenciaHacia(): boolean {
        return this.tipo === 0 && this.rutRelacionado != null;
    }
    isTransferenciaDesde(): boolean {
        return this.tipo === 1 && this.rutRelacionado != null;
    }

    customDescripcion(): string {
        if(this.isRetiro()) return "Retiro de dinero";
        if(this.isDeposito()) return "Depósito de dinero";
        if(this.isTransferenciaHacia()) return "Transferencia enviado al R.U.T. " + this.rutRelacionado;
        if(this.isTransferenciaDesde()) return "Transferencia recibida de R.U.T. " + this.rutRelacionado;
        return "";
        
    }
}
