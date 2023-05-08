package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.entity.ExpAcademica;
import java.util.List;
import java.util.Optional;


public interface IExpAcademicaService {
    
    //metodo para traer todas las experiencias academicas
    public List<ExpAcademica> verExpAcademica();

    //metodo para crear una experiencia academica
    public void crearExpAcademica(ExpAcademica ExpAca);

    //metodo para borrar una experiencia academica
    public void borrarExpAcademica(Long id);

    //metodo para encontrar una experiencia academica por el id
    public ExpAcademica buscarExpAcademica(Long id);

    //metodo para encontrar una experiencia academica por el nombre
    public Optional<ExpAcademica> getByNombreExpAcademica(String nombreExpAcademica);

    //metodo si existe experiencia academica por id
    public boolean existById(Long id);

    //metodo si existe experiencia academica por nombre
    public boolean existByNombreExpAcademica(String nombreExpAcademica);
    
}
