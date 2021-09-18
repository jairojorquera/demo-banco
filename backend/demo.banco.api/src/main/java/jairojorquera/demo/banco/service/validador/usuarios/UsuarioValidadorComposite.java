package jairojorquera.demo.banco.service.validador.usuarios;

import jairojorquera.demo.banco.service.validador.Validador;
import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.utils.Resultado;
import java.util.List;

/**
 *
 * @author jjorquerar
 */
public class UsuarioValidadorComposite implements Validador<Usuario> {

    private final List< Validador> validadores;

    public UsuarioValidadorComposite(List< Validador> validadores) {
        this.validadores = validadores;
    }

    @Override
    public Resultado validar(Usuario usuario) {
        Resultado rtdo = Resultado.of();
        validadores.forEach(validador -> {
            rtdo.addResultado(validador.validar(usuario));
        });
        return rtdo;
    }

}
