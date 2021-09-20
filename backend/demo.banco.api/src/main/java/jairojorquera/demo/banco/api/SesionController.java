package jairojorquera.demo.banco.api;

import jairojorquera.demo.banco.model.Credenciales;
import jairojorquera.demo.banco.model.Sesion;
import jairojorquera.demo.banco.service.SesionService;
import jairojorquera.demo.banco.utils.Resultado;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @DeleteMapping("/{rut}/{token}")
    public Resultado cerrarSesion(@PathVariable String rut,@PathVariable String token) {
        logger.info("cerrarSesion [001] rut: {}", rut);
        return sesionService.cerrarSesion(rut, token);
    }

}
