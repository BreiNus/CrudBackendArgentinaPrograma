package Backend.example.BackEndCRUD.controller;

import Backend.example.BackEndCRUD.dto.ExpAcademicaDto;
import Backend.example.BackEndCRUD.dto.Mensaje;
import Backend.example.BackEndCRUD.dto.PersonaDto;
import Backend.example.BackEndCRUD.entity.ExpAcademica;
import Backend.example.BackEndCRUD.entity.Persona;
import Backend.example.BackEndCRUD.service.ExpAcademicaService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
@CrossOrigin(origins = "*")
public class ExpAcademicaController {


    @Autowired
    private ExpAcademicaService expAcademicaService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ExpAcademicaDto expAcademicaDto) {

        if (StringUtils.isBlank(expAcademicaDto.getNivel())) {
            return new ResponseEntity(new Mensaje("El nivel educativo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expAcademicaDto.getLugar())) {
            return new ResponseEntity(new Mensaje("El lugar de cursado es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expAcademicaDto.getTitulo())) {
            return new ResponseEntity(new Mensaje("El nombre del titulo/certificado es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expAcademicaDto.getInicioEstudio())) {
            return new ResponseEntity(new Mensaje("La fecha de inicio del cursado es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expAcademicaDto.getFinEstudio())) {
            return new ResponseEntity(new Mensaje("La fecha de fin del cursado es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        ExpAcademica expAcademica = new ExpAcademica(expAcademicaDto.getNivel(), expAcademicaDto.getLugar(), expAcademicaDto.getTitulo(), expAcademicaDto.getInicioEstudio(), expAcademicaDto.getFinEstudio());
        expAcademicaService.crearExpAcademica(expAcademica);
        return new ResponseEntity(new Mensaje("La Experiencia academica ha sido creada"), HttpStatus.OK);

    }

    @GetMapping("/ver")
    public ResponseEntity<List<ExpAcademica>> verExpAcademica() {
        List<ExpAcademica> list = expAcademicaService.verExpAcademica();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
        if (!expAcademicaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe experiencia academica con ese ID"), HttpStatus.NOT_FOUND);
        }
        expAcademicaService.borrarExpAcademica(id);
        return new ResponseEntity(new Mensaje("Experiencia academica eliminada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody ExpAcademicaDto expAcademicaDto) {
        if (!expAcademicaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe una experiencia academica con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(expAcademicaDto.getNivel())) {
            return new ResponseEntity(new Mensaje("El nivel educativo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expAcademicaDto.getLugar())) {
            return new ResponseEntity(new Mensaje("El lugar de cursado es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expAcademicaDto.getTitulo())) {
            return new ResponseEntity(new Mensaje("El nombre del titulo/certificado es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expAcademicaDto.getInicioEstudio())) {
            return new ResponseEntity(new Mensaje("La fecha de inicio del cursado es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expAcademicaDto.getFinEstudio())) {
            return new ResponseEntity(new Mensaje("La fecha de fin del cursado es obligatoria"), HttpStatus.BAD_REQUEST);
        }

       ExpAcademica expAcademica = expAcademicaService.buscarExpAcademica(id);
        expAcademica.setNivel(expAcademicaDto.getNivel());
        expAcademica.setTitulo(expAcademicaDto.getTitulo());
        expAcademica.setLugar(expAcademicaDto.getLugar());
        expAcademica.setInicioEstudio(expAcademicaDto.getInicioEstudio());
        expAcademica.setFinEstudio(expAcademicaDto.getInicioEstudio());
        expAcademicaService.crearExpAcademica(expAcademica);
        return new ResponseEntity(new Mensaje("La experiencia academica ha sido actualizada"), HttpStatus.OK);

    }





}