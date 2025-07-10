package com.veterinaria.veterinario.repository;

import com.veterinaria.veterinario.model.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
    Optional<Veterinario> findByUsuarioEmail(String email);
}
