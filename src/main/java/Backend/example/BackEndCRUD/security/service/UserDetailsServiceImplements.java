package Backend.example.BackEndCRUD.security.service;

import Backend.example.BackEndCRUD.security.model.Usuario;
import Backend.example.BackEndCRUD.security.model.UsuarioPrincipal;
import Backend.example.BackEndCRUD.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplements implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nombreOrEmail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuarioOrEmail(nombreOrEmail, nombreOrEmail).get();
        return UsuarioPrincipal.build(usuario);
    }
}
