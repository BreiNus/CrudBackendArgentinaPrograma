package Backend.example.BackEndCRUD.entity;

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


@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePersona;
    private String puestoTrabajo;
    private String sobreMi;
    private String localidad;
    private long telefono;
    private String email;
    private String gitHubPersonal;
    
    //creo las relacions que tiene persona con exp laboral y academica
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "persona_id")
    private List<ExpLaboral> listadoExpLabolares;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "persona_id")
    private List<ExpAcademica> listadoExpAcademica;

    public Persona() {
    }

    public Persona(String nombrePersona, String puestoTrabajo, String sobreMi, String localidad, long telefono, String email, String gitHubPersonal) {
        this.nombrePersona = nombrePersona;
        this.puestoTrabajo = puestoTrabajo;
        this.sobreMi = sobreMi;
        this.localidad = localidad;
        this.telefono = telefono;
        this.email = email;
        this.gitHubPersonal = gitHubPersonal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public String getSobreMi() {
        return sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGitHubPersonal() {
        return gitHubPersonal;
    }

    public void setGitHubPersonal(String gitHubPersonal) {
        this.gitHubPersonal = gitHubPersonal;
    }





    public void agregarExpLaboral(ExpLaboral expLaboral){
        if (listadoExpLabolares !=null){
            listadoExpLabolares.add(expLaboral);
        }
    }

    public void agregarExpAcademica(ExpAcademica expAcademica){
        if (listadoExpAcademica !=null){
            listadoExpAcademica.add(expAcademica);
        }
    }


}
