package com.veterinaria.fichaMedica.mapper;

import com.veterinaria.fichaMedica.dto.FichaMedicaDTO;
import com.veterinaria.fichaMedica.dto.FichaMedicaResponseDTO;
import com.veterinaria.fichaMedica.model.FichaMedica;
import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.veterinario.model.Veterinario;

public class FichaMedicaMapper {

    public static FichaMedicaResponseDTO toDTO(FichaMedica fichaMedica) {

        FichaMedicaResponseDTO fichaMedicaResponseDTO = new FichaMedicaResponseDTO();

        fichaMedicaResponseDTO.setId(fichaMedica.getId());
        fichaMedicaResponseDTO.setMotivoConsulta(fichaMedica.getMotivoConsulta());
        fichaMedicaResponseDTO.setDiagnostico(fichaMedica.getDiagnostico());
        fichaMedicaResponseDTO.setTratamiento(fichaMedica.getTratamiento());
        fichaMedicaResponseDTO.setFecha(fichaMedica.getFecha());

        fichaMedicaResponseDTO.setNombreMascota(fichaMedica.getMascota().getNombre());

        fichaMedicaResponseDTO.setNombreVeterinario(fichaMedica.getVeterinario().getUsuario().getNombre()
                + " " + fichaMedica.getVeterinario().getUsuario().getApellidoPaterno()
                + " " + fichaMedica.getVeterinario().getUsuario().getApellidoMaterno());

        return fichaMedicaResponseDTO;
    }

    public static FichaMedica toEntity(FichaMedicaDTO fichaMedicaDTO, Mascota mascota, Veterinario veterinario) {

        FichaMedica fichaMedica = new FichaMedica();

        fichaMedica.setMotivoConsulta(fichaMedicaDTO.getMotivoConsulta());
        fichaMedica.setDiagnostico(fichaMedicaDTO.getDiagnostico());
        fichaMedica.setTratamiento(fichaMedicaDTO.getTratamiento());
        fichaMedica.setFecha(fichaMedicaDTO.getFecha());

        fichaMedica.setMascota(mascota);
        fichaMedica.setVeterinario(veterinario);

        return fichaMedica;

    }
}
