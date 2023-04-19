package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.model.ExpLaboral;
import java.util.List;


public interface IExpLaboralService {
    
        //metodo para traer todas las experiencias laborales
    public List<ExpLaboral> verExpLaboral();

    //metodo para crear una experiencia laboral
    public void crearExpLaboral(ExpLaboral ExpLab);

    //metodo para borrar una experiencia laboral
    public void borrarExpLaboral(Long id);

    //metodo para encontrar una experiencia laboral
    public ExpLaboral buscarExpLaboral(Long id);
    
}
