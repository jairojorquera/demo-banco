package jairojorquera.demo.banco.service.validador.transacciones;

import jairojorquera.demo.banco.service.validador.Validador;
import jairojorquera.demo.banco.utils.TipoTransaccion;
import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.service.UsuarioService;
import jairojorquera.demo.banco.utils.Resultado;

/**
 *
 * @author jjorquerar
 */
public class UsuarioTransferenciaValidador implements Validador<Transaccion> {

    private final UsuarioService usuarioService;

    public UsuarioTransferenciaValidador(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public Resultado validar(Transaccion transaccion) {
        Resultado rtdo = Resultado.of();
        if (TipoTransaccion.RETIRO.getCodigo() == transaccion.getTipo() && transaccion.getRutRelacionado() != null) {
            rtdo.addResultado(usuarioService.getUsuario(transaccion.getRutRelacionado()));
        }
        return rtdo;
    }

}
