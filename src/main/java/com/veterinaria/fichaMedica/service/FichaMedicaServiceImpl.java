package com.veterinaria.fichaMedica.service;

import com.veterinaria.fichaMedica.model.FichaMedica;
import com.veterinaria.fichaMedica.repository.FichaMedicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FichaMedicaServiceImpl implements FichaMedicaService {

    private final FichaMedicaRepository fichaMedicaRepository;

    public FichaMedicaServiceImpl(FichaMedicaRepository fichaMedicaRepository) {
        this.fichaMedicaRepository = fichaMedicaRepository;
    }

    @Override
    public FichaMedica crearFichaMedica(FichaMedica fichaMedica) {
        return fichaMedicaRepository.save(fichaMedica);
    }

    @Override
    public List<FichaMedica> listarFichasPorMascotas(Long mascotaId) {
        return fichaMedicaRepository.findByMascotaId(mascotaId);
    }

    @Override
    public Optional<FichaMedica> obtenerPorId(Long id) {
        return fichaMedicaRepository.findById(id);
    }
}
