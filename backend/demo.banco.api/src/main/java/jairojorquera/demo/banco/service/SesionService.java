package jairojorquera.demo.banco.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import jairojorquera.demo.banco.model.Accion;
import jairojorquera.demo.banco.model.Credenciales;
import jairojorquera.demo.banco.model.Sesion;
import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.utils.Resultado;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author jjorquerar
 */
@Service
public class SesionService {

    @Autowired
    private AccionService accionService;

    @Autowired
    private UsuarioService usuarioService;

    @Value("${jwt.secret}")
    private String secreto;

    public Resultado<Sesion> login(Credenciales credenciales) {

        Resultado<Usuario> rtdoUsuario = usuarioService.getUsuario(credenciales);
        if (!rtdoUsuario.isOK()) {
            return Resultado.of().addResultado(rtdoUsuario);
        }

        Resultado<String> rtdoToken = generarToken(credenciales.getUsuario());
        if (!rtdoToken.isOK()) {
            return Resultado.of().addResultado(rtdoToken);
        }
        String token = rtdoToken.getData();

        Accion accion = new Accion();
        accion.setRutUsuario(credenciales.getUsuario());
        accion.setToken(token);
        accion.setActiva(true);

        Resultado<Accion> rtdoAccion = accionService.saveAccion(accion);
        if (!rtdoAccion.isOK()) {
            return Resultado.of().addResultado(rtdoAccion);
        }

        Sesion sesion = new Sesion();
        sesion.setUsuario(rtdoUsuario.getData());
        sesion.setToken(token);

        return Resultado.of(sesion);
    }

    public Resultado cerrarSesion(String rut, String token) {
        Resultado rtdo = accionService.cerrarSesion(rut, token);
        return rtdo;
    }

    private Resultado<String> generarToken(String usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secreto);
            UUID uuid = UUID.randomUUID();

            return Resultado.of(JWT.create()
                    .withIssuer("demo.banco")
                    .withSubject(usuario)
                    .withIssuedAt(new Date())
                    .withJWTId(uuid.toString())
                    .sign(algorithm));
        } catch (JWTCreationException exception) {
            return Resultado.error("Error al generar el token");
        }
    }

}
