package Backend.example.BackEndCRUD.controller;

import Backend.example.BackEndCRUD.dto.Mensaje;
import Backend.example.BackEndCRUD.dto.PersonaDto;
import Backend.example.BackEndCRUD.entity.Persona;
import Backend.example.BackEndCRUD.service.PersonaService;
import io.micrometer.common.util.StringUtils;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "https://mi-portfolio-web-6e5bd.web.app")
public class Controller {

    @Autowired
    public PersonaService personaService;
    /*
    @GetMapping("/ver")
    public ResponseEntity<List<Persona>> verPersonas() {
        List<Persona> list = personaService.verPersonas();
        return new ResponseEntity(list, HttpStatus.OK);
    }*/
    @GetMapping("/ver-persona")
    public ResponseEntity findPersona() {
        Optional<Persona> per = Optional.ofNullable(personaService.buscarPersona(Long.valueOf(1)));
        return new ResponseEntity(per, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") long id) {
        if (!personaService.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe el id"), HttpStatus.NOT_FOUND);
        }

        Persona persona = personaService.buscarPersona(id);

        return new ResponseEntity(persona, HttpStatus.OK);
    }

    //esta annotacion indica que solo el user con rol admin puede hacer uso de este metodo
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody PersonaDto personaDto) {

        if (StringUtils.isBlank(personaDto.getNombrePersona())) {
            return new ResponseEntity(new Mensaje("El nombre completo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (personaService.existByNombrePersona(personaDto.getNombrePersona())) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = new Persona(personaDto.getNombrePersona(),personaDto.getPuestoTrabajo(),personaDto.getSobreMi(),personaDto.getLocalidad(),personaDto.getTelefono(),personaDto.getEmail(),personaDto.getGitHubPersonal());
        personaService.crearPersona(persona);
        return new ResponseEntity(new Mensaje("Persona creada"), HttpStatus.OK);
        /*
    public String agregarPersona(@RequestBody Persona pers) {
        persoServ.crearPersona(pers);
        //devuelve un string avisando si se creo correctamente
        return "La persona fue creada correctamente";
         */
    }

    //esta annotacion indica que solo el user con rol admin puede hacer uso de este metodo
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PersonaDto personaDto) {
        if (!personaService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe persona con ese ID"), HttpStatus.NOT_FOUND);
        }
        if(personaService.existByNombrePersona(personaDto.getNombrePersona()) && personaService.getByNombrePersona(personaDto.getNombrePersona()).get().getId() != id){
            return new ResponseEntity(new Mensaje("Esa persona ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personaDto.getNombrePersona())){
            return new ResponseEntity(new Mensaje("El nombre completo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.buscarPersona(id);
        persona.setNombrePersona(personaDto.getNombrePersona());
        persona.setPuestoTrabajo(personaDto.getPuestoTrabajo());
        persona.setSobreMi(personaDto.getSobreMi());
        persona.setLocalidad(personaDto.getLocalidad());
        persona.setTelefono(personaDto.getTelefono());
        persona.setEmail(personaDto.getEmail());
        persona.setGitHubPersonal(personaDto.getGitHubPersonal());
        personaService.crearPersona(persona);
        return new ResponseEntity(new Mensaje("La persona ha sido actualizada"), HttpStatus.OK);

    }
    /*
    public Persona editarPersona(@PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido) {

        //busco la persona en cuestion
        Persona pers = personaService.buscarPersona(id);

        //esto tambien puede ir en service
        //para desacoplar mejor el codigo en un nuevo metodo
        pers.setNombre(nombre);
        pers.setApellido(apellido);

        personaService.crearPersona(pers);
        //retorna la nueva persona
        return pers;
    }
     */

    //esta annotacion indica que solo el user con rol admin puede hacer uso de este metodo
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!personaService.existById(id)) {
            return new ResponseEntity(new Mensaje("No existe persona con ese ID"), HttpStatus.NOT_FOUND);
        }
        personaService.borrarPersona(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);

        /*
    public String borrarPersona(@PathVariable Long id) {
        personaService.borrarPersona(id);
        //devuelve un string avisando si se elimino correctamente
        return "La persona fue eliminada correctamente";
         */
    }


}
