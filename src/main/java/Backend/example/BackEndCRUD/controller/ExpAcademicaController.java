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
@CrossOrigin(origins = "http://mi-portfolio-web-6e5bd.web.app")
public class ExpAcademicaController {


    @Autowired
    public ExpAcademicaService expAcademicaService;


    @GetMapping("/ver")
    public ResponseEntity<List<ExpAcademica>> list() {
        List<ExpAcademica> list = expAcademicaService.verExpAcademica();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<ExpAcademica> getById(@PathVariable("id") long id) {
        if (!expAcademicaService.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        }

        ExpAcademica expAcademica =expAcademicaService.buscarExpAcademica(id);

        return new ResponseEntity(expAcademica, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ExpAcademicaDto expAcademicaDto) {

        if (StringUtils.isBlank(expAcademicaDto.getNombreExpAcademica())) {
            return new ResponseEntity(new Mensaje("El Titulo/Nombre del estudio es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        ExpAcademica expAcademica = new ExpAcademica(expAcademicaDto.getNombreExpAcademica(),expAcademicaDto.getDescripcionExpAcademica(),expAcademicaDto.getNivel(),expAcademicaDto.getLugar(), expAcademicaDto.getInicioEstudio(), expAcademicaDto.getFinEstudio());
        expAcademicaService.crearExpAcademica(expAcademica);
        return new ResponseEntity(new Mensaje("La Experiencia academica ha sido creada"), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ExpAcademicaDto expAcademicaDto) {
        if (!expAcademicaService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe una experiencia academica con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (expAcademicaService.existByNombreExpAcademica(expAcademicaDto.getNombreExpAcademica()) && expAcademicaService.getByNombreExpAcademica(expAcademicaDto.getNombreExpAcademica()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa experiencia academica ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expAcademicaDto.getNombreExpAcademica())) {
            return new ResponseEntity(new Mensaje("El nombre de la Experiencia academica es obligatorio"), HttpStatus.BAD_REQUEST);
        }


        ExpAcademica expAcademica = expAcademicaService.buscarExpAcademica(id);
        expAcademica.setNombreExpAcademica(expAcademicaDto.getNombreExpAcademica());
        expAcademica.setDescripcionExpAcademica(expAcademicaDto.getDescripcionExpAcademica());
        expAcademica.setNivel(expAcademicaDto.getNivel());
        expAcademica.setLugar(expAcademicaDto.getLugar());
        expAcademica.setInicioEstudio(expAcademicaDto.getInicioEstudio());
        expAcademica.setFinEstudio(expAcademicaDto.getInicioEstudio());
        expAcademicaService.crearExpAcademica(expAcademica);
        return new ResponseEntity(new Mensaje("La experiencia academica ha sido actualizada"), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!expAcademicaService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe experiencia academica con ese ID"), HttpStatus.NOT_FOUND);
        }
        expAcademicaService.borrarExpAcademica(id);
        return new ResponseEntity(new Mensaje("Experiencia academica eliminada"), HttpStatus.OK);
    }

}