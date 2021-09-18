package jairojorquera.demo.banco.service.validador.usuarios;

import jairojorquera.demo.banco.service.validador.Validador;
import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.model.repository.UsuarioRepositorio;
import jairojorquera.demo.banco.utils.Resultado;
import java.util.Optional;

/**
 *
 * @author jjorquerar
 *
 * Valida que usuario no existe. Si existe retorna resultado con fail
 */
public class UsuarioNuevoValidador implements Validador<Usuario> {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioNuevoValidador(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Resultado validar(Usuario usuario) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(usuario.getRut());

        if (usuarioOpt.isPresent()) {
            return Resultado.fail("Usuario ya existe");
        }

        return Resultado.of();
    }

}
