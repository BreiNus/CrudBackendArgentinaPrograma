package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.dto.ExpAcademicaDto;
import Backend.example.BackEndCRUD.dto.ExpLaboralDto;
import Backend.example.BackEndCRUD.entity.Persona;
import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    //metodo para traer todas las personas
    public List<Persona> verPersonas();

    //metodo para dar de alta una persona
    public void crearPersona(Persona per);

    //metodo para borrar una persona
    public void borrarPersona(Long id);

    //metodo para encontrar una persona por id
    public Persona buscarPersona(Long id);

    //metodo para encontrar una persona por nombre
    public Optional<Persona> getByNombrePersona(String nombrePersona);

    //metodo si existe persona por id
    public boolean existById(Long id);

    //metodo si existe persona por nombre
    public boolean existByNombrePersona(String nombrePersona);




    //metodo para agregar exp Laboral
    public void agregarExpLaboral(Long id , ExpLaboralDto expLaboralDto);

    //metodo para agregar exp Academica
    public void agregarExpAcademica(Long id , ExpAcademicaDto expAcademicaDto);


}
