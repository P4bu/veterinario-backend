package com.veterinaria.paciente.mapper;

import com.veterinaria.paciente.dto.PacienteResponseDTO;
import com.veterinaria.paciente.model.Paciente;

public class PacienteMapper {

    public static PacienteResponseDTO toResponseDTO(Paciente paciente) {
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();

        pacienteResponseDTO.setNombre(paciente.getUsuario().getNombre());
        pacienteResponseDTO.setApellidoPaterno(paciente.getUsuario().getApellidoPaterno());
        pacienteResponseDTO.setApellidoMaterno(paciente.getUsuario().getApellidoMaterno());
        pacienteResponseDTO.setEmail(paciente.getUsuario().getEmail());
        pacienteResponseDTO.setTelefono(paciente.getUsuario().getTelefono());

        return pacienteResponseDTO;
    }
}
