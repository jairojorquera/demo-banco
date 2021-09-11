package jairojorquera.demo.banco.model.repository;

import jairojorquera.demo.banco.model.Transaccion;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author jjorquerar
 */
public interface TransaccionRepositorio extends JpaRepository<Transaccion, BigDecimal> {

    @Query(
            value = "SELECT * FROM transacciones tr "
            + "WHERE tr.rut = :rut order by tr.fecha desc",
            nativeQuery = true)
    List<Transaccion> findAllTransaccionPorRut(
            @Param("rut") String rut);
}
