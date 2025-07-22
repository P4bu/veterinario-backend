package com.veterinaria.mascota.service;

import com.veterinaria.mascota.dto.MascotaDTO;
import com.veterinaria.mascota.dto.MascotaResponseDTO;

import java.util.List;

public interface MascotaService {
    MascotaDTO crearMascota(MascotaDTO mascotaDTO, Long pacienteId);
    List<MascotaResponseDTO> obtenerMascotaPorPaciente(Long pacienteId);
    List<MascotaResponseDTO> ListarMascota();
}
