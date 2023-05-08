package Backend.example.BackEndCRUD.security.controller;

import Backend.example.BackEndCRUD.dto.Mensaje;
import Backend.example.BackEndCRUD.security.dto.JwtDto;
import Backend.example.BackEndCRUD.security.dto.LoginUsuario;
import Backend.example.BackEndCRUD.security.dto.NuevoUsuario;
import Backend.example.BackEndCRUD.security.enums.RolNombre;
import Backend.example.BackEndCRUD.security.jwt.JwtProvider;
import Backend.example.BackEndCRUD.security.model.Rol;
import Backend.example.BackEndCRUD.security.model.Usuario;
import Backend.example.BackEndCRUD.security.service.RolService;
import Backend.example.BackEndCRUD.security.service.UsuarioService;
import jakarta.validation.Valid;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
//si dejamos esta annotacion como viene por default significa que nosotros podemos acceder desde cualquier URL a esta 
@CrossOrigin
public class AuthController {
    //dentro tendra tres metodos 

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    // metodo crear usuario y asignar los roles
    // la primer parte del parametro es cuando se ejecuta el metodo tansforma el JSON que es lo que espera, a una clase JAVA tipo nuevoUsuario. Lo de BindingResult es para validarlo
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        //en caso de que bindingResult no tenga errores
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
        
        //en caso contrario, comprobamos que el nombre no exista, porque el nombreUsuario es unico
         if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        
        //tambien comprovamos que no exista el email, porque el email es unico
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
            //si esta todo bien creamos un usuario
        
        Usuario usuario =
                 new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        //ahora le asignamos los roles, todos los roles por defecto son de tipo String
        Set<Rol> roles = new HashSet<>();
        //como esto "roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER)" nos devuelve un opcional lo convertimos con el .get() a un objeto tipo rol
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        //comprobamos que sea administrador el nuevoUsuario
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        
        //finalmente estos roles se los asignamos al usuario
        usuario.setRoles(roles);
        //guardamos el usuario
        usuarioService.save(usuario);
        //devolvemos el ResponseEntity
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    //metodo login
    //<JwtDto> quiere decir que va a devolver un token
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        //en caso de que bindingResult no tenga errores
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        
        //parte de la autorizacion
        Authentication authentication =
                 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        //aca atutenticamos el usuario con el login y es donde ponemos en marcha el JwtTokenFilter
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //a partir de la autorizacion generamos el token
        String jwt = jwtProvider.generateToken(authentication);
        //creamos el jwtDto
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
    
    @PostMapping("/refresh")
    //endpoint para hacer el refresh del token
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }
}
