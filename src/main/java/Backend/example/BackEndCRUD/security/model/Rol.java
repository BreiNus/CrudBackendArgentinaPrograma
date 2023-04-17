package Backend.example.BackEndCRUD.security.model;

import Backend.example.BackEndCRUD.security.enums.RolNombre;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;


@Entity
public class Rol {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    //como va a ser de tipo cadena(String), hay que indicarselo porque sino por defecto la DB creara un ordinal(un numero)
    @Enumerated(EnumType.STRING)
    //el enum que se creo
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
    
    
}
