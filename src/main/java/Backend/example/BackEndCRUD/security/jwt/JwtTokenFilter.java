package Backend.example.BackEndCRUD.security.jwt;

import Backend.example.BackEndCRUD.security.service.UserDetailsServiceImplements;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
//Esta clase se va a ejecutar por cada peticion (request), va a comprobar que sea valido el token(esto lo hace utilizando 
//el JwtProvider)y en caso de que sea valido el token va a permitir el acceso al recurso, sino lanzara la excepcion
//por cada request se va a ejecutar el filtro
public class JwtTokenFilter extends OncePerRequestFilter {

    //este es un logger y lo utilizaremos en desarrollo para ver cual es el metodo que nos este dando error en caso de que no funcione la app
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailsServiceImplements userDetailsServiceImplements;

    @Override
    //aca se va decir si el token esta autenticado y va a comprobar si el token es valido o no
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);
            //comprobamos que el token es valido y que existe el token
            if (token != null && jwtProvider.validateToken(token)) {
                //a partir del token obtenemos el nombreUsuario
                String nombreUsuario = jwtProvider.getNombreUsuarioFromToken(token);
                //para acceder a los datos del usuario
                //el nombreUsuario es el que obtenemos del token
                UserDetails userDetails = userDetailsServiceImplements.loadUserByUsername(nombreUsuario);
                //creamos una nueva autenticacion
                UsernamePasswordAuthenticationToken auth
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                //al contexto de la utenticacion vamos a asignarle ese usuario
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            logger.error("Fallo en el método doFilter " + e.getMessage());
        }
        //si no tira la excepcion llamamos a filterChain
        filterChain.doFilter(req, res);

    }

    //como estamos utilizando bearer el token esta formado por HEADER que pone Authorization: bearer y viene el token
    //por eso se crea un metodo para extraer el token, eliminar el bearer con su espacio
    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer")) {
            return header.replace("Bearer ", "");
        }

        return null;
    }
}
