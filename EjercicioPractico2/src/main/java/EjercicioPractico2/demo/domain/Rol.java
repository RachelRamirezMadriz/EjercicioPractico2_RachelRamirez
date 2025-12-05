package EjercicioPractico2.demo.domain;

/**
 *
 * @author Rach Ramírez
 */

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "rol")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;
    
    @Column(name = "descripcion", length = 100)
    private String descripcion;
}
