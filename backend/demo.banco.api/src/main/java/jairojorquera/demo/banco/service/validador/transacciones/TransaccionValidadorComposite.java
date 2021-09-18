package jairojorquera.demo.banco.service.validador.transacciones;

import jairojorquera.demo.banco.service.validador.Validador;
import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.utils.Resultado;
import java.util.List;

/**
 *
 * @author jjorquerar
 */
public class TransaccionValidadorComposite implements Validador<Transaccion> {

    private final List< Validador> validadores;

    public TransaccionValidadorComposite(List< Validador> validadores) {
        this.validadores = validadores;
    }

    @Override
    public Resultado validar(Transaccion transaccion) {
        Resultado rtdo = Resultado.of();
        validadores.forEach(validador -> {
            rtdo.addResultado(validador.validar(transaccion));
        });
        return rtdo;
    }

}
