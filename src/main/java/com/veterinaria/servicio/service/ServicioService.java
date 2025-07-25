package com.veterinaria.servicio.service;

import com.veterinaria.servicio.dto.ServicioDTO;
import com.veterinaria.servicio.dto.ServicioResponseDTO;
import com.veterinaria.servicio.model.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    ServicioDTO crearServicio(ServicioDTO servicioDTO);
    List<ServicioResponseDTO> listarServicios();
    List<ServicioResponseDTO> listarPorVeterinario(Long veterinarioId);
    Optional<ServicioResponseDTO> obtenerPorId(Long id);
}
