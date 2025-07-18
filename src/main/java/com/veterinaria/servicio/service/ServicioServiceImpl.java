package com.veterinaria.servicio.service;

import com.veterinaria.servicio.dto.ServicioDTO;
import com.veterinaria.servicio.mapper.ServicioMapper;
import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.servicio.repository.ServicioRepository;
import com.veterinaria.veterinario.model.Veterinario;
import com.veterinaria.veterinario.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;
    private final VeterinarioRepository veterinarioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository, VeterinarioRepository veterinarioRepository) {
        this.servicioRepository = servicioRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public ServicioDTO crearServicio(ServicioDTO servicioDTO) {
        Veterinario veterinario = veterinarioRepository.findById(servicioDTO.getVeterinarioId())
                .orElseThrow(()-> new RuntimeException("EL VETERINARIO NO FUE ENCONTRADO"));

        Servicio servicio = ServicioMapper.toEntity(servicioDTO, veterinario);
        Servicio servicioGuardado = servicioRepository.save(servicio);
        return ServicioMapper.toDTO(servicioGuardado);
    }

    @Override
    public List<Servicio> listarServicios() {
        return servicioRepository.findAll();
    }

    @Override
    public List<Servicio> listarPorVeterinario(Long veterinarioId) {
        return servicioRepository.findByVeterinarioId(veterinarioId);
    }

    @Override
    public Optional<Servicio> obtenerPorId(Long id) {
        return servicioRepository.findById(id);
    }
}
