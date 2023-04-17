package Backend.example.BackEndCRUD.security.dto;

//esta clase se utilizara en el momento que hagamos un login,nos va a devolver esto
//el responseEntity  del controller, un JWT
public class JwtDto {

    private String token;

    public JwtDto() {
    }
    
    public JwtDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
