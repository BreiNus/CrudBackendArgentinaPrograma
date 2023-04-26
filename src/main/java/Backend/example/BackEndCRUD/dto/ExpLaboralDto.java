package Backend.example.BackEndCRUD.dto;


import jakarta.validation.constraints.NotBlank;


public class ExpLaboralDto {

    @NotBlank
    private String nombreCompania;
    @NotBlank
    private String esTrabajoActual;
    @NotBlank
    private String inicioTrabajo;
    @NotBlank
    private String finTrabajo;
    @NotBlank
    private String sobreProyecto;

    public ExpLaboralDto() {
    }

    public ExpLaboralDto(String nombreCompania, String esTrabajoActual, String inicioTrabajo, String finTrabajo, String sobreProyecto) {
        this.nombreCompania = nombreCompania;
        this.esTrabajoActual = esTrabajoActual;
        this.inicioTrabajo = inicioTrabajo;
        this.finTrabajo = finTrabajo;
        this.sobreProyecto = sobreProyecto;
    }

    public String getNombreCompania() {
        return nombreCompania;
    }

    public void setNombreCompania(String nombreCompania) {
        this.nombreCompania = nombreCompania;
    }

    public String getEsTrabajoActual() {
        return esTrabajoActual;
    }

    public void setEsTrabajoActual(String esTrabajoActual) {
        this.esTrabajoActual = esTrabajoActual;
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

    public String getSobreProyecto() {
        return sobreProyecto;
    }

    public void setSobreProyecto(String sobreProyecto) {
        this.sobreProyecto = sobreProyecto;
    }
}
