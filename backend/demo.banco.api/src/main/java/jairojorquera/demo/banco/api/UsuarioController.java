package jairojorquera.demo.banco.api;

import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.service.UsuarioService;
import jairojorquera.demo.banco.utils.Resultado;
import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jjorquerar
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")
public class UsuarioController {

    Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioService usuarioService;

    @PostMapping()
    public Resultado<Usuario> add(@RequestBody Usuario usuario) {
        logger.info("add [001] usuario: {}", usuario);
        return usuarioService.saveUsuario(usuario);
    }

}
