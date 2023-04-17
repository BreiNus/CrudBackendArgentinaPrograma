package Backend.example.BackEndCRUD.security.jwt;

import Backend.example.BackEndCRUD.security.dto.JwtDto;
import Backend.example.BackEndCRUD.security.model.UsuarioPrincipal;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import java.security.Key;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
//Lo que hace esta clase es generar el token y posee un metodo de validacion para ver que este bien formado, que no este expirado, etc
//No implementa ni hereda de ninguna clase
public class JwtProvider {
//dentro habran tres metodos

    //este es un logger y lo utilizaremos en desarrollo para ver cual es el metodo que nos este dando error en caso de que no funcione la app
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    //estos dos valores estan en application.properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    //se contrulle el token
    public String generateToken(Authentication authentication) {
        //aca se realizo un casteo se esta casteando el .getPrincipal() de un UsuarioPrincipal que es nuestra clase
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        //para pasar los roles hacemos una lista de roles
        List<String> roles = usuarioPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        //construimos el token, en el cual le pasamos por parametros el usuario, el tiempo de expiracion y la firma
        //subject es el nombre de usuario
        return Jwts.builder()
                .setSubject(usuarioPrincipal.getUsername())
                //estoy diciendo que en el payload va a hacer un campo llamado roles, que va a contener la lista de roles que se hice en la linea 40
                .claim("roles", roles)
                //momento de creacion del token
                .setIssuedAt(new Date())
                //momento de expiracion del token
                .setExpiration(new Date(new Date().getTime() + expiration))
                //firma
                .signWith(getSecret(secret))
                .compact();
    }

    //se deconstrulle el token
    //obtener el nombreUsuario del token
    public String getNombreUsuarioFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    //validar el token 
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("Token vacio");
        } catch (SignatureException e) {
            logger.error("Falla en la firma");
        }
        return false;
    }

    //metodo para hacer un refresh del token con la libreria "nimbus jose jtw"
    public String refreshToken(JwtDto jwtDto) throws ParseException {
        //comprobamos si el token es valido para que no genere vulnerabilidad en la pagina
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(jwtDto.getToken());
        } catch (ExpiredJwtException e) {
            //aca parseo el jwt que viene de JwtDto
            JWT jwt = JWTParser.parse(jwtDto.getToken());
            //aca optengo los claims y los guardo en esta variable
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            //ahora queda obtener los valores
            String nombreUsuario = claims.getSubject();
            List<String> roles = (List<String>) claims.getClaim("roles");

            //aca creamos nuevamente el token, esto es por si el otro token expiro y asi lo creamos otra vez con nuevas fechas de expiracion
            return Jwts.builder()
                    .setSubject(nombreUsuario)
                    //estoy diciendo que en el payload va a hacer un campo llamado roles, que va a contener la lista de roles que se hice en la linea 40
                    .claim("roles", roles)
                    //momento de creacion del token
                    .setIssuedAt(new Date())
                    //momento de expiracion del token
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    //firma
                    .signWith(getSecret(secret))
                    .compact();
        }
        return null;
    }
    
    private Key getSecret(String secret){
        //primero lo convierto en un array de bytes
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        //y lo ciframos
        return Keys.hmacShaKeyFor(secretBytes);
        
    }
}
