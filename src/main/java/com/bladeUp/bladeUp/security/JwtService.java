package com.bladeUp.bladeUp.security;

import com.bladeUp.bladeUp.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "bladeUpTokenSecret";  // Cambia esto por una clave segura

    // Método para generar el token
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())  // Puede incluir el email o el ID de usuario
                .claim("userId", user.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 dia de expiración
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}