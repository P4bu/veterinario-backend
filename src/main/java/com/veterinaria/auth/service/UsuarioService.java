package com.veterinaria.auth.service;

import com.veterinaria.auth.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> findByEmail(String email);
}
