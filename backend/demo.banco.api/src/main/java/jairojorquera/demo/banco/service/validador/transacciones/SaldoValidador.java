package jairojorquera.demo.banco.service.validador.transacciones;

import jairojorquera.demo.banco.service.validador.Validador;
import jairojorquera.demo.banco.utils.TipoTransaccion;
import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.model.Usuario;
import jairojorquera.demo.banco.model.UsuarioPublico;
import jairojorquera.demo.banco.service.UsuarioService;
import jairojorquera.demo.banco.utils.Resultado;
import jairojorquera.demo.banco.utils.Status;
import java.util.Optional;

/**
 *
 * @author jjorquerar
 *
 */
public class SaldoValidador implements Validador<Transaccion> {

    private final UsuarioService usuarioService;

    public SaldoValidador(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public Resultado validar(Transaccion transaccion) {
        Resultado rtdo = Resultado.of();
        if (transaccion.getTipo() == TipoTransaccion.RETIRO.getCodigo()) {
            Resultado<UsuarioPublico> usuarioRtdo = usuarioService.getUsuario(transaccion.getRut());

            if (!usuarioRtdo.isOK()) {
                rtdo.addMensaje(Status.FAIL, "Usuario inválido");
                return rtdo;
            }

            //Si el saldo del usuario es menor que el monto de la transaccion se rechaza
            if (usuarioRtdo.getData().getSaldo().compareTo(transaccion.getMonto()) == -1) {
                rtdo.addMensaje(Status.FAIL, "Saldo insuficiente para realizar la operación");
            }
        }
        return rtdo;
    }

}
