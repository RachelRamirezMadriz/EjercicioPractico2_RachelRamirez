package EjercicioPractico2.demo.domain;

/**
 *
 * @author Rach Ramírez
 */

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario") // Mapear tabla usuario
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "apellido", length = 150)
    private String apellido;

    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email; 

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    // Relación Many-to-One con la entidad Rol
    @ManyToOne 
    @JoinColumn(name = "rol_id", nullable = false) 
    private Rol rol; 

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
}
