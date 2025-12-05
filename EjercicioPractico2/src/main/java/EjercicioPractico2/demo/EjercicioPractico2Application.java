package EjercicioPractico2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import EjercicioPractico2.demo.domain.Usuario;
import EjercicioPractico2.demo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@SpringBootApplication
public class EjercicioPractico2Application {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioPractico2Application.class, args);
	}
       @Bean
        CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Encriptar contraseñas 
            List<Usuario> usuarios = usuarioRepository.findAll();
            
            for (Usuario usuario : usuarios) {
                if (!usuario.getPassword().startsWith("$2a$")) {
                    System.out.println("Encriptando contraseña para: " + usuario.getEmail());
                    usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
                    usuarioRepository.save(usuario);
                }
            }
    
        };
    }
}