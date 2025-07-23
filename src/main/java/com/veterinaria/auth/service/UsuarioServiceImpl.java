package com.veterinaria.auth.service;

import com.veterinaria.auth.model.Usuario;
import com.veterinaria.auth.repository.RoleRepository;
import com.veterinaria.auth.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;

    public UsuarioServiceImpl (UsuarioRepository usuarioRepository, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public Optional<Usuario> findByEmail(String email) {
        if(email == null || email.isEmpty()){
            throw new IllegalArgumentException("Email no puede estar vacio ");
        }
        return usuarioRepository.findByEmail(email);
    }
}
