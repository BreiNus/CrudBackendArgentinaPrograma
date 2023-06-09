package Backend.example.BackEndCRUD.repository;

import Backend.example.BackEndCRUD.entity.ExpAcademica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //mapeamos como repositorio
//la interface extiende de JpaRepository (que menera repositorios JPA)
//en los parametros <> deben ir: <clase a persistir, tipo de dato de su id>
public interface ExpAcademicaRepository extends JpaRepository<ExpAcademica, Long>{
    public Optional<ExpAcademica> findByNombreExpAcademica(String nombreExpAcademica);
    public boolean existsByNombreExpAcademica(String nombreExpAcademica);
}
