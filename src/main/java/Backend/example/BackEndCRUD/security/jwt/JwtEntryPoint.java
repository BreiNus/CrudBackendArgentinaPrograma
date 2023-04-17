package Backend.example.BackEndCRUD.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


@Component
//Lo que hace esta clase es comprobar comprobar si hay un token valido, sino lo que hace es que devuelve una respuesta 401 no autorizado
//basicamente rechaza todas las peticiones que no esten autenticadas
public class JwtEntryPoint implements AuthenticationEntryPoint {
    
    //este es un logger y lo utilizaremos en desarrollo para ver cual es el metodo que nos este dando error en caso de que no funcione la app
    private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e) throws IOException, ServletException {
        //aca vamos a lanzar el error
        logger.error("fail en el método commence"  + e.getMessage() + e.getLocalizedMessage());
        //el response manda el error
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
    }
    
}
