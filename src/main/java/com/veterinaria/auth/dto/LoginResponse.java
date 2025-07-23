package com.veterinaria.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

public class LoginResponse {

    private String token;
    private String nombre;
    private String tipo = "Bearer";
    private String email;

    @JsonIgnore
    private Set<String> roles;

    public LoginResponse(String token, String nombre, String tipo, String email, Set<String> roles) {
        this.token = token;
        this.nombre = nombre;
        this.tipo = tipo;
        this.email = email;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
