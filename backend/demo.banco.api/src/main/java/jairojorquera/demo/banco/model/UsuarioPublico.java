package jairojorquera.demo.banco.model;

import java.math.BigDecimal;

/**
 *
 * @author jjorquerar
 */
public interface UsuarioPublico {

    public String getRut();

    public String getNombre();

    public String getEmail();

    public BigDecimal getSaldo();
}
