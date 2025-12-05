package EjercicioPractico2.demo.controller;

/**
 *
 * @author Rach Ramírez
 */
import EjercicioPractico2.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reportes")
public class ReportesController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/")
    public String reportes(Model model) {
        var usuarios = usuarioService.listarOrdenadosPorFecha();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalActivos", usuarioService.contarActivos());
        model.addAttribute("totalInactivos", usuarioService.contarInactivos());
        return "reportes/reportes";
    }
}