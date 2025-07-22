package com.veterinaria.veterinario.mapper;

import com.veterinaria.veterinario.dto.VeterinarioResponseDTO;
import com.veterinaria.veterinario.model.Veterinario;

public class VeterinarioMapper {

    public static VeterinarioResponseDTO toResponseDTO(Veterinario veterinario) {
        VeterinarioResponseDTO veterinarioResponseDTO = new VeterinarioResponseDTO();

        veterinarioResponseDTO.setNombre(veterinario.getUsuario().getNombre());
        veterinarioResponseDTO.setApellidoPaterno(veterinario.getUsuario().getApellidoPaterno());
        veterinarioResponseDTO.setApellidoMaterno(veterinario.getUsuario().getApellidoMaterno());
        veterinarioResponseDTO.setEmail(veterinario.getUsuario().getEmail());
        veterinarioResponseDTO.setTelefono(veterinario.getUsuario().getTelefono());
        veterinarioResponseDTO.setRol(veterinario.getUsuario().getRol());
        veterinarioResponseDTO.setEspecialidad(veterinario.getEspecialidad());

        return veterinarioResponseDTO;
    }
}
