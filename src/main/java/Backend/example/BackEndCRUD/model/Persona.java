package Backend.example.BackEndCRUD.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String fechaNacimiento;
    private String telefono;
    private String localidad;
    private String sobreMi;
    
    //creo las relacions que tiene persona con exp laboral y academica
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "persona_id")
    private List<ExpLaboral> listadoExpLabolares;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "persona_id")
    private List<ExpAcademica> listadoExpAcademica;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String email, String fechaNacimiento, String telefono, String localidad, String sobreMi) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.localidad = localidad;
        this.sobreMi = sobreMi;
    }

}
