package jairojorquera.demo.banco.service;

import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.model.repository.TransaccionRepositorio;
import jairojorquera.demo.banco.service.validador.TransaccionValidador;
import jairojorquera.demo.banco.utils.Resultado;
import jairojorquera.demo.banco.utils.TipoTransaccion;
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

        //Si es una transferencia, se crea una nueva transacción para registrar en cuenta del relacionado
        if (TipoTransaccion.RETIRO.getCodigo() == transaccion.getTipo()
                && transaccion.getRutRelacionado() != null) {
            Transaccion transaccionRelacionada = new Transaccion();
            transaccionRelacionada.setFecha(transaccion.getFecha());
            transaccionRelacionada.setMonto(transaccion.getMonto());
            transaccionRelacionada.setRut(transaccion.getRutRelacionado());
            transaccionRelacionada.setRutRelacionado(transaccion.getRut());
            transaccionRelacionada.setTipo(TipoTransaccion.DEPOSITO.getCodigo());
            rtdo.addResultado(this.saveTransaccion(transaccionRelacionada));
            if (!rtdo.isOK()) {
                return rtdo;
            }
        }

        return new Resultado(transaccionRepositorio.save(transaccion));
    }

    public Transaccion getTransaccion(BigDecimal id) {
        return transaccionRepositorio.findById(id).get();

    }

}
