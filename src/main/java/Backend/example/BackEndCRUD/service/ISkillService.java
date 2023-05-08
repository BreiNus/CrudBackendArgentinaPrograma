package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.dto.ExpAcademicaDto;
import Backend.example.BackEndCRUD.dto.ExpLaboralDto;
import Backend.example.BackEndCRUD.entity.Persona;
import Backend.example.BackEndCRUD.entity.Skills;

import java.util.List;
import java.util.Optional;

public interface ISkillService {

    //metodo para traer todas las Skills
    public List<Skills> verSkills();

    //metodo para dar de alta una Skill
    public void crearSkills(Skills skill);

    //metodo para borrar una Skill
    public void borrarSkills(Long id);

    //metodo para encontrar una Skill por id
    public Skills buscarSkills(Long id);

    //metodo si existe Skill por id
    public boolean existById(Long id);

    //metodo para encontrar uns skill por si nombre
    public Optional<Skills> getByNombreSkill(String nombreSkill);

    //metodo para comprar si existe por nombre de Skill
    public boolean existByNombreSkill(String nombreSkill);


}
