package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.entity.ExpLaboral;
import Backend.example.BackEndCRUD.repository.ExpLaboralRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ExpLaboralService implements IExpLaboralService {

    @Autowired
    ExpLaboralRepository expLabRepo;

    @Override
    public List<ExpLaboral> verExpLaboral() {
        return expLabRepo.findAll();
    }

    @Override
    public void crearExpLaboral(ExpLaboral ExpLab) {
        expLabRepo.save(ExpLab);
    }

    @Override
    public void borrarExpLaboral(Long id) {
        expLabRepo.deleteById(id);
    }

    @Override
    public ExpLaboral buscarExpLaboral(Long id) {
        //aca si no encuentro la experiencia laboral, devuelvo un null, esa es la funcion que cumple el orElse
        return expLabRepo.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return expLabRepo.existsById(id);
    }

}
