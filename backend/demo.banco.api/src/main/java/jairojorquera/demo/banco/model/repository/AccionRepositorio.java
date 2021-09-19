package jairojorquera.demo.banco.model.repository;

import jairojorquera.demo.banco.model.Accion;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jjorquerar
 */
public interface AccionRepositorio extends JpaRepository<Accion, BigDecimal> {

    @Query(
            value = "select * from sesiones "
            + "where token = :token and rut_usuario=:rut "
            + "and accion_fecha + :max > now() and activa = 1 ",
            nativeQuery = true)
    List<Accion> findSesionActiva(
            @Param("token") String token,
            @Param("rut") String rut,
            @Param("max") Integer max);
}
