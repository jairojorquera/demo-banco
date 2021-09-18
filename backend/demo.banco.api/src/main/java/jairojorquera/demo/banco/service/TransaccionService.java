package jairojorquera.demo.banco.service;

import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.model.repository.TransaccionRepositorio;
import jairojorquera.demo.banco.service.validador.TransaccionValidador;
import jairojorquera.demo.banco.utils.Resultado;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jjorquerar
 */
@Service
@Transactional
public class TransaccionService {

    @Autowired
    private TransaccionRepositorio transaccionRepositorio;

    private final TransaccionValidador validadorTransaccion;

    public TransaccionService(TransaccionValidador validator) {
        this.validadorTransaccion = validator;
    }

    public List<Transaccion> getTransacciones() {
        return transaccionRepositorio.findAll();
    }

    public List<Transaccion> getTransacciones(String rut) {
        return transaccionRepositorio.findAllTransaccionPorRut(rut);
    }

    public Resultado<Transaccion> saveTransaccion(Transaccion transaccion) {

        Resultado rtdo = validadorTransaccion.validar(transaccion);
        if (!rtdo.isOK()) {
            return rtdo;
        }

        if (transaccion != null && transaccion.getFecha() == null) {
            transaccion.setFecha(LocalDateTime.now());
        }

        return new Resultado(transaccionRepositorio.save(transaccion));
    }

    public Transaccion getTransaccion(BigDecimal id) {
        return transaccionRepositorio.findById(id).get();

    }

}
