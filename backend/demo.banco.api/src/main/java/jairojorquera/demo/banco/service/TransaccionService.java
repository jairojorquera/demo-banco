package jairojorquera.demo.banco.service;

import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.model.repository.TransaccionRepositorio;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<Transaccion> getTransacciones() {
        return transaccionRepositorio.findAll();
    }

    public List<Transaccion> getTransacciones(String rut) {
        return transaccionRepositorio.findAllTransaccionPorRut(rut);
    }

    public void saveTransaccion(Transaccion transaccion) {
        transaccionRepositorio.save(transaccion);
    }

    public Transaccion getTransaccion(BigDecimal id) {
        return transaccionRepositorio.findById(id).get();

    }

}
