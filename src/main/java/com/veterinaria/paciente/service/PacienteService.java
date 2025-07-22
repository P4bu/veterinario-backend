package com.veterinaria.paciente.service;

import com.veterinaria.paciente.dto.PacienteDTO;
import com.veterinaria.paciente.dto.PacienteResponseDTO;
import com.veterinaria.paciente.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    Paciente crearPaciente(PacienteDTO pacienteDTO);
    Optional<PacienteResponseDTO> obtenerPacientePorId(Long id);
    List<PacienteResponseDTO> listarPacientes();
}
