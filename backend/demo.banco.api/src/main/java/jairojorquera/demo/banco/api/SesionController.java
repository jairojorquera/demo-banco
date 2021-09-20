package jairojorquera.demo.banco.api;

import jairojorquera.demo.banco.model.Credenciales;
import jairojorquera.demo.banco.model.Sesion;
import jairojorquera.demo.banco.service.SesionService;
import jairojorquera.demo.banco.utils.Resultado;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jjorquerar
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/sesiones")
public class SesionController {
    
    Logger logger = LoggerFactory.getLogger(SesionController.class);
    
    @Autowired
    SesionService sesionService;
    
    @PostMapping()
    public Resultado<Sesion> add(@RequestBody Credenciales credenciales) {
        logger.info("add [001] credenciales: {}", credenciales);
        return sesionService.login(credenciales);
    }
    
     
    /*


    @GetMapping("/{rut}")
    public Resultado<Usuario> get(@PathVariable String rut) {
        logger.info("get [001] rut: {}", rut);
        Resultado<Usuario> usuarioRtdo = usuarioService.getUsuario(rut);
        return usuarioRtdo;
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
    }*/
}
