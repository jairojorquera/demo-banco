package jairojorquera.demo.banco.service.validador;

import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.utils.Resultado;

/**
 *
 * @author jjorquerar
 */
public interface TransaccionValidador {

    public Resultado validar(Transaccion transaccion);

}
