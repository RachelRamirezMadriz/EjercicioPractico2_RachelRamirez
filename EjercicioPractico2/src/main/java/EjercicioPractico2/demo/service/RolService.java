/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package EjercicioPractico2.demo.service;

/**
 *
 * @author Rach Ramírez
 */
import EjercicioPractico2.demo.domain.Rol;
import EjercicioPractico2.demo.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;
    
    @Transactional(readOnly = true)
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Rol obtenerRolPorId(Long id) {
        return rolRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Rol guardarRol(Rol rol) {
        return rolRepository.save(rol);
    }
    
    @Transactional
    public void eliminarRol(Long id) {
        rolRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public Rol buscarPorNombre(String nombre) {
        return rolRepository.findByNombre(nombre).orElse(null);
    }
}
