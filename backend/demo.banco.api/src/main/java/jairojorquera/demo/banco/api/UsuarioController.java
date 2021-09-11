package jairojorquera.demo.banco.api;

import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.service.UsuarioService;
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
        try {

            Usuario usuario = usuarioService.getUsuario(rut);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public void add(@RequestBody Usuario usuario) {
        usuarioService.saveUsuario(usuario);
    }

    @PutMapping("/{rut}")
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable String rut) {
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
