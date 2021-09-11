package jairojorquera.demo.banco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jjorquerar
 */
@Entity
@Table(name = "transacciones")
public class Transaccion implements Serializable {

    @Id
    private BigDecimal idTransaccion;
    private LocalDateTime fecha;
    private BigDecimal monto;
    private int tipo;
    private String rut;
    private String rutRelacionado;

    public Transaccion() {
    }
    
    public Transaccion(BigDecimal idTransaccion, LocalDateTime fecha, BigDecimal monto, int tipo, String rut, String rutRelacionado) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.tipo = tipo;
        this.rut = rut;
        this.rutRelacionado = rutRelacionado;
    }

    public BigDecimal getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(BigDecimal idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRutRelacionado() {
        return rutRelacionado;
    }

    public void setRutRelacionado(String rutRelacionado) {
        this.rutRelacionado = rutRelacionado;
    }

}
