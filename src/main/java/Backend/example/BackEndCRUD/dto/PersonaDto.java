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
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String email;
    @NotBlank
    private String fechaNacimiento;
    @NotBlank
    private String telefono;
    @NotBlank
    private String localidad;
    @NotBlank
    private String sobreMi;

    private List<ExpLaboral> listadoExpLabolares;

    private List<ExpAcademica> listadoExpAcademica;

    public PersonaDto() {
    }

    public PersonaDto(@NotBlank String nombre, @NotBlank String apellido, @NotBlank String email, @NotBlank String fechaNacimiento, @NotBlank String telefono, @NotBlank String localidad, @NotBlank String sobreMi) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.localidad = localidad;
        this.sobreMi = sobreMi;
        this.listadoExpAcademica =new ArrayList<>();
        this.listadoExpLabolares =new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getSobreMi() {
        return sobreMi;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public List<ExpLaboral> getListadoExpLabolares() {
        return listadoExpLabolares;
    }

    public List<ExpAcademica> getListadoExpAcademica() {
        return listadoExpAcademica;
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
