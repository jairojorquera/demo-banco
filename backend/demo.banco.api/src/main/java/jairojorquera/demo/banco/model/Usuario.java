package jairojorquera.demo.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jjorquerar
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    private String rut;
    private String nombre;
    private String email;
    private BigDecimal saldo;
    
    @JsonIgnore
    private String password;

    public Usuario() {
    }

    public Usuario(String rut, String nombre, String email, String password) {
        this.rut = rut;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "rut=" + rut + ", nombre=" + nombre + ", email=" + email + ", saldo=" + saldo + ", password=" + password + '}';
    }


}
