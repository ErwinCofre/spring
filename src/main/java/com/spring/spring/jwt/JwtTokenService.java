package com.spring.spring.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@Service
public class JwtTokenService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    /**
     * Genera un token JWT para un usuario autenticado
     *
     * @param username El nombre de usuario
     * @param role     El rol del usuario
     * @return Token JWT generado
     */
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)//establece nombre de usuario
                .claim("role", role)//seteamos datos payload...rol
                .claim("mensaje","Hola")//seteamos datos payload...mensaje
                .setExpiration(new Date(System.currentTimeMillis() + (1000*60*10))) // dar fecha de cadicidad
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes(StandardCharsets.UTF_8))//firmar token
                .compact();//justa to.do en un texto
    }

//    private Instant expirationDate(int value) {
//        return LocalDateTime.now().plusHours(value).toInstant(ZoneOffset.of("-03:00"));
//    }
}
