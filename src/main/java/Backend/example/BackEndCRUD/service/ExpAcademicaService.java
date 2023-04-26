package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.entity.ExpAcademica;
import Backend.example.BackEndCRUD.repository.ExpAcademicaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExpAcademicaService implements IExpAcademicaService {

    @Autowired
    ExpAcademicaRepository expAcaRepo;

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
    public boolean existsById(Long id) {
        return expAcaRepo.existsById(id);
    }

}
