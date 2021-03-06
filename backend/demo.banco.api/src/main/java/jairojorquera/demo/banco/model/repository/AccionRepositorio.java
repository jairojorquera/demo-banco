package jairojorquera.demo.banco.model.repository;

import jairojorquera.demo.banco.model.Accion;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jjorquerar
 */
public interface AccionRepositorio extends JpaRepository<Accion, BigDecimal> {

    @Query(
            value = "select * from sesiones "
            + "where token = :token and rut_usuario=:rut "
            + "and activa = 1 ",
            nativeQuery = true)
    List<Accion> findSesionActiva(
            @Param("token") String token,
            @Param("rut") String rut);

    @Modifying
    @Query(
            value = "update sesiones set activa = 0 "
            + "where token = :token and rut_usuario=:rut "
            + "and activa = 1 ",
            nativeQuery = true)
    void cerrarSesion(
            @Param("rut") String rut,
            @Param("token") String token
    );
}
