package jairojorquera.demo.banco.api;

import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.service.TransaccionService;
import jairojorquera.demo.banco.utils.Resultado;
import java.util.List;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/transacciones")
public class TransaccionController {

    Logger logger = LoggerFactory.getLogger(TransaccionController.class);

    @Autowired
    TransaccionService transaccionService;

    @GetMapping()
    public List<Transaccion> list() {
        return transaccionService.getTransacciones();
    }

    @GetMapping("/{rut}")
    public List<Transaccion> get(@PathVariable String rut) {
        return transaccionService.getTransacciones(rut);

    }

    @PostMapping()
    public Resultado<Transaccion> add(@RequestBody Transaccion transaccion) {
        logger.info("add [INI] transaccion: {}", transaccion);
        return transaccionService.saveTransaccion(transaccion);
    }

}
