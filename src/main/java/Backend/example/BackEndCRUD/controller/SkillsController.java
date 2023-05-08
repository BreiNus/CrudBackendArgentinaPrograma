package Backend.example.BackEndCRUD.controller;

import Backend.example.BackEndCRUD.dto.Mensaje;
import Backend.example.BackEndCRUD.dto.SkillsDto;
import Backend.example.BackEndCRUD.entity.Skills;
import Backend.example.BackEndCRUD.service.SkillService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "*")
public class SkillsController {

    @Autowired
    public SkillService skillService;


    @GetMapping("/ver")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = skillService.verSkills();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") long id) {
        if (!skillService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe skill con ese ID"), HttpStatus.NOT_FOUND);
        }
        Skills skill = skillService.buscarSkills(id);
        return new ResponseEntity(skill, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody SkillsDto skillsDto) {

        if (StringUtils.isBlank(skillsDto.getNombreSkill())) {
            return new ResponseEntity(new Mensaje("El nombre de la skill es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (skillService.existByNombreSkill(skillsDto.getNombreSkill())) {
            return new ResponseEntity(new Mensaje("Ya existe ese nombre"), HttpStatus.BAD_REQUEST);
        }
        Skills skill = new Skills(skillsDto.getNombreSkill(), skillsDto.getPorcentajeSkill(), skillsDto.getColorSkill());
        skillService.crearSkills(skill);
        return new ResponseEntity(new Mensaje("La nueva Skill ha sido creada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody SkillsDto skillsDto) {
        if (!skillService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe una skill con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(skillsDto.getNombreSkill())) {
            return new ResponseEntity(new Mensaje("El nombre de la skill es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (skillService.existByNombreSkill(skillsDto.getNombreSkill())&& skillService.getByNombreSkill(skillsDto.getNombreSkill()).get().getId() !=id) {
            return new ResponseEntity(new Mensaje("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }

        Skills skill = skillService.buscarSkills(id);
        skill.setNombreSkill(skillsDto.getNombreSkill());
        skill.setPorcentajeSkill(skillsDto.getPorcentajeSkill());
        skill.setColorSkill(skillsDto.getColorSkill());
        skillService.crearSkills(skill);
        return new ResponseEntity(new Mensaje("La skill ha sido actualizada"), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!skillService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe una skill con ese ID"), HttpStatus.NOT_FOUND);
        }
        skillService.borrarSkills(id);
        return new ResponseEntity(new Mensaje("Skill eliminada"), HttpStatus.OK);
    }
}