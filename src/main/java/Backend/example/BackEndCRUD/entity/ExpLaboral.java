package Backend.example.BackEndCRUD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;



@Entity
public class ExpLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreExpLaboral;
    private String nombreCompania;
    private String inicioTrabajo;
    private String finTrabajo;
    private String descripcionTrabajo;
    //variable que va a relacionar la expLaboral con Persona
    @Column(name = "persona_id")
    private Long personaId;

    public ExpLaboral() {
    }

    public ExpLaboral(String nombreExpLaboral, String nombreCompania, String inicioTrabajo, String finTrabajo, String descripcionTrabajo) {
        this.nombreExpLaboral = nombreExpLaboral;
        this.nombreCompania = nombreCompania;
        this.inicioTrabajo = inicioTrabajo;
        this.finTrabajo = finTrabajo;
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreExpLaboral() {
        return nombreExpLaboral;
    }

    public void setNombreExpLaboral(String nombreExpLaboral) {
        this.nombreExpLaboral = nombreExpLaboral;
    }

    public String getNombreCompania() {
        return nombreCompania;
    }

    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }

    public String getInicioTrabajo() {
        return inicioTrabajo;
    }

    public void setInicioTrabajo(String inicioTrabajo) {
        this.inicioTrabajo = inicioTrabajo;
    }

    public String getFinTrabajo() {
        return finTrabajo;
    }

    public void setFinTrabajo(String finTrabajo) {
        this.finTrabajo = finTrabajo;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }
}
