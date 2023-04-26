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
@CrossOrigin(origins = "*")
public class ExpLaboralController {

    @Autowired
    ExpLaboralService expLaboralService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ExpLaboralDto expLaboralDto) {

        if (StringUtils.isBlank(expLaboralDto.getNombreCompania())) {
            return new ResponseEntity(new Mensaje("El nombre de la compania es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expLaboralDto.getEsTrabajoActual().toString())) {
            return new ResponseEntity(new Mensaje("El lugar de cursado es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expLaboralDto.getInicioTrabajo())) {
            return new ResponseEntity(new Mensaje("El fecha de inicio del trabajo es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expLaboralDto.getFinTrabajo())) {
            return new ResponseEntity(new Mensaje("La fecha de fin del trabajo es obligatioria / aclarar en caso de seguir en ese trabajo "), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expLaboralDto.getSobreProyecto())) {
            return new ResponseEntity(new Mensaje("Es obligatoria una descripcion breve del proyecto"), HttpStatus.BAD_REQUEST);
        }
        ExpLaboral expLaboral = new ExpLaboral(expLaboralDto.getNombreCompania(),expLaboralDto.getEsTrabajoActual(),expLaboralDto.getInicioTrabajo(),expLaboralDto.getFinTrabajo(),expLaboralDto.getSobreProyecto());
        expLaboralService.crearExpLaboral(expLaboral);
        return new ResponseEntity(new Mensaje("La Experiencia laboral ha sido creada"), HttpStatus.OK);

    }

    @GetMapping("/ver")
    public ResponseEntity<List<ExpLaboral>> verExpLaboral() {
        List<ExpLaboral> list = expLaboralService.verExpLaboral();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
        if (!expLaboralService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe experiencia laboral con ese ID"), HttpStatus.NOT_FOUND);
        }
        expLaboralService.borrarExpLaboral(id);
        return new ResponseEntity(new Mensaje("Experiencia academica eliminada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody ExpLaboralDto expLaboralDto) {
        if (!expLaboralService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe una experiencia laboral con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (StringUtils.isBlank(expLaboralDto.getNombreCompania())) {
            return new ResponseEntity(new Mensaje("El nombre de la compania es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expLaboralDto.getEsTrabajoActual())) {
            return new ResponseEntity(new Mensaje("Indicar si actualmente este es tu trabajo actual o no, es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expLaboralDto.getInicioTrabajo())) {
            return new ResponseEntity(new Mensaje("El fecha de inicio del trabajo es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expLaboralDto.getFinTrabajo())) {
            return new ResponseEntity(new Mensaje("La fecha de fin del trabajo es obligatioria / aclarar en caso de seguir en ese trabajo "), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(expLaboralDto.getSobreProyecto())) {
            return new ResponseEntity(new Mensaje("Es obligatoria una descripcion breve del proyecto"), HttpStatus.BAD_REQUEST);
        }

        ExpLaboral expLaboral = expLaboralService.buscarExpLaboral(id);
        expLaboral.setNombreCompania(expLaboralDto.getNombreCompania());
        expLaboral.setEsTrabajoActual(expLaboralDto.getEsTrabajoActual());
        expLaboral.setInicioTrabajo(expLaboralDto.getInicioTrabajo());
        expLaboral.setFinTrabajo(expLaboralDto.getFinTrabajo());
        expLaboral.setSobreProyecto(expLaboralDto.getSobreProyecto());
        expLaboralService.crearExpLaboral(expLaboral);
        return new ResponseEntity(new Mensaje("La experiencia laboral ha sido actualizada"), HttpStatus.OK);

    }
    
}
