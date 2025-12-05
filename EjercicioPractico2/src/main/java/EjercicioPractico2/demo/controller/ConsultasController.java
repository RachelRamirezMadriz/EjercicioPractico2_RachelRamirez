package EjercicioPractico2.demo.controller;

/**
 *
 * @author Rach Ramírez
 */
import EjercicioPractico2.demo.service.RolService;
import EjercicioPractico2.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/consultas")
public class ConsultasController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;
    
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("roles", rolService.listarRoles());
        return "consultas/index";
    }
    
    @GetMapping("/por-rol")
    public String porRol(@RequestParam Long rolId, Model model) {
        var usuarios = usuarioService.buscarPorRol(rolId);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Usuarios por Rol");
        model.addAttribute("roles", rolService.listarRoles());
        return "consultas/index";
    }
    
    @GetMapping("/por-fechas")
    public String porFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin,
            Model model) {
        var usuarios = usuarioService.buscarPorRangoFechas(inicio, fin);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Usuarios por Rango de Fechas");
        model.addAttribute("roles", rolService.listarRoles());
        return "consultas/index";
    }
    
    @GetMapping("/buscar-texto")
    public String buscarTexto(@RequestParam String texto, Model model) {
        var usuarios = usuarioService.buscarPorTexto(texto);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("titulo", "Búsqueda: " + texto);
        model.addAttribute("roles", rolService.listarRoles());
        return "consultas/index";
    }
    
    @GetMapping("/estadisticas")
    public String estadisticas(Model model) {
        model.addAttribute("activos", usuarioService.contarActivos());
        model.addAttribute("inactivos", usuarioService.contarInactivos());
        model.addAttribute("usuarios", usuarioService.listarOrdenadosPorFecha());
        model.addAttribute("titulo", "Estadísticas de Usuarios");
        model.addAttribute("roles", rolService.listarRoles());
        return "consultas/index";
    }
}
