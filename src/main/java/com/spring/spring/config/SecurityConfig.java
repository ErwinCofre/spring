package com.spring.spring.config;

import com.spring.spring.services.UsuarioLoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UsuarioLoginService usuarioLoginService;

    public SecurityConfig(UsuarioLoginService usuarioLoginService) {
        this.usuarioLoginService = usuarioLoginService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //AuthenticationManager
    //encargado de realizar la autenticacion

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, UsuarioLoginService usuarioLoginService,
                                                       PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder
                .userDetailsService(usuarioLoginService)
                .passwordEncoder(passwordEncoder);
        return authBuilder.build();


    }


    //SecurityFilterChain
    //va a generar la reglas  de seguridad

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/rest/**").permitAll()//a estas rutas se puede acceder sin auth- rutas publicas
                                .anyRequest().authenticated()//todas las demas rutas necesitan auth - rutas privadas
                )
                .formLogin(form ->

                        form
                                .defaultSuccessUrl("/producto/lista", true)//cuando se hace el login nos envia a esta rutta
                                .permitAll()//quienes-TODOS

                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")//ruta de logout
                                .logoutSuccessUrl("/login?logout")//ruta cuando el logout esta hecho

                );
        return http.build();


    }


}