package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.entity.ExpLaboral;
import java.util.List;
import java.util.Optional;


public interface IExpLaboralService {
    
    //metodo para traer todas las experiencias laborales
    public List<ExpLaboral> verExpLaboral();

    //metodo para crear una experiencia laboral
    public void crearExpLaboral(ExpLaboral ExpLab);

    //metodo para borrar una experiencia laboral
    public void borrarExpLaboral(Long id);

    //metodo para encontrar una experiencia laboral por id
    public ExpLaboral buscarExpLaboral(Long id);

    //metodo para encontrar una experiencia laboral por nombre
    public Optional<ExpLaboral> getByNombreExpLaboral(String nombreExpLaboral);

    //metodo si existe experiencia laboral por id
    public boolean existById(Long id);

    //metodo si existe experiencia laboral por nombre
    public boolean existByNombreExpLaboral(String nombreExpLaboral);
}
