package Backend.example.BackEndCRUD.dto;

//de momento solo esta implementado en AuthController en la parte de seguridad
public class Mensaje {
    
    private String mensaje;

    public Mensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
