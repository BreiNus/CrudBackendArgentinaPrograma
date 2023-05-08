package Backend.example.BackEndCRUD.repository;

import Backend.example.BackEndCRUD.entity.Proyectos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //mapeamos como repositorio
//la interface extiende de JpaRepository (que maneja repositorios JPA)
//en los parametros <> deben ir: <clase a persistir, tipo de dato de su id>
public interface ProyectosRepository extends JpaRepository<Proyectos, Long> {
    public Optional<Proyectos> findByNombreProyecto(String nombreProyecto);
    public boolean existsByNombreProyecto(String nombreProyecto);
}
