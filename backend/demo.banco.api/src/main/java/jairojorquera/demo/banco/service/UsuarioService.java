package jairojorquera.demo.banco.service;

import jairojorquera.demo.banco.api.UsuarioController;
import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.model.repository.UsuarioRepositorio;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public List<Usuario> getUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public void saveUsuario(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    public Usuario getUsuario(String rut) {
        return usuarioRepositorio.findById(rut).get();

    }

}
