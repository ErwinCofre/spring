package com.spring.spring.filters;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final String jwtKey;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, String jwtKey) {
        super(authenticationManager);
        this.jwtKey = jwtKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        //obtiene encabezado de la consulta buscando Authorization
        String authorizationHeader = request.getHeader("Authorization");


            //verifica que exista cabezare y estructura Bearer
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");//limpia y deja solo token
            String username = Jwts.parserBuilder()
                    .setSigningKey(jwtKey.getBytes(StandardCharsets.UTF_8))//setea clave secreta para validar la firma
                    .build()
                    .parseClaimsJws(token)//obtener/analizar informacion de payload
                    .getBody()
                    .getSubject();//obtenermos informacion sobre usuario del token

            if (username != null) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, null);
                //guardamos en contexto informacion de usuario y auntenticacion
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
