package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.model.Persona;
import Backend.example.BackEndCRUD.repository.PersonaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService implements IPersonaService {

    @Autowired
    public PersonaRepository persoRepo;

    @Override
    public List<Persona> verPersonas() {
        return persoRepo.findAll();
    }

    @Override
    public void crearPersona(Persona per) {
        persoRepo.save(per);
    }

    @Override
    public void borrarPersona(Long id) {
        persoRepo.deleteById(id);
    }

    @Override
    public Persona buscarPersona(Long id) {
        //aca si no encuentro la persona, devuelvo un null, esa es la funcion que cumple el orElse
        return persoRepo.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return persoRepo.existsById(id);
    }

}
