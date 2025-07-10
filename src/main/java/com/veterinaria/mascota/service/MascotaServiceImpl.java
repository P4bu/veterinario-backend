package com.veterinaria.mascota.service;

import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    @Override
    public Mascota crearMascota(Mascota mascota, Long pacienteId) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public List<Mascota> obtenerMascotaPorPaciente(Long pacienteId) {
        return mascotaRepository.findByPacienteId(pacienteId);
    }
}
