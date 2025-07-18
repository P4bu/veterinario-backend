package com.veterinaria.servicio.service;

import com.veterinaria.servicio.dto.ServicioDTO;
import com.veterinaria.servicio.model.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    ServicioDTO crearServicio(ServicioDTO servicioDTO);
    List<Servicio> listarServicios();
    List<Servicio> listarPorVeterinario(Long veterinarioId);
    Optional<Servicio> obtenerPorId(Long id);
}
