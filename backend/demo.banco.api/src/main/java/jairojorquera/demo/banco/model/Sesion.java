package jairojorquera.demo.banco.model;

/**
 *
 * @author jjorquerar
 */
public class Sesion {

    String token;
    Usuario usuario;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Sesion{" + "token=" + token + ", usuario=" + usuario + '}';
    }

    
    
}
