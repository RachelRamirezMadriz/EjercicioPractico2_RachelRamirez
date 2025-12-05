package EjercicioPractico2.demo.controller;

/**
 *
 * @author Rach Ramírez
 */
import EjercicioPractico2.demo.domain.Rol;
import EjercicioPractico2.demo.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RolController {
    
    @Autowired
    private RolService rolService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        var roles = rolService.listarRoles();
        model.addAttribute("roles", roles);
        return "roles/listado";
    }
    
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("rol", new Rol());
        return "roles/formulario";
    }
    
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Rol rol) {
        rolService.guardarRol(rol);
        return "redirect:/roles/listado";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        var rol = rolService.obtenerRolPorId(id);
        model.addAttribute("rol", rol);
        return "roles/formulario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        rolService.eliminarRol(id);
        return "redirect:/roles/listado";
    }
}