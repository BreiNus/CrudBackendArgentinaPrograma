package Backend.example.BackEndCRUD.service;

import Backend.example.BackEndCRUD.dto.ExpAcademicaDto;
import Backend.example.BackEndCRUD.dto.ExpLaboralDto;
import Backend.example.BackEndCRUD.entity.ExpAcademica;
import Backend.example.BackEndCRUD.entity.ExpLaboral;
import Backend.example.BackEndCRUD.entity.Persona;
import Backend.example.BackEndCRUD.repository.ExpAcademicaRepository;
import Backend.example.BackEndCRUD.repository.ExpLaboralRepository;
import Backend.example.BackEndCRUD.repository.PersonaRepository;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService implements IPersonaService {

    @Autowired
    public PersonaRepository persoRepo;

    @Autowired
    public ExpLaboralRepository expLaboralRepository;

    @Autowired
    public ExpAcademicaRepository expAcademicaRepository;


    @Override
    public List<Persona> verPersonas() {
        return persoRepo.findAll();
    }

    @Override
    public void crearPersona(Persona per) {
        persoRepo.save(per);
    }

    @Override
    public void borrarPersona(Long id) {
        persoRepo.deleteById(id);
    }

    @Override
    public Persona buscarPersona(Long id) {
        //aca si no encuentro la persona, devuelvo un null, esa es la funcion que cumple el orElse
        return persoRepo.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return persoRepo.existsById(id);
    }

    @Override
    public void agregarExpLaboral(Long id , ExpLaboralDto expLaboralDto){
        Persona per = buscarPersona(id);
        if(per==null){
            throw new RuntimeException("La persona no existe por ese ID");
        }
        ExpLaboral expLaboral = new ExpLaboral(expLaboralDto.getNombreCompania(),expLaboralDto.getEsTrabajoActual(),expLaboralDto.getInicioTrabajo(),expLaboralDto.getFinTrabajo(),expLaboralDto.getSobreProyecto());
                expLaboralRepository.save(expLaboral);
    }

    @Override
    public void agregarExpAcademica(Long id, ExpAcademicaDto expAcademicaDto) {
        Persona per = buscarPersona(id);
        if(per==null){
            throw new RuntimeException("La persona no existe por ese ID");
        }
        ExpAcademica expAcademica =new ExpAcademica(expAcademicaDto.getNivel(),expAcademicaDto.getLugar(),expAcademicaDto.getTitulo(),expAcademicaDto.getInicioEstudio(),expAcademicaDto.getFinEstudio());
        expAcademicaRepository.save(expAcademica);
    }


}
