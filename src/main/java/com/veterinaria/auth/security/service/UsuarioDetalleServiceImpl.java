package com.veterinaria.auth.security.service;

import com.veterinaria.auth.model.Usuario;
import com.veterinaria.auth.security.model.UsuarioDetalleImpl;
import com.veterinaria.auth.service.UsuarioService;
import com.veterinaria.auth.service.UsuarioServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetalleServiceImpl implements UserDetailsService {

    private final UsuarioService usuarioService;

    public UsuarioDetalleServiceImpl(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("USUARIO NO ENCONTRADO: " + email));
        usuario.getRoles().size();
        return new UsuarioDetalleImpl(usuario);
    }
}
