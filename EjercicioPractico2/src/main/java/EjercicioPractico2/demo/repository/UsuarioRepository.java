package EjercicioPractico2.demo.repository;
/**
 *
 * @author Rach Ramírez
 */
import EjercicioPractico2.demo.domain.Usuario;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailAndActivoTrue(String email);
    List<Usuario> findByActivoTrue();
    Optional<Usuario> findByNombre(String nombre);
    boolean existsByNombreOrEmail(String nombre, String email);
    
   
    
    // Consultas
    List<Usuario> findByRolId(Long rolId);
    
    List<Usuario> findByFechaCreacionBetween(LocalDateTime inicio, LocalDateTime fin);
    
    List<Usuario> findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(String nombre, String email);
    
    long countByActivoTrue();
    long countByActivoFalse();
    
    List<Usuario> findAllByOrderByFechaCreacionDesc();
}
