package jairojorquera.demo.banco.service.validador.transacciones;

import jairojorquera.demo.banco.service.validador.Validador;
import jairojorquera.demo.banco.utils.TipoTransaccion;
import jairojorquera.demo.banco.model.Transaccion;
import jairojorquera.demo.banco.service.UsuarioService;
import jairojorquera.demo.banco.utils.Resultado;
import jairojorquera.demo.banco.utils.Status;

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
        if(transaccion.getRut().equals(transaccion.getRutRelacionado())){
            rtdo.addMensaje(Status.FAIL, "R.U.T. inválido. No puede transferirse a si mismo.");
        }
        
        if (TipoTransaccion.RETIRO.getCodigo() == transaccion.getTipo() && transaccion.getRutRelacionado() != null) {
            rtdo.addResultado(usuarioService.getUsuario(transaccion.getRutRelacionado()));
        }
        
        return rtdo;
    }

}
