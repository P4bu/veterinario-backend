package com.veterinaria.auth.repository;

import com.veterinaria.auth.model.Erole;
import com.veterinaria.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNombre(Erole nombre);
}
