package EjercicioPractico2.demo.repository;

/**
 *
 * @author Rach Ramírez
 */

import EjercicioPractico2.demo.domain.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
