package jairojorquera.demo.banco.service;

import jairojorquera.demo.banco.model.Credenciales;
import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.model.repository.UsuarioRepositorio;
import jairojorquera.demo.banco.service.validador.Validador;
import jairojorquera.demo.banco.utils.Resultado;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jjorquerar
 */
@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    private final Validador validadorUsuario;

    public UsuarioService(Validador<Usuario> validator) {
        this.validadorUsuario = validator;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Resultado<Usuario> saveUsuario(Usuario usuario) {
        Resultado rtdo = validadorUsuario.validar(usuario);
        if (!rtdo.isOK()) {
            return rtdo;
        }

        usuario.setSaldo(BigDecimal.ZERO);
        Usuario usuarioRegistrado = usuarioRepositorio.save(usuario);

        return Resultado.of(usuarioRegistrado);
    }

    public Resultado<Usuario> getUsuario(String rut) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(rut);
        if (!usuarioOpt.isPresent()) {
            return Resultado.fail("Usuario no encontrado");
        }

        return Resultado.of(usuarioOpt.get());

    }

    public Resultado<Usuario> getUsuario(Credenciales credenciales) {

        Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(credenciales.getUsuario());

        if (!usuarioOpt.isPresent() || !credenciales.getPassword().equals(usuarioOpt.get().getPassword())) {
            return Resultado.fail("Usuario y/o clave inválidos");
        }

        return Resultado.of(usuarioOpt.get());

    }

}
