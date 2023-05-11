package Backend.example.BackEndCRUD.util;

import Backend.example.BackEndCRUD.security.enums.RolNombre;
import Backend.example.BackEndCRUD.security.model.Rol;
import Backend.example.BackEndCRUD.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
//para poder crear al usuario y asignarle en rol, esto tambien se puede hacer desde la DB
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
/*
        //dentro creamos 2 roles
        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNombre.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
*/
    }

}

//Una vez ejecutado este metodo una vez, y ya creado los roles, hay que borrarlo o comentarlo porque sino cada vez
//que se ejecute la app e inclulla estos metodos se crearan otra vez los roles
