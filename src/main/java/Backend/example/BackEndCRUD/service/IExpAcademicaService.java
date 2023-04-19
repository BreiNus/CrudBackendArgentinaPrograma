package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.model.ExpAcademica;
import java.util.List;


public interface IExpAcademicaService {
    
        //metodo para traer todas las experiencias academicas
    public List<ExpAcademica> verExpAcademica();

    //metodo para crear una experiencia academica
    public void crearExpAcademica(ExpAcademica ExpAca);

    //metodo para borrar una experiencia academica
    public void borrarExpAcademica(Long id);

    //metodo para encontrar una experiencia academica
    public ExpAcademica buscarExpAcademica(Long id);
    
}
