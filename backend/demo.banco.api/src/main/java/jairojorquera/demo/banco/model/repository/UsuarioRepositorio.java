package jairojorquera.demo.banco.model.repository;

import jairojorquera.demo.banco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jjorquerar
 */
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
}
