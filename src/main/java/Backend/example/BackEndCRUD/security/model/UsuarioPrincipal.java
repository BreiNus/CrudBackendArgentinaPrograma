package Backend.example.BackEndCRUD.security.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//esta clase va a ser la encargada de generar la seguridad, es decir va a implementar lo que es el tema de los privilegios de cada usuario
//obtiene la autorizacion del usuario para hacer o no hacer cosas
public class UsuarioPrincipal implements UserDetails {

    private String nombre;
    private String nombreUsuario;
    private String email;
    private String password;
    //en una coleccion al poner <? > (el signo de pregunta) quiere decir que sera una coleccion generica 
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(String nombre, String nombreUsuario, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    //Con este metodo estatico llamado build, es el que va a asignar los pribilegios a cada usuario, es decir su autorizacion, si es un user comun o un adc
    //Como parametro le mandamos un Usuario, lo que hace es convertir un usuario que representa a la entidad de la DB en un UsuarioPrincipal para saber los pribilegios de cada Usuario   
    public static UsuarioPrincipal build(Usuario usuario) {

        //primero obtener los roles
        List<GrantedAuthority> authorities
                = //entonces obtenemos una lista de  GrantedAuthorities a partir de los roles, estamos convirtiendo la clase Rol en clase GrantedAuthority  (esto -> significa que es una funcion lamda)
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                .getRolNombre().name())).collect(Collectors.toList());
        //por ultimo devolvemos un nuevo UsuarioPrincipal
        return new UsuarioPrincipal(usuario.getNombre(), usuario.getNombreUsuario(), usuario.getEmail(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
