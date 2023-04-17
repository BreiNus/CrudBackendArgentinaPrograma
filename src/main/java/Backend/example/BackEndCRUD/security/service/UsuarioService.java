package Backend.example.BackEndCRUD.security.service;

import Backend.example.BackEndCRUD.security.model.Usuario;
import Backend.example.BackEndCRUD.security.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@Transactional es para mantener la coerencia en la DB, si hay varios accesos y uno escribe una tabla que no la escribe 
//al mismo tiempo puede generar una incoherencia, si falla una operacion se hace un roll back y se vuelve al estado anterior 
@Transactional
public class UsuarioService {
    //dentro habran 4 metodos
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    
    //metodo para guardar
    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }
}
