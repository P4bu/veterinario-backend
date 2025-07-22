package com.veterinaria.cita.mapper;

import com.veterinaria.cita.dto.CitaDTO;
import com.veterinaria.cita.dto.CitaResponseDTO;
import com.veterinaria.cita.model.Cita;
import com.veterinaria.cita.model.EstadoCita;
import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.veterinario.model.Veterinario;

public class CitaMapper {

    public static CitaResponseDTO toDTO(Cita cita) {
        if (cita == null) {
            return null;
        }

        CitaResponseDTO citaResponseDTO = new CitaResponseDTO();
        citaResponseDTO.setId(cita.getId());
        citaResponseDTO.setNombreMascota(cita.getMascota().getNombre());
        citaResponseDTO.setNombreVeterinario(cita.getVeterinario().getUsuario().getNombre());
        citaResponseDTO.setFechaHora(cita.getFechaHora());
        citaResponseDTO.setMotivo(cita.getMotivo());
        citaResponseDTO.setEstado(cita.getEstado().name());

        return citaResponseDTO;
    }

    public static Cita toEntity(CitaDTO citaDTO, Mascota mascota, Veterinario veterinario, Servicio servicio) {
        if(citaDTO == null || mascota == null || veterinario == null) {
            return null;
        }

        Cita cita = new Cita();
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        cita.setServicio(servicio);
        cita.setFechaHora(citaDTO.getFechaHora());
        cita.setMotivo(citaDTO.getMotivo());
        cita.setEstado(EstadoCita.RESERVADA);

        return cita;
    }
}
