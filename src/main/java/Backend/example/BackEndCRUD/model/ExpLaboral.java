package Backend.example.BackEndCRUD.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class ExpLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCompania;
    private Boolean esTrabajoActual;
    private Date inicioTrabajo;
    private Date finTrabajo;
    private String sobreProyecto;
    //variable que va a relacionar la expLaboral con Persona
    @Column(name = "persona_id")
    private Long personaId;

    public ExpLaboral() {
    }

    public ExpLaboral(String nombreCompania, Boolean esTrabajoActual, Date inicioTrabajo, Date finTrabajo, String sobreProyecto, Long personaId) {
        this.nombreCompania = nombreCompania;
        this.esTrabajoActual = esTrabajoActual;
        this.inicioTrabajo = inicioTrabajo;
        this.finTrabajo = finTrabajo;
        this.sobreProyecto = sobreProyecto;
        this.personaId = personaId;
    }

}
