package br.com.fiap.coletaSeletiva.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerificarToken verificarToken;

    @Bean
    public SecurityFilterChain filtrarCadeiaDeSeguranca(
            HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        //Registro e login
                        .requestMatchers(HttpMethod.POST, "auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "auth/login").permitAll()
                        .requestMatchers("/error").permitAll()

                        //Autorização ponto de descarte
                        .requestMatchers(HttpMethod.GET, "api/ponto-descarte").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "api/ponto-descarte/{pontoId}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/ponto-descarte").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/ponto-descarte/{pontoId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/ponto-descarte").hasRole("ADMIN")

                        //Autorização coleta
                        .requestMatchers(HttpMethod.GET, "api/coleta").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "api/coleta/{coletaId}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/coleta").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/coleta/{coletaId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/coleta/").hasRole("ADMIN")

                        //Autorização usuário
                        .requestMatchers(HttpMethod.GET, "api/usuarios").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "api/usuarios/{usuarioId}").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/usuarios/{usuarioId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/usuarios").hasRole("ADMIN")


                        .anyRequest()
                        .authenticated())
                .addFilterBefore(
                        verificarToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
