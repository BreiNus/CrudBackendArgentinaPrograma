package Backend.example.BackEndCRUD.repository;

import Backend.example.BackEndCRUD.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //mapeamos como repositorio
//la interface extiende de JpaRepository (que menera repositorios JPA)
//en los parametros <> deben ir: <clase a persistir, tipo de dato de su id>
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
