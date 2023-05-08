package Backend.example.BackEndCRUD.dto;

import jakarta.validation.constraints.NotBlank;

public class ProyectosDto {

    @NotBlank
    private String nombreProyecto;
    @NotBlank
    private String urlRepo;
    @NotBlank
    private String imgProyecto;
    @NotBlank
    private String descripcionProyecto;

    public ProyectosDto() {
    }

    public ProyectosDto(String nombreProyecto, String urlRepo, String imgProyecto, String descripcionProyecto) {
        this.nombreProyecto = nombreProyecto;
        this.urlRepo = urlRepo;
        this.imgProyecto = imgProyecto;
        this.descripcionProyecto = descripcionProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getUrlRepo() {
        return urlRepo;
    }

    public void setUrlRepo(String urlRepo) {
        this.urlRepo = urlRepo;
    }

    public String getImgProyecto() {
        return imgProyecto;
    }

    public void setImgProyecto(String imgProyecto) {
        this.imgProyecto = imgProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setdescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }
}
