package Backend.example.BackEndCRUD.controller;

import Backend.example.BackEndCRUD.dto.Mensaje;
import Backend.example.BackEndCRUD.dto.PersonaDto;
import Backend.example.BackEndCRUD.entity.Persona;
import Backend.example.BackEndCRUD.service.PersonaService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
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
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    private PersonaService personaService;

    //esta annotacion indica que solo el user con rol admin puede hacer uso de este metodo
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody PersonaDto personaDto) {

        if (StringUtils.isBlank(personaDto.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personaDto.getApellido())) {
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personaDto.getTelefono())) {
            return new ResponseEntity(new Mensaje("El telefono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personaDto.getEmail())) {
            return new ResponseEntity(new Mensaje("El email es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personaDto.getFechaNacimiento())) {
            return new ResponseEntity(new Mensaje("La fecha de nacimiento es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(personaDto.getSobreMi())) {
            return new ResponseEntity(new Mensaje("Se debe agregar una breve descripcion de la persona"), HttpStatus.BAD_REQUEST);
        }
        Persona persona = new Persona(personaDto.getNombre(), personaDto.getApellido(), personaDto.getEmail(), personaDto.getFechaNacimiento(), personaDto.getTelefono(), personaDto.getLocalidad(), personaDto.getSobreMi());
        personaService.crearPersona(persona);
        return new ResponseEntity(new Mensaje("Persona creada"), HttpStatus.OK);
        /*
    public String agregarPersona(@RequestBody Persona pers) {
        persoServ.crearPersona(pers);
        //devuelve un string avisando si se creo correctamente
        return "La persona fue creada correctamente";
         */
    }

    @GetMapping("/ver")
    public ResponseEntity<List<Persona>> verPersonas() {
        List<Persona> list = personaService.verPersonas();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //esta annotacion indica que solo el user con rol admin puede hacer uso de este metodo
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrar(@PathVariable("id") Long id) {
        if (!personaService.existsById(id)) {
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

    //esta annotacion indica que solo el user con rol admin puede hacer uso de este metodo
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody PersonaDto personaDto) {
        if (!personaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe persona con ese ID"), HttpStatus.NOT_FOUND);
        }
        if(StringUtils.isBlank(personaDto.getNombre())){
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personaDto.getApellido())){
            return new ResponseEntity(new Mensaje("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personaDto.getEmail())){
            return new ResponseEntity(new Mensaje("El email es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personaDto.getFechaNacimiento())){
            return new ResponseEntity(new Mensaje("La fecha de nacimiento es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personaDto.getTelefono())){
            return new ResponseEntity(new Mensaje("El numero de telefono es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personaDto.getLocalidad())){
            return new ResponseEntity(new Mensaje("La localidad es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(personaDto.getSobreMi())){
            return new ResponseEntity(new Mensaje("La descripcion breve de la persona es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.buscarPersona(id);
        persona.setNombre(personaDto.getNombre());
        persona.setApellido(personaDto.getApellido());
        persona.setTelefono(personaDto.getTelefono());
        persona.setEmail(personaDto.getEmail());
        persona.setLocalidad(personaDto.getLocalidad());
        persona.setSobreMi(personaDto.getSobreMi());
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
}
