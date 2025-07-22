package com.veterinaria.servicio.mapper;

import com.veterinaria.auth.model.Usuario;
import com.veterinaria.servicio.dto.ServicioDTO;
import com.veterinaria.servicio.dto.ServicioResponseDTO;
import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.shared.VeterinarioInfoDTO;
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

    public static ServicioResponseDTO toResponseDTO(Servicio servicio) {
        ServicioResponseDTO servicioResponseDTO = new ServicioResponseDTO();

        servicioResponseDTO.setNombre(servicio.getNombre());
        servicioResponseDTO.setDescripcion(servicio.getDescripcion());
        servicioResponseDTO.setPrecio(servicio.getPrecio());
        servicioResponseDTO.setDuracionMinutos(servicio.getDuracionMinutos());

        Veterinario veterinario = servicio.getVeterinario();
        Usuario usuario = servicio.getVeterinario().getUsuario();

        VeterinarioInfoDTO veterinarioInfoDTO = new VeterinarioInfoDTO();
        veterinarioInfoDTO.setId(veterinario.getId());
        veterinarioInfoDTO.setNombreCompleto(usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno());
        veterinarioInfoDTO.setEspecialidad(veterinario.getEspecialidad());

        servicioResponseDTO.setVeterinarioInfoDTO(veterinarioInfoDTO);

        return servicioResponseDTO;

    }

}
