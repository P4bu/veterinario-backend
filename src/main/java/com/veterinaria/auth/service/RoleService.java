package com.veterinaria.auth.service;

import com.veterinaria.auth.model.Erole;
import com.veterinaria.auth.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByNombre(Erole nombre);
}
