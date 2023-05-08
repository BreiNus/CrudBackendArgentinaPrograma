package Backend.example.BackEndCRUD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
public class ExpAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreExpAcademica;
    private String descripcionExpAcademica;
    private String nivel;
    private String lugar;
    private String inicioEstudio;
    private String finEstudio;
    //variable que va a relacionar la expAcademica con Persona
    @Column(name = "persona_id")
    private Long personaId;

    public ExpAcademica() {
    }

    public ExpAcademica(String nombreExpAcademica, String descripcionExpAcademica, String nivel, String lugar, String inicioEstudio, String finEstudio) {
        this.nombreExpAcademica = nombreExpAcademica;
        this.descripcionExpAcademica = descripcionExpAcademica;
        this.nivel = nivel;
        this.lugar = lugar;
        this.inicioEstudio = inicioEstudio;
        this.finEstudio = finEstudio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreExpAcademica() {
        return nombreExpAcademica;
    }

    public void setNombreExpAcademica(String nombreExpAcademica) {
        this.nombreExpAcademica = nombreExpAcademica;
    }

    public String getDescripcionExpAcademica() {
        return descripcionExpAcademica;
    }

    public void setDescripcionExpAcademica(String descripcionExpAcademica) {
        this.descripcionExpAcademica = descripcionExpAcademica;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getInicioEstudio() {
        return inicioEstudio;
    }

    public void setInicioEstudio(String inicioEstudio) {
        this.inicioEstudio = inicioEstudio;
    }

    public String getFinEstudio() {
        return finEstudio;
    }

    public void setFinEstudio(String finEstudio) {
        this.finEstudio = finEstudio;
    }
}
