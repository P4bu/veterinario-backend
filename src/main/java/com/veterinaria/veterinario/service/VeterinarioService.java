package com.veterinaria.veterinario.service;

import com.veterinaria.veterinario.model.Veterinario;

import java.util.List;
import java.util.Optional;

public interface VeterinarioService {
    Veterinario crearVeterinario(Veterinario veterinario);
    Optional<Veterinario> obtenerVeterinarioPorId(Long id);
    List<Veterinario> obtenerVeterinarios();
}
