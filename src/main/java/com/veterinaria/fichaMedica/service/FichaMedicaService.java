package com.veterinaria.fichaMedica.service;

import com.veterinaria.fichaMedica.dto.FichaMedicaDTO;
import com.veterinaria.fichaMedica.dto.FichaMedicaResponseDTO;
import com.veterinaria.fichaMedica.model.FichaMedica;

import java.util.List;
import java.util.Optional;

public interface FichaMedicaService {
    FichaMedicaResponseDTO crearFichaMedica(FichaMedicaDTO fichaMedicaDTO);
    List<FichaMedicaResponseDTO> listarFichasPorMascotas(Long mascotaId);
    Optional<FichaMedicaResponseDTO> obtenerPorId(Long id);
    List<FichaMedicaResponseDTO> listarFichasPorVeterinario(Long veterinarioId);
}
