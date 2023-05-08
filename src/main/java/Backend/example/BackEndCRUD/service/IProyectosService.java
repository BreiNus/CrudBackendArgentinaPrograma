package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.entity.Proyectos;

import java.util.List;
import java.util.Optional;

public interface IProyectosService {
    //metodo para traer todos los proyectos
    public List<Proyectos> verProyectos();

    //metodo para dar de alta un proyecto
    public void crearProyectos(Proyectos proyectos);

    //metodo para borrar un proyecto
    public void borrarProyecto(Long id);

    //metodo para encontrar un proyecto por id
    public Proyectos buscarProyectos(Long id);

    //metodo si existe proyecto por id
    public boolean existById(Long id);

    //metodo para encontrar un proyecto por su nombre
    public Optional<Proyectos> getByNombreProyecto(String nombreProyecto);

    //metodo para comprar si existe por nombre de proyecto
    public boolean existByNombreProyecto(String nombreProyecto);
}
