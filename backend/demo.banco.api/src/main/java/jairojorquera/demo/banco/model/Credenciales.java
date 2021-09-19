package jairojorquera.demo.banco.model;

/**
 *
 * @author jjorquerar
 */
public class Credenciales {
    String usuario;
    String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credenciales{" + "usuario=" + usuario + '}';
    }
    
}
