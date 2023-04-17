package Backend.example.BackEndCRUD.security.repository;

import Backend.example.BackEndCRUD.security.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    //obtener un usuario a partir del nombreUsuario
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    //obtener un usuario a partir del nombreUsuario o email
    Optional<Usuario> findByNombreUsuarioOrEmail(String nombreUsuario, String email);
    //y como el nombreUsuario y Email son unicos pondremos dos booleans para saber si exiten o no por nombreUsuario
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);

}
