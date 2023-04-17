package Backend.example.BackEndCRUD.security.service;

import Backend.example.BackEndCRUD.security.enums.RolNombre;
import Backend.example.BackEndCRUD.security.model.Rol;
import Backend.example.BackEndCRUD.security.repository.RolRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolService {
    //dentro habra un solo metodo

    @Autowired
    RolRepository rolRepository;

    //encontrar uno rol por rolNombre
    public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
        return rolRepository.findByRolNombre(rolNombre);
    }

    //metodo save para guardar un rol
    public void save(Rol rol) {
        rolRepository.save(rol);
    }

}
