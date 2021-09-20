package jairojorquera.demo.banco.service;

import jairojorquera.demo.banco.model.Accion;
import jairojorquera.demo.banco.model.repository.AccionRepositorio;
import jairojorquera.demo.banco.utils.Resultado;
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
public class AccionService {

    @Autowired
    private AccionRepositorio accionRepositorio;

    public AccionService() {
    }

    public List<Accion> getSesionActiva(String token, String rut) {
        return accionRepositorio.findSesionActiva(token, rut);
    }

    public Resultado cerrarSesion(String rut, String token) {
        accionRepositorio.cerrarSesion(rut, token);
        return Resultado.of();
    }

    public Resultado<Accion> saveAccion(Accion accion) {
        return Resultado.of(accionRepositorio.save(accion));
    }

}
