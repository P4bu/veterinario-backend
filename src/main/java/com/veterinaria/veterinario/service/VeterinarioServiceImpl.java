package com.veterinaria.veterinario.service;

import com.veterinaria.veterinario.model.Veterinario;
import com.veterinaria.veterinario.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeterinarioServiceImpl implements VeterinarioService{

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioServiceImpl(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public Veterinario crearVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    public Optional<Veterinario> obtenerVeterinarioPorId(Long id) {
        return veterinarioRepository.findById(id);
    }

    @Override
    public List<Veterinario> obtenerVeterinarios() {
        return veterinarioRepository.findAll();
    }
}
