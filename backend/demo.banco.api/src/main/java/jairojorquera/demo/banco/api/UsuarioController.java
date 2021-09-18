package jairojorquera.demo.banco.api;

import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.service.UsuarioService;
import java.util.List;
import java.util.Optional;
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

    @GetMapping()
    public List<Usuario> list() {
        return usuarioService.getUsuarios();
    }

    @GetMapping("/{rut}")
    public ResponseEntity<Usuario> get(@PathVariable String rut) {
        logger.info("get [001] rut: {}", rut);
        try {

            Optional<Usuario> usuarioOpt = usuarioService.getUsuario(rut);

            return new ResponseEntity<>(usuarioOpt.get(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public void add(@RequestBody Usuario usuario) {
        logger.info("add [001] usuario: {}", usuario);
        usuarioService.saveUsuario(usuario);
    }

    @PutMapping("/{rut}")
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable String rut) {
        logger.info("update [001] rut: {}", rut);
        try {
            usuarioService.getUsuario(rut);
            usuario.setRut(rut);
            usuarioService.saveUsuario(usuario);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
