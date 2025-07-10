package com.veterinaria.paciente.service;

import com.veterinaria.paciente.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    Paciente crearPaciente(Paciente paciente);
    Optional<Paciente> obtenerPacientePorId(Long id);
    List<Paciente> listarPacientes();
}
