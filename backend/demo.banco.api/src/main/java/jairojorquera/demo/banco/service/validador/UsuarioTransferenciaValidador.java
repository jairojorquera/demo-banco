package jairojorquera.demo.banco.service.validador;

import jairojorquera.demo.banco.utils.TipoTransaccion;
import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.service.UsuarioService;
import jairojorquera.demo.banco.utils.Resultado;
import jairojorquera.demo.banco.utils.Status;

/**
 *
 * @author jjorquerar
 */
public class UsuarioTransferenciaValidador implements TransaccionValidador {

    private final UsuarioService usuarioService;

    public UsuarioTransferenciaValidador(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public Resultado validar(Transaccion transaccion) {
        Resultado rtdo = new Resultado();
        if (TipoTransaccion.RETIRO.getCodigo() == transaccion.getTipo() && transaccion.getRutRelacionado() != null) {
            Usuario usuario = usuarioService.getUsuario(transaccion.getRutRelacionado());

            //Si el saldo del usuario es menor que el monto de la transaccion se rechaza
            if (usuario == null) {
                rtdo.addMensaje(Status.FAIL, "No existe el usuario al que desea transferir.");
            }
        }
        return rtdo;
    }

}
