package Backend.example.BackEndCRUD.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ExpAcademica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nivel;
    private String lugar;
    private String titulo;
    private String inicioEstudio;
    private String finEstudio;
    //variable que va a relacionar la expAcademica con Persona
    @Column(name = "persona_id")
    private Long personaId;

    public ExpAcademica() {
    }

    public ExpAcademica(String nivel, String lugar, String titulo, String inicioEstudio, String finEstudio) {
        this.nivel = nivel;
        this.lugar = lugar;
        this.titulo = titulo;
        this.inicioEstudio = inicioEstudio;
        this.finEstudio = finEstudio;
    }

}
