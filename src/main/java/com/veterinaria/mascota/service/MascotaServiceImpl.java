package com.veterinaria.mascota.service;

import com.veterinaria.mascota.dto.MascotaDTO;
import com.veterinaria.mascota.dto.MascotaResponseDTO;
import com.veterinaria.mascota.mapper.MascotaMapper;
import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.repository.MascotaRepository;
import com.veterinaria.paciente.model.Paciente;
import com.veterinaria.paciente.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if(pacienteOptional.isEmpty()) {
            throw new IllegalArgumentException("EL PACIENTE NO EXISTE EN LA BASE DE DATOS");
        }

        Paciente paciente = pacienteOptional.get();

        Mascota mascota = MascotaMapper.toEntity(mascotaDTO, paciente);
        Mascota mascotaGuardada = mascotaRepository.save(mascota);

        return MascotaMapper.toDTO(mascotaGuardada);
    }

    @Override
    public List<MascotaResponseDTO> obtenerMascotaPorPaciente(Long pacienteId) {
        List<Mascota> mascotas = mascotaRepository.findByPacienteId(pacienteId);

        return mascotas.stream().map(MascotaMapper::toResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<MascotaResponseDTO> ListarMascota() {
        List<Mascota> mascotas = mascotaRepository.findAll();
        return mascotas.stream().map(MascotaMapper::toResponseDTO).collect(Collectors.toList());
    }
}
