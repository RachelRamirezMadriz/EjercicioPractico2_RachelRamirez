package EjercicioPractico2.demo;

/**
 *
 * @author Rach Ramírez
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> {
            // Recursos públicos
            requests.requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll();
            requests.requestMatchers("/login", "/error").permitAll();
            
            // ADMIN: puede gestionar usuarios y roles
            requests.requestMatchers("/usuarios/**").hasRole("ADMIN");
            requests.requestMatchers("/roles/**").hasRole("ADMIN");
            requests.requestMatchers("/consultas/**").hasRole("ADMIN");
            
            // PROFESOR:
            requests.requestMatchers("/reportes/**").hasRole("PROFESOR");
            
            // Modulo en comun
            requests.requestMatchers("/perfil/**").hasAnyRole("ESTUDIANTE", "PROFESOR", "ADMIN");
            
            // Página principal accesible para todos los autenticados
            requests.requestMatchers("/").authenticated();
            
            // Cualquier otra petición requiere autenticación
            requests.anyRequest().authenticated();
        });
        
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
        ).logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        ).exceptionHandling(exceptions -> exceptions
                .accessDeniedPage("/acceso-denegado")
        ).sessionManagement(session -> session
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
        );
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build, 
            @Lazy PasswordEncoder passwordEncoder, 
            @Lazy UserDetailsService userDetailsService) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}