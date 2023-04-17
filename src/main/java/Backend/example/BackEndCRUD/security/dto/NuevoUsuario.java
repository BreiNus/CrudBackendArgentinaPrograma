package Backend.example.BackEndCRUD.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class NuevoUsuario {

    //Esta annotacion quiere decir que no puede el valor no puede ser null, o una String vacia de longitud 0, ni uno o mas espacios en blanco
    @NotBlank
    private String nombre;
    @NotBlank
    private String nombreUsuario;
    @Email
    private String email;
    @NotBlank
    private String password;
    //es es porque al utilizar una API REST vamos a usar objetos tipos JSON y por eso es mejor que Set<> 
    //sea de tipo String, se trabaja en JSON basicamente para agilizar el pasaje de informacion
    //al la hora de usar postman para pasar los parametros en crudo hay que acordarse que el Set<> es un Array, asique el valor va entre corchetes [], ejemplo; "roles":["admin"]
    private Set<String> roles = new HashSet<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

}
