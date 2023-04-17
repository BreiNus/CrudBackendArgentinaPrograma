package Backend.example.BackEndCRUD.security;

import Backend.example.BackEndCRUD.security.jwt.JwtEntryPoint;
import Backend.example.BackEndCRUD.security.jwt.JwtTokenFilter;
import Backend.example.BackEndCRUD.security.service.UserDetailsServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
//esta annotacion lo que hace es indicar a que metodos puede acceder solo el administrador
//los metodos que no lleven una annotacion puede acceder tanto el adm como un user comun 
@EnableMethodSecurity
//Esta clase es la que organiza todas las clases e interfaces de security
public class MainSecurity {

    @Autowired
    UserDetailsServiceImplements userDetailsServiceImplements;

    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsServiceImplements).passwordEncoder(passwordEncoder);
        authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);

        http.csrf().disable();
        http.cors();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //endopoints
        http.authorizeHttpRequests().requestMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();

        //control de sesiones
        http.exceptionHandling().authenticationEntryPoint(jwtEntryPoint);
        //añadir filtro antes
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    
}
