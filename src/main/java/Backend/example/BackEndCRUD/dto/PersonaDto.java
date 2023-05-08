/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.example.BackEndCRUD.dto;

import Backend.example.BackEndCRUD.entity.ExpAcademica;
import Backend.example.BackEndCRUD.entity.ExpLaboral;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class PersonaDto {

    @NotBlank
    private String nombrePersona;
    @NotBlank
    private String puestoTrabajo;
    @NotBlank
    private String sobreMi;
    @NotBlank
    private String localidad;
    @NotBlank
    private long telefono;
    @NotBlank
    private String email;
    @NotBlank
    private String gitHubPersonal;

    private List<ExpLaboral> listadoExpLabolares;

    private List<ExpAcademica> listadoExpAcademica;

    public PersonaDto() {
    }

    public PersonaDto(String nombre, String puestoTrabajo, String sobreMi, String localidad, long telefono, String email, String gitHubPersonal, List<ExpLaboral> listadoExpLabolares, List<ExpAcademica> listadoExpAcademica) {
        this.nombrePersona = nombrePersona;
        this.puestoTrabajo = puestoTrabajo;
        this.sobreMi = sobreMi;
        this.localidad = localidad;
        this.telefono = telefono;
        this.email = email;
        this.gitHubPersonal = gitHubPersonal;
        this.listadoExpLabolares = listadoExpLabolares;
        this.listadoExpAcademica = listadoExpAcademica;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getPuestoTrabajo() {
        return puestoTrabajo;
    }

    public void setPuestoTrabajo(String puestoTrabajo) {
        this.puestoTrabajo = puestoTrabajo;
    }

    public String getSobreMi() {
        return sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGitHubPersonal() {
        return gitHubPersonal;
    }

    public void setGitHubPersonal(String gitHubPersonal) {
        this.gitHubPersonal = gitHubPersonal;
    }


    public void agregarExpLaboral(ExpLaboral expLaboral){
        if (listadoExpLabolares !=null){
            listadoExpLabolares.add(expLaboral);
        }
    }

    public void agregarExpAcademica(ExpAcademica expAcademica){
        if (listadoExpAcademica !=null){
            listadoExpAcademica.add(expAcademica);
        }
    }
}
