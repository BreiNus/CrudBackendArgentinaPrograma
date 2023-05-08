package Backend.example.BackEndCRUD.dto;


import jakarta.validation.constraints.NotBlank;


public class ExpLaboralDto {

    @NotBlank
    private String nombreExpLaboral;
    @NotBlank
    private String nombreCompania;
    @NotBlank
    private String inicioTrabajo;
    @NotBlank
    private String finTrabajo;
    @NotBlank
    private String descripcionTrabajo;

    public ExpLaboralDto() {
    }

    public ExpLaboralDto(String nombreExpLaboral,String nombreCompania, String esTrabajoActual, String inicioTrabajo, String finTrabajo, String descripcionTrabajo) {
        this.nombreExpLaboral = nombreExpLaboral;
        this.nombreCompania = nombreCompania;
        this.inicioTrabajo = inicioTrabajo;
        this.finTrabajo = finTrabajo;
        this.descripcionTrabajo = descripcionTrabajo;
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
}
