package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.dto.ExpAcademicaDto;
import Backend.example.BackEndCRUD.dto.ExpLaboralDto;
import Backend.example.BackEndCRUD.entity.Persona;
import java.util.List;

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

    //metodo para agregar exp Laboral
    public void agregarExpLaboral(Long id , ExpLaboralDto expLaboralDto);


    //metodo para agregar exp Academica
    public void agregarExpAcademica(Long id , ExpAcademicaDto expAcademicaDto);


}
