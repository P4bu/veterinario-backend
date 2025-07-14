package com.veterinaria.servicio.service;

import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.servicio.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public Servicio crearServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
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
