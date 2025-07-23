package com.veterinaria.auth.security.model;

import com.veterinaria.auth.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDetalleImpl implements UserDetails {

    private final Long id;
    private final String email;
    private final String password;
    private final String nombre;
    private final String apellidoPaterno;
    private final String apellidoMaterno;
    private final String telefono;
    private final Collection<? extends GrantedAuthority> authorities;

    public UsuarioDetalleImpl(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.nombre = usuario.getNombre();
        this.apellidoPaterno = usuario.getApellidoPaterno();
        this.apellidoMaterno = usuario.getApellidoMaterno();
        this.telefono = usuario.getTelefono();
        this.authorities = getAuthoritiesFromRoles(usuario);
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromRoles(Usuario usuario) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        usuario.getRoles().forEach(rol -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
        });
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
