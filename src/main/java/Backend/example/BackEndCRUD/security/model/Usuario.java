package Backend.example.BackEndCRUD.security.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
//clase encargada de acceder a la DB, datos, tablas, etc
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nombre;

    @NotNull
    //el nombre de usuario va a ser unico
    @Column(unique = true)
    private String nombreUsuario;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    //un usuario puede tener muchos roles y un rol puede perteneser muchos usuarios, para implementar esto se crea una tabla 
    //intermedia que va a contener dos campos, uno va a ser el id usuario y el otro id rol, para relacionar cada rol con cada usuario 
    // el (fetch = FetchType.EAGER) quiere decir que yo al indexar un usuario y pasar los datos, sea mas eficiencia y que no cargue muchos datos
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    public Usuario(String nombre, String nombreUsuario, String email, String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

}
