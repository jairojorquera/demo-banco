package jairojorquera.demo.banco.service.validador;

import jairojorquera.demo.banco.service.UsuarioService;
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
    TransaccionValidador orderItemValidator(UsuarioService usuarioService) {
        return new TransaccionValidadorComposite(Arrays.asList(
                new SaldoValidador(usuarioService), 
                new UsuarioTransferenciaValidador(usuarioService)
        ));
    }
}
