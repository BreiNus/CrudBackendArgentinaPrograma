package Backend.example.BackEndCRUD.dto;

import jakarta.validation.constraints.NotBlank;

public class SkillsDto {
    @NotBlank
    private String nombreSkill;
    @NotBlank
    private int porcentajeSkill;
    @NotBlank
    private String colorSkill;

    public SkillsDto() {
    }

    public SkillsDto(String nombreSkill, int porcentajeSkill, String colorSkill) {
        this.nombreSkill = nombreSkill;
        this.porcentajeSkill = porcentajeSkill;
        this.colorSkill = colorSkill;
    }

    public String getNombreSkill() {
        return nombreSkill;
    }

    public void setNombreSkill(String nombreSkill) {
        this.nombreSkill = nombreSkill;
    }

    public int getPorcentajeSkill() {
        return porcentajeSkill;
    }

    public void setPorcentajeSkill(int porcentajeSkill) {
        this.porcentajeSkill = porcentajeSkill;
    }

    public String getColorSkill() {
        return colorSkill;
    }

    public void setColorSkill(String colorSkill) {
        this.colorSkill = colorSkill;
    }
}
