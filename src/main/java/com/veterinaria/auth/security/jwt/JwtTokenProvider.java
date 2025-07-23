package com.veterinaria.auth.security.jwt;

import com.veterinaria.auth.security.model.UsuarioDetalleImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {

    private final String jwtSecret = "clave_super_segura_123456789_veterinaria";
    private final long jwtExpirationInMinutes = 86400000;
    private final Key jwtSecretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

    public String generarToken(Authentication authentication) {
        UsuarioDetalleImpl userPrincipal = (UsuarioDetalleImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("usuarioId", userPrincipal.getId())
                .claim("roles", userPrincipal.getAuthorities().stream()
                        .map(auth -> auth.getAuthority())
                        .toList())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMinutes))
                .signWith(jwtSecretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String obtenerEmailDesdeToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecretKey).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validarToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecretKey).build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Key getJwtSecretKey(){
        return jwtSecretKey;
    }

    public List<String> obtenerRolesDesdeToken(String token) {
        return (List<String>) Jwts.parserBuilder()
                .setSigningKey(jwtSecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
    }
}
