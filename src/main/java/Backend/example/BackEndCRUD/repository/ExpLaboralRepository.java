package Backend.example.BackEndCRUD.repository;

import Backend.example.BackEndCRUD.entity.ExpLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //mapeamos como repositorio
//la interface extiende de JpaRepository (que menera repositorios JPA)
//en los parametros <> deben ir: <clase a persistir, tipo de dato de su id>
public interface ExpLaboralRepository extends JpaRepository<ExpLaboral, Long>{
    public Optional<ExpLaboral> findByNombreExpLaboral(String nombreExpLaboral);
    public boolean existsByNombreExpLaboral(String nombreExpLaboral);
}
