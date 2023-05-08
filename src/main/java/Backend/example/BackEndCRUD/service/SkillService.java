package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.entity.Skills;
import Backend.example.BackEndCRUD.repository.SkillsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkillService implements ISkillService{

    @Autowired
    public SkillsRepository skillsRepository;

    @Override
    public List<Skills> verSkills() {
        return skillsRepository.findAll();
    }

    @Override
    public void crearSkills(Skills skill) {
        skillsRepository.save(skill);

    }

    @Override
    public void borrarSkills(Long id) {
        skillsRepository.deleteById(id);

    }

    @Override
    public Skills buscarSkills(Long id) {
        return skillsRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existById(Long id) {
        return skillsRepository.existsById(id);
    }

    @Override
    public Optional<Skills> getByNombreSkill(String nombreSkill) {
        return skillsRepository.findByNombreSkill(nombreSkill);
    }

    @Override
    public boolean existByNombreSkill(String nombreSkill) {
        return skillsRepository.existsByNombreSkill(nombreSkill);
    }
}
