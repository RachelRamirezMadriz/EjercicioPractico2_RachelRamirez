package EjercicioPractico2.demo.service;

/**
 *
 * @author Rach Ramírez
 */
import EjercicioPractico2.demo.domain.Usuario;
import EjercicioPractico2.demo.repository.UsuarioRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Usuario guardarUsuario(Usuario usuario) {
        // Encriptar contraseña solo si es nueva o ha cambiado
        if (usuario.getId() == null || !usuario.getPassword().startsWith("$2a$")) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }
    
    @Transactional
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmailAndActivoTrue(email).orElse(null);
    }
    
    
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorRol(Long rolId) {
        return usuarioRepository.findByRolId(rolId);
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return usuarioRepository.findByFechaCreacionBetween(inicio, fin);
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorTexto(String texto) {
        return usuarioRepository.findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(texto, texto);
    }
    
    @Transactional(readOnly = true)
    public long contarActivos() {
        return usuarioRepository.countByActivoTrue();
    }
    
    @Transactional(readOnly = true)
    public long contarInactivos() {
        return usuarioRepository.countByActivoFalse();
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> listarOrdenadosPorFecha() {
        return usuarioRepository.findAllByOrderByFechaCreacionDesc();
    }
}
