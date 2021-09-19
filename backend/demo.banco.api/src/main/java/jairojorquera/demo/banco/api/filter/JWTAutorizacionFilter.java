package jairojorquera.demo.banco.api.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jairojorquera.demo.banco.model.Accion;
import jairojorquera.demo.banco.service.AccionService;
import jairojorquera.demo.banco.utils.Resultado;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author jjorquerar
 */
@Component
public class JWTAutorizacionFilter extends OncePerRequestFilter {
    
    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    
    @Value("${jwt.secret}")
    private String SECRETO;
    
    @Autowired
    private AccionService accionService;
    
    private void sendError(HttpServletResponse response, String error) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setHeader("detalle", error);
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Sesión Inválida");
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            
            if (!checkJWTToken(request, response)) {
                sendError(response, "Solicitud inválida");
            }
            String token = request.getHeader(HEADER).replace(PREFIX, "");
            
            Resultado<DecodedJWT> claimsRtdo = validarToken(token);
            
            if (!claimsRtdo.isOK()) {
                sendError(response, "JWT inválido");
            }

            //falta validar que el token esta en la BD
            List<Accion> acciones = accionService.getSesionActiva(token, claimsRtdo.getData().getSubject());
            if (acciones != null && !acciones.isEmpty()) {
                Accion accion = acciones.get(0);
                accion.setAccionFecha(LocalDateTime.now());
                accionService.saveAccion(accion);
                
                chain.doFilter(request, response);
            } else {
                sendError(response, "Token inválido");
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setHeader("detalle", e.getMessage());
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "Sesión Inválida");
            
        }
    }
    
    private Resultado<DecodedJWT> validarToken(String token) {
        
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRETO);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("demo.banco")
                    .build(); //Reusable verifier instance            

            return Resultado.of(verifier.verify(token));
        } catch (JWTVerificationException exception) {
            return Resultado.error("Sesión inválida");
        }
        
    }
    
    private boolean checkJWTToken(HttpServletRequest request, HttpServletResponse res) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
            return false;
        }
        return true;
    }
    
}
