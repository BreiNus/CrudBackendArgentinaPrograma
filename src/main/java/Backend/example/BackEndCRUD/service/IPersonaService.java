package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.model.Persona;
import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    //metodo para traer todas las personas
    public List<Persona> verPersonas();

    //metodo para dar de alta una persona
    public void crearPersona(Persona per);

    //metodo para borrar una persona
    public void borrarPersona(Long id);

    //metodo para encontrar una persona
    public Persona buscarPersona(Long id);

    //metodo si existe persona por id
    public boolean existsById(Long id);

}
