package jairojorquera.demo.banco.service.validador.usuarios;

import jairojorquera.demo.banco.service.validador.Validador;
import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.utils.Resultado;
import jairojorquera.demo.banco.utils.Status;
import jairojorquera.demo.banco.utils.ValidadorUtils;

/**
 *
 * @author jjorquerar
 *
 * Valida que usuario no existe. Si existe retorna resultado con fail
 */
public class ParametrosNuevoUsuarioValidador implements Validador<Usuario> {

    public ParametrosNuevoUsuarioValidador() {

    }

    @Override
    public Resultado validar(Usuario usuario) {
        Resultado rtdo = Resultado.of();
        if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
            rtdo.addMensaje(Status.FAIL, "Nombre es obligatorio");
        }

        //RUT valido
        if (usuario.getRut() == null || usuario.getRut().isEmpty() || !ValidadorUtils.validaRut(usuario.getRut())) {
            rtdo.addMensaje(Status.FAIL, "R.U.T. inválido");
        }

        //email valido
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty() || !ValidadorUtils.validaEmail(usuario.getEmail())) {
            rtdo.addMensaje(Status.FAIL, "Email inválido");
        }

        //clave de 5 de largo numero, min y may
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty() || !ValidadorUtils.validaPassword(usuario.getPassword())) {
            rtdo.addMensaje(Status.FAIL, "Password inválida");
        }
        return rtdo;
    }

}
