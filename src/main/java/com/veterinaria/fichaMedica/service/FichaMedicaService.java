package com.veterinaria.fichaMedica.service;

import com.veterinaria.fichaMedica.model.FichaMedica;

import java.util.List;
import java.util.Optional;

public interface FichaMedicaService {
    FichaMedica crearFichaMedica(FichaMedica fichaMedica);
    List<FichaMedica> listarFichasPorMascotas(Long mascotaId);
    Optional<FichaMedica> obtenerPorId(Long id);
}
