package Backend.example.BackEndCRUD.dto;

import jakarta.validation.constraints.NotBlank;

public class ExpAcademicaDto {

    @NotBlank
    private String nivel;
    @NotBlank
    private String lugar;
    @NotBlank
    private String titulo;
    @NotBlank
    private String inicioEstudio;
    @NotBlank
    private String finEstudio;

    public ExpAcademicaDto() {
    }

    public ExpAcademicaDto(String nivel, String lugar, String titulo, String inicioEstudio, String finEstudio) {
        this.nivel = nivel;
        this.lugar = lugar;
        this.titulo = titulo;
        this.inicioEstudio = inicioEstudio;
        this.finEstudio = finEstudio;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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
