package com.veterinaria.mascota.service;

import com.veterinaria.mascota.model.Mascota;

import java.util.List;

public interface MascotaService {
    Mascota crearMascota(Mascota mascota, Long pacienteId);
    List<Mascota> obtenerMascotaPorPaciente(Long pacienteId);
    List<Mascota> ListarMascota();
}
