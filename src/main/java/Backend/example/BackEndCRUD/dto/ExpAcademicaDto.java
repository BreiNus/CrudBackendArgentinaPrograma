package Backend.example.BackEndCRUD.dto;

import jakarta.validation.constraints.NotBlank;

public class ExpAcademicaDto {

    @NotBlank
    private String nombreExpAcademica;
    @NotBlank
    private String descripcionExpAcademica;
    @NotBlank
    private String nivel;
    @NotBlank
    private String lugar;
    @NotBlank
    private String inicioEstudio;
    @NotBlank
    private String finEstudio;

    public ExpAcademicaDto() {
    }

    public ExpAcademicaDto(String nombreExpAcademica, String descripcionExpAcademica, String nivel, String lugar, String inicioEstudio, String finEstudio) {
        this.nombreExpAcademica = nombreExpAcademica;
        this.descripcionExpAcademica = descripcionExpAcademica;
        this.nivel = nivel;
        this.lugar = lugar;
        this.inicioEstudio = inicioEstudio;
        this.finEstudio = finEstudio;
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
