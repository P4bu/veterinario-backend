package com.veterinaria.cita.service;

import com.veterinaria.cita.model.Cita;
import com.veterinaria.cita.model.EstadoCita;
import com.veterinaria.cita.repository.CitaRepository;
import com.veterinaria.ingreso.service.IngresoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaServiceImpl implements CitaService{

    private final CitaRepository citaRepository;
    private final IngresoService ingresoService;

    public CitaServiceImpl(CitaRepository citaRepository, IngresoService ingresoService) {
        this.citaRepository = citaRepository;
        this.ingresoService = ingresoService;

    }

    @Override
    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public Cita modificarEstado(Long citaId, String nuevoEstado) {
        Optional<Cita> optionalCita = citaRepository.findById(citaId);

        if(optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            EstadoCita estado = EstadoCita.valueOf(nuevoEstado);

            cita.setEstado(estado);

            if(estado == EstadoCita.ATENDIDA) {
                ingresoService.registrarIngresoDesdeCita(cita);
            }

            return citaRepository.save(cita);
        }
        throw new RuntimeException("CITA NO ENCONTRADA");
    }

    @Override
    public List<Cita> listarCitas() {
        return citaRepository.findAll();
    }

    @Override
    public List<Cita> listarPorVeterinario(Long veterinarioId) {
        return citaRepository.findByVeterinarioId(veterinarioId);
    }

    @Override
    public List<Cita> listarPorMascota(Long mascotaId) {
        return citaRepository.findByMascotaId(mascotaId);
    }

    @Override
    public Optional<Cita> obtenerPorId(Long id) {
        return citaRepository.findById(id);
    }
}
