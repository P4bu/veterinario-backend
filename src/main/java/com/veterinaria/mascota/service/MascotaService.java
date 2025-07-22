package com.veterinaria.mascota.service;

import com.veterinaria.mascota.dto.MascotaDTO;
import com.veterinaria.mascota.dto.MascotaResponseDTO;
import com.veterinaria.mascota.model.Mascota;

import java.util.List;

public interface MascotaService {
    MascotaDTO crearMascota(MascotaDTO mascotaDTO, Long pacienteId);
    List<MascotaResponseDTO> obtenerMascotaPorPaciente(Long pacienteId);
    List<MascotaResponseDTO> ListarMascota();
}
