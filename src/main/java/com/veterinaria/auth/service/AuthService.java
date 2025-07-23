package com.veterinaria.auth.service;

import com.veterinaria.auth.dto.LoginDTO;
import com.veterinaria.auth.dto.LoginResponse;
import com.veterinaria.auth.repository.UsuarioRepository;
import com.veterinaria.auth.security.jwt.JwtTokenProvider;
import com.veterinaria.auth.security.model.UsuarioDetalleImpl;
import com.veterinaria.auth.security.service.UsuarioDetalleServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioDetalleServiceImpl usuarioDetalleService;
    private final UsuarioRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UsuarioDetalleServiceImpl usuarioDetalleService, UsuarioRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.usuarioDetalleService = usuarioDetalleService;
        this.userRepository = userRepository;
    }

    @Transactional
    public LoginResponse login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        UsuarioDetalleImpl userDetails = (UsuarioDetalleImpl) authentication.getPrincipal();

        String jwt = jwtTokenProvider.generarToken(authentication);

        Set<String> roles = userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toSet());

        String nameComplete = userDetails.getUsername();

        return new LoginResponse(
                jwt,
                nameComplete,
                "Bearer",
                userDetails.getUsername(),
                roles
        );
    }

}

