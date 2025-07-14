package com.veterinaria.cita.service;

import com.veterinaria.cita.model.Cita;

import java.util.List;
import java.util.Optional;

public interface CitaService {
    Cita crearCita(Cita cita);
    Cita modificarEstado(Long citaId, String nuevoEstado);
    List<Cita> listarCitas();
    List<Cita> listarPorVeterinario(Long veterinarioId);
    List<Cita> listarPorMascota(Long mascotaId);
    Optional<Cita> obtenerPorId(Long id);
}
