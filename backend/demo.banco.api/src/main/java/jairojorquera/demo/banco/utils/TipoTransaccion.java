package jairojorquera.demo.banco.utils;

/**
 *
 * @author jjorquerar
 */
public enum TipoTransaccion {
    RETIRO(0), DEPOSITO(1);

    int codigo;

    private TipoTransaccion(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    @Override
    public String toString() {
        return "TipoTransaccion{" + this.name() + ", codigo=" + codigo + '}';
    }

}
