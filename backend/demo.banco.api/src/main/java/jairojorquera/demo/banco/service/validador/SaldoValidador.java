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
public class SaldoValidador implements TransaccionValidador {

    private final UsuarioService usuarioService;

    public SaldoValidador(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public Resultado validar(Transaccion transaccion) {
        Resultado rtdo = new Resultado();
        if (transaccion.getTipo() == TipoTransaccion.RETIRO.getCodigo()) {
            Usuario usuario = usuarioService.getUsuario(transaccion.getRut());

            //Si el saldo del usuario es menor que el monto de la transaccion se rechaza
            if (usuario.getSaldo().compareTo(transaccion.getMonto()) == -1) {
                rtdo.addMensaje(Status.FAIL, "Saldo insuficiente para realizar la operación");
            }
        }
        return rtdo;
    }

}
