package com.veterinaria.servicio.mapper;

import com.veterinaria.servicio.dto.ServicioDTO;
import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.veterinario.model.Veterinario;

public class ServicioMapper {

    public static Servicio toEntity(ServicioDTO servicioDTO, Veterinario veterinario) {

        Servicio servicio = new Servicio();
        servicio.setNombre(servicioDTO.getNombre());
        servicio.setDescripcion(servicioDTO.getDescripcion());
        servicio.setDuracionMinutos(servicioDTO.getDuracionMinutos());
        servicio.setPrecio(servicioDTO.getPrecio());
        servicio.setVeterinario(veterinario);

        return servicio;
    }

    public static ServicioDTO toDTO(Servicio servicio) {

        ServicioDTO dto = new ServicioDTO();
        dto.setNombre(servicio.getNombre());
        dto.setDescripcion(servicio.getDescripcion());
        dto.setDuracionMinutos(servicio.getDuracionMinutos());
        dto.setPrecio(servicio.getPrecio());
        dto.setVeterinarioId(servicio.getVeterinario().getId());

        return dto;
    }

}
