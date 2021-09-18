package jairojorquera.demo.banco.service.validador;

import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.utils.Resultado;
import java.util.List;

/**
 *
 * @author jjorquerar
 */
public class TransaccionValidadorComposite implements TransaccionValidador {

    private final List< TransaccionValidador> validadores;

    TransaccionValidadorComposite(List< TransaccionValidador> validadores) {
        this.validadores = validadores;
    }

    @Override
    public Resultado validar(Transaccion transaccion) {
        Resultado rtdo = new Resultado();
        validadores.forEach(validador -> {
            rtdo.addResultado(validador.validar(transaccion));
        });
        return rtdo;
    }

}
