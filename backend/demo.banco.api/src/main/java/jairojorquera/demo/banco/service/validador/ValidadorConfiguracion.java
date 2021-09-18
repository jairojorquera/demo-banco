package jairojorquera.demo.banco.service.validador;

import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.model.repository.UsuarioRepositorio;
import jairojorquera.demo.banco.service.UsuarioService;
import jairojorquera.demo.banco.service.validador.transacciones.SaldoValidador;
import jairojorquera.demo.banco.service.validador.transacciones.TransaccionValidadorComposite;
import jairojorquera.demo.banco.service.validador.transacciones.UsuarioTransferenciaValidador;
import jairojorquera.demo.banco.service.validador.usuarios.ParametrosNuevoUsuarioValidador;
import jairojorquera.demo.banco.service.validador.usuarios.UsuarioNuevoValidador;
import jairojorquera.demo.banco.service.validador.usuarios.UsuarioValidadorComposite;
import java.util.Arrays;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jjorquerar
 */
@Configuration
public class ValidadorConfiguracion {

    @Bean
    Validador<Transaccion> transferenciaValidator(UsuarioService usuarioService) {
        return new TransaccionValidadorComposite(Arrays.asList(
                new SaldoValidador(usuarioService),
                new UsuarioTransferenciaValidador(usuarioService)
        ));
    }

    @Bean
    Validador<Usuario> usuarioValidator(UsuarioRepositorio usuarioService) {
        return new UsuarioValidadorComposite(Arrays.asList(
                new UsuarioNuevoValidador(usuarioService),
                new ParametrosNuevoUsuarioValidador()
        ));
    }
}
