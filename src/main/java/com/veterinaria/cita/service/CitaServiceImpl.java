package com.veterinaria.cita.service;

import com.veterinaria.cita.dto.CitaDTO;
import com.veterinaria.cita.dto.CitaResponseDTO;
import com.veterinaria.cita.mapper.CitaMapper;
import com.veterinaria.cita.model.Cita;
import com.veterinaria.cita.model.EstadoCita;
import com.veterinaria.cita.repository.CitaRepository;
import com.veterinaria.ingreso.model.Ingreso;
import com.veterinaria.ingreso.repository.IngresoRespository;
import com.veterinaria.ingreso.service.IngresoService;
import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.repository.MascotaRepository;
import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.servicio.repository.ServicioRepository;
import com.veterinaria.veterinario.model.Veterinario;
import com.veterinaria.veterinario.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService{

    private final CitaRepository citaRepository;
    private final IngresoService ingresoService;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final ServicioRepository servicioRepository;
    private final IngresoRespository ingresoRespository;

    public CitaServiceImpl(CitaRepository citaRepository, IngresoService ingresoService, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository, ServicioRepository servicioRepository, IngresoRespository ingresoRespository) {
        this.citaRepository = citaRepository;
        this.ingresoService = ingresoService;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.servicioRepository = servicioRepository;
        this.ingresoRespository = ingresoRespository;
    }

    @Override
    public CitaResponseDTO crearCita(CitaDTO citaDTO) {
        Mascota mascota = mascotaRepository.findById(citaDTO.getMascotaId())
                .orElseThrow(() -> new RuntimeException("MASCOTA NO ENCONTRADA EN LA BASE DE DATOS"));

        Veterinario veterinario = veterinarioRepository.findById(citaDTO.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("VETERINARIO NO ENCONTRADO EN LA BASE DE DATOS"));

        Servicio servicio = servicioRepository.findById(citaDTO.getServicioId())
                .orElseThrow(() -> new RuntimeException("SERVICIO NO ENCONTRADO EN LA BASE DE DATOS"));

        Cita citaGuardada = citaRepository.save(CitaMapper.toEntity(citaDTO, mascota, veterinario, servicio));

        return CitaMapper.toDTO(citaGuardada);
    }

    @Override
    public Cita modificarEstado(Long citaId, String nuevoEstado) {
        Optional<Cita> optionalCita = citaRepository.findById(citaId);
        if(optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            EstadoCita estadoAnterior = cita.getEstado();
            EstadoCita estadoNuevo = EstadoCita.valueOf(nuevoEstado.toUpperCase());

            cita.setEstado(estadoNuevo);
            Cita citaActualizada = citaRepository.save(cita);

            if(estadoNuevo == EstadoCita.ATENDIDA && estadoAnterior != EstadoCita.ATENDIDA){
                ingresoService.registrarIngresoDesdeCita(citaActualizada);
            }

            return citaActualizada;
        }
        throw new RuntimeException("CITA NO ENCONTRADA");
    }

    @Override
    public List<CitaResponseDTO> listarCitas() {
        List<Cita> citas = citaRepository.findAll();

        return citas.stream().map(CitaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CitaResponseDTO> listarPorVeterinario(Long veterinarioId) {
        List<Cita> citas = citaRepository.findByVeterinarioId(veterinarioId);

        return citas.stream().map(CitaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<CitaResponseDTO> listarPorMascota(Long mascotaId) {
        List<Cita> citas = citaRepository.findByMascotaId(mascotaId);

        return citas.stream().map(CitaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CitaResponseDTO> obtenerPorId(Long id) {
        Optional<Cita> cita = citaRepository.findById(id);

        return cita.map(CitaMapper::toDTO);
    }
}
