/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.example.BackEndCRUD.controller;

import Backend.example.BackEndCRUD.dto.ExpLaboralDto;
import Backend.example.BackEndCRUD.dto.Mensaje;
import Backend.example.BackEndCRUD.entity.ExpLaboral;
import Backend.example.BackEndCRUD.service.ExpLaboralService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajos")
@CrossOrigin(origins = "http://mi-portfolio-web-6e5bd.web.app")
public class ExpLaboralController {

    @Autowired
    public ExpLaboralService expLaboralService;


    @GetMapping("/ver")
    public ResponseEntity<List<ExpLaboral>> list() {
        List<ExpLaboral> list = expLaboralService.verExpLaboral();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<ExpLaboral> getById(@PathVariable("id") long id) {
        if (!expLaboralService.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe experiencia laboral con ese ID"), HttpStatus.NOT_FOUND);
        }
        ExpLaboral expLaboral = expLaboralService.buscarExpLaboral(id);
        return new ResponseEntity(expLaboral, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ExpLaboralDto expLaboralDto) {

        if (StringUtils.isBlank(expLaboralDto.getNombreExpLaboral())) {
            return new ResponseEntity(new Mensaje("El nombre del trabajo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (expLaboralService.existByNombreExpLaboral(expLaboralDto.getNombreExpLaboral())) {
            return new ResponseEntity(new Mensaje("ya existe un trabajo con ese nombre"), HttpStatus.BAD_REQUEST);
        }

        ExpLaboral expLaboral = new ExpLaboral(expLaboralDto.getNombreExpLaboral(), expLaboralDto.getNombreCompania(), expLaboralDto.getInicioTrabajo(), expLaboralDto.getFinTrabajo(), expLaboralDto.getDescripcionTrabajo());
        expLaboralService.crearExpLaboral(expLaboral);
        return new ResponseEntity(new Mensaje("La Experiencia laboral ha sido creada"), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ExpLaboralDto expLaboralDto) {
        if (!expLaboralService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe una experiencia laboral con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (expLaboralService.existByNombreExpLaboral(expLaboralDto.getNombreExpLaboral()) && expLaboralService.getByNombreExpLaboral(expLaboralDto.getNombreExpLaboral()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa experienci laboral ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expLaboralDto.getNombreExpLaboral())) {
            return new ResponseEntity(new Mensaje("El nombre del trabajo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

            ExpLaboral expLaboral = expLaboralService.buscarExpLaboral(id);
            expLaboral.setNombreExpLaboral(expLaboralDto.getNombreExpLaboral());
            expLaboral.setNombreCompania(expLaboralDto.getNombreCompania());
            expLaboral.setInicioTrabajo(expLaboralDto.getInicioTrabajo());
            expLaboral.setFinTrabajo(expLaboralDto.getFinTrabajo());
            expLaboral.setDescripcionTrabajo(expLaboralDto.getDescripcionTrabajo());
            expLaboralService.crearExpLaboral(expLaboral);
            return new ResponseEntity(new Mensaje("La experiencia laboral ha sido actualizada"), HttpStatus.OK);
    }
        @PreAuthorize("hasRole('ADMIN')")
        @DeleteMapping("/borrar/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            if (!expLaboralService.existById(id)) {
                return new ResponseEntity(new Mensaje("No existe experiencia laboral con ese ID"), HttpStatus.NOT_FOUND);
            }
            expLaboralService.borrarExpLaboral(id);
            return new ResponseEntity(new Mensaje("Experiencia academica eliminada"), HttpStatus.OK);
        }
}
