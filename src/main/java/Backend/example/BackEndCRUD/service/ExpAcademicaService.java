package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.entity.ExpAcademica;
import Backend.example.BackEndCRUD.repository.ExpAcademicaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExpAcademicaService implements IExpAcademicaService {

    @Autowired
    public ExpAcademicaRepository expAcaRepo;

    @Override
    public List<ExpAcademica> verExpAcademica() {
        return expAcaRepo.findAll();
    }

    @Override
    public void crearExpAcademica(ExpAcademica ExpAca) {
        expAcaRepo.save(ExpAca);
    }

    @Override
    public void borrarExpAcademica(Long id) {
        expAcaRepo.deleteById(id);
    }

    @Override
    public ExpAcademica buscarExpAcademica(Long id) {
        //aca si no encuentro la experiencia academica, devuelvo un null, esa es la funcion que cumple el orElse
        return expAcaRepo.findById(id).orElse(null);
    }

    @Override
    public Optional<ExpAcademica> getByNombreExpAcademica(String nombreExpAcademica) {
        return expAcaRepo.findByNombreExpAcademica(nombreExpAcademica);
    }

    @Override
    public boolean existById(Long id) {
        return expAcaRepo.existsById(id);
    }

    @Override
    public boolean existByNombreExpAcademica(String nombreExpAcademica) {
        return expAcaRepo.existsByNombreExpAcademica(nombreExpAcademica);
    }

}
