package com.veterinaria.cita.service;

import com.veterinaria.cita.dto.CitaDTO;
import com.veterinaria.cita.dto.CitaResponseDTO;
import com.veterinaria.cita.model.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    CitaResponseDTO crearCita(CitaDTO citaDTO);
    Cita modificarEstado(Long citaId, String nuevoEstado);
    List<CitaResponseDTO> listarCitas();
    List<CitaResponseDTO> listarPorVeterinario(Long veterinarioId);
    List<CitaResponseDTO> listarPorMascota(Long mascotaId);
    Optional<CitaResponseDTO> obtenerPorId(Long id);
}
