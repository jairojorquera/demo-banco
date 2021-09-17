package jairojorquera.demo.banco.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;
    private LocalDateTime fecha;
    private BigDecimal monto;
    private int tipo;
    private String rut;
    private String rutRelacionado;

    public Transaccion() {
    }
    
    public Transaccion(BigDecimal id, LocalDateTime fecha, BigDecimal monto, int tipo, String rut, String rutRelacionado) {
        this.id = id;
        this.fecha = fecha;
        this.monto = monto;
        this.tipo = tipo;
        this.rut = rut;
        this.rutRelacionado = rutRelacionado;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Transaccion{" + "id=" + id + ", fecha=" + fecha + ", monto=" + monto + ", tipo=" + tipo + ", rut=" + rut + ", rutRelacionado=" + rutRelacionado + '}';
    }

}
