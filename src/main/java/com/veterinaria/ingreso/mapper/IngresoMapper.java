package com.veterinaria.ingreso.mapper;

import com.veterinaria.cita.model.Cita;
import com.veterinaria.ingreso.dto.IngresoDTO;
import com.veterinaria.ingreso.model.Ingreso;
import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.veterinario.model.Veterinario;

public class IngresoMapper {

    public static IngresoDTO toDTO(Ingreso ingreso) {

        IngresoDTO ingresoDTO = new IngresoDTO();
        ingresoDTO.setId(ingreso.getId());
        ingresoDTO.setMonto(ingreso.getMonto());
        ingresoDTO.setFecha(ingreso.getFecha());
        ingresoDTO.setServicioId(ingreso.getServicio().getId());
        ingresoDTO.setVeterinarioId(ingreso.getVeterinario().getId());
        ingresoDTO.setCitaId(ingreso.getCita() != null ? ingreso.getCita().getId() : null);

        return ingresoDTO;
    }

    public static Ingreso toEntity(IngresoDTO ingresoDTO, Servicio servicio, Veterinario veterinario, Cita cita) {

        Ingreso ingreso = new Ingreso();
        ingreso.setMonto(ingresoDTO.getMonto());
        ingreso.setFecha(ingresoDTO.getFecha());
        ingreso.setServicio(servicio);
        ingreso.setVeterinario(veterinario);
        ingreso.setCita(cita);

        return ingreso;
    }
}
