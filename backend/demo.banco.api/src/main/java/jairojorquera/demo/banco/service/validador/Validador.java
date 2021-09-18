package jairojorquera.demo.banco.service.validador;

import jairojorquera.demo.banco.utils.Resultado;

/**
 *
 * @author jjorquerar
 */
public interface Validador<T> {

    public Resultado validar(T transaccion);

}
