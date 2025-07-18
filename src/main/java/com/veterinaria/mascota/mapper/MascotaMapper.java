package com.veterinaria.mascota.mapper;

import com.veterinaria.mascota.dto.MascotaDTO;
import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.paciente.model.Paciente;

public class MascotaMapper {

    public static Mascota toEntity(MascotaDTO mascotaDTO, Paciente paciente) {
        Mascota mascota = new Mascota();

        mascota.setNombre(mascotaDTO.getNombre());
        mascota.setEspecie(mascotaDTO.getEspecie());
        mascota.setRaza(mascotaDTO.getRaza());
        mascota.setEdad(mascotaDTO.getEdad());
        mascota.setPaciente(paciente);

        return mascota;
    }

    public static MascotaDTO toDTO(Mascota mascota) {
        MascotaDTO dto = new MascotaDTO();

        dto.setNombre(mascota.getNombre());
        dto.setEspecie(mascota.getEspecie());
        dto.setRaza(mascota.getRaza());
        dto.setEdad(mascota.getEdad());

        return dto;
    }

}
