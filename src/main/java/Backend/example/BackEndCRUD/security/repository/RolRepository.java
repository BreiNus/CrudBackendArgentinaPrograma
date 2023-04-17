package Backend.example.BackEndCRUD.security.repository;

import Backend.example.BackEndCRUD.security.enums.RolNombre;
import Backend.example.BackEndCRUD.security.model.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Integer>{
        //dentro vamos a tener 1 metodo
    
        //obtener un rol a partir del RolNombre
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
