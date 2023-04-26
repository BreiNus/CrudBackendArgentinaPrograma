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
public class ExpLaboral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCompania;
    private String esTrabajoActual;
    private String inicioTrabajo;
    private String finTrabajo;
    private String sobreProyecto;
    //variable que va a relacionar la expLaboral con Persona
    @Column(name = "persona_id")
    private Long personaId;

    public ExpLaboral() {
    }

    public ExpLaboral(String nombreCompania, String esTrabajoActual, String inicioTrabajo, String finTrabajo, String sobreProyecto) {
        this.nombreCompania = nombreCompania;
        this.esTrabajoActual = esTrabajoActual;
        this.inicioTrabajo = inicioTrabajo;
        this.finTrabajo = finTrabajo;
        this.sobreProyecto = sobreProyecto;
    }

}
