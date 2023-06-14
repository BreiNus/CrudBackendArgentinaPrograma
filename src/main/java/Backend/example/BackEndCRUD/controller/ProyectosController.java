package Backend.example.BackEndCRUD.controller;

import Backend.example.BackEndCRUD.dto.Mensaje;
import Backend.example.BackEndCRUD.dto.ProyectosDto;
import Backend.example.BackEndCRUD.dto.SkillsDto;
import Backend.example.BackEndCRUD.entity.Proyectos;
import Backend.example.BackEndCRUD.entity.Skills;
import Backend.example.BackEndCRUD.repository.ProyectosRepository;
import Backend.example.BackEndCRUD.service.ProyectosService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "https://mi-portfolio-web-6e5bd.web.app")
public class ProyectosController {

    @Autowired
    public ProyectosService proyectosService;

    @GetMapping("/ver")
    public ResponseEntity<List<Proyectos>> list() {
        List<Proyectos> list = proyectosService.verProyectos();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Proyectos> getById(@PathVariable("id") long id) {
        if (!proyectosService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe proyecto con ese ID"), HttpStatus.NOT_FOUND);
        }
        Proyectos proyecto = proyectosService.buscarProyectos(id);
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ProyectosDto proyectosDto) {

        if (StringUtils.isBlank(proyectosDto.getNombreProyecto())) {
            return new ResponseEntity(new Mensaje("El nombre del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (proyectosService.existByNombreProyecto(proyectosDto.getNombreProyecto())) {
            return new ResponseEntity(new Mensaje("Ya existe ese nombre"), HttpStatus.BAD_REQUEST);
        }
        Proyectos proyecto = new Proyectos(proyectosDto.getNombreProyecto(),proyectosDto.getUrlRepo(),proyectosDto.getImgProyecto(),proyectosDto.getDescripcionProyecto());
        proyectosService.crearProyectos(proyecto);
        return new ResponseEntity(new Mensaje("El nuevo proyecto ha sido creada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProyectosDto proyectosDto) {
        if (!proyectosService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe un proyecto con ese ID"), HttpStatus.NOT_FOUND);
        }
        if (proyectosService.existByNombreProyecto(proyectosDto.getNombreProyecto()) && proyectosService.getByNombreProyecto(proyectosDto.getNombreProyecto()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe un proyecto con ese nombre"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(proyectosDto.getNombreProyecto())){
            return new ResponseEntity(new Mensaje("El nombre del proyecto es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Proyectos proyecto = proyectosService.buscarProyectos(id);
        proyecto.setNombreProyecto(proyectosDto.getNombreProyecto());
        proyecto.setUrlRepo(proyectosDto.getUrlRepo());
        proyecto.setImgProyecto(proyectosDto.getImgProyecto());
        proyecto.setDescripcionProyecto(proyectosDto.getDescripcionProyecto());
        proyectosService.crearProyectos(proyecto);
        return new ResponseEntity(new Mensaje("El proyecto ha sido actualizad"), HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!proyectosService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe un proyecto con ese ID"), HttpStatus.NOT_FOUND);
        }
        proyectosService.borrarProyecto(id);
        return new ResponseEntity(new Mensaje("proyecto eliminado"), HttpStatus.OK);
    }
}
