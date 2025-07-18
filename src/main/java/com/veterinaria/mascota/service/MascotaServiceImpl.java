package com.veterinaria.mascota.service;

import com.veterinaria.mascota.dto.MascotaDTO;
import com.veterinaria.mascota.mapper.MascotaMapper;
import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.repository.MascotaRepository;
import com.veterinaria.paciente.model.Paciente;
import com.veterinaria.paciente.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImpl implements MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PacienteRepository pacienteRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository, PacienteRepository pacienteRepository) {
        this.mascotaRepository = mascotaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public MascotaDTO crearMascota(MascotaDTO mascotaDTO, Long pacienteId) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(pacienteId);
        if(!pacienteOptional.isPresent()) {
            throw new IllegalArgumentException("EL PACIENTE NO EXISTE EN LA BASE DE DATOS");
        }

        Paciente paciente = pacienteOptional.get();

        Mascota mascota = MascotaMapper.toEntity(mascotaDTO, paciente);
        Mascota mascotaGuardada = mascotaRepository.save(mascota);

        return MascotaMapper.toDTO(mascotaGuardada);
    }

    @Override
    public List<Mascota> obtenerMascotaPorPaciente(Long pacienteId) {
        return mascotaRepository.findByPacienteId(pacienteId);
    }

    @Override
    public List<Mascota> ListarMascota() {
        return mascotaRepository.findAll();
    }
}
