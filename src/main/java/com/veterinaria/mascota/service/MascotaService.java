package com.veterinaria.mascota.service;

import com.veterinaria.mascota.dto.MascotaDTO;
import com.veterinaria.mascota.model.Mascota;

import java.util.List;

public interface MascotaService {
    MascotaDTO crearMascota(MascotaDTO mascotaDTO, Long pacienteId);
    List<Mascota> obtenerMascotaPorPaciente(Long pacienteId);
    List<Mascota> ListarMascota();
}
