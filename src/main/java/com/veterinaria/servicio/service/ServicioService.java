package com.veterinaria.servicio.service;

import com.veterinaria.servicio.model.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    Servicio crearServicio(Servicio servicio);
    List<Servicio> listarServicios();
    List<Servicio> listarPorVeterinario(Long veterinarioId);
    Optional<Servicio> obtenerPorId(Long id);
}
