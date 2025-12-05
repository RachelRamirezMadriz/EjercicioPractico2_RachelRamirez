package EjercicioPractico2.demo.controller;

/**
 *
 * @author Rach Ramírez
 */
import EjercicioPractico2.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/")
    public String perfil(Model model, Authentication authentication) {
        String email = authentication.getName();
        var usuario = usuarioService.buscarPorEmail(email);
        model.addAttribute("usuario", usuario);
        return "perfil/perfil";
    }
}
