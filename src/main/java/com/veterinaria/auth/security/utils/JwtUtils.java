package com.veterinaria.auth.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class JwtUtils {

    private final String jwtSecret = "clave_super_segura_123456789_veterinaria";

    public Long getUsuarioIdDesdeToken() {
        String token = extractTokenFromContext();
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("usuarioId", Long.class);
    }

    private String extractTokenFromContext() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getCredentials() instanceof String token) {
            return token; // El token ya viene limpio, sin "Bearer "
        }

        throw new RuntimeException("TOKEN JWT NO ENCONTRADOR EN SECURITYCONTEXT");
    }
}
