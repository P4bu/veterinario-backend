package com.veterinaria.fichaMedica.service;

import com.veterinaria.fichaMedica.dto.FichaMedicaDTO;
import com.veterinaria.fichaMedica.dto.FichaMedicaResponseDTO;
import com.veterinaria.fichaMedica.mapper.FichaMedicaMapper;
import com.veterinaria.fichaMedica.model.FichaMedica;
import com.veterinaria.fichaMedica.repository.FichaMedicaRepository;
import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.repository.MascotaRepository;
import com.veterinaria.veterinario.model.Veterinario;
import com.veterinaria.veterinario.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FichaMedicaServiceImpl implements FichaMedicaService {

    private final FichaMedicaRepository fichaMedicaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public FichaMedicaServiceImpl(FichaMedicaRepository fichaMedicaRepository, MascotaRepository mascotaRepository, VeterinarioRepository veterinarioRepository) {
        this.fichaMedicaRepository = fichaMedicaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    @Override
    public FichaMedicaResponseDTO crearFichaMedica(FichaMedicaDTO fichaMedicaDTO) {
        Mascota mascota = mascotaRepository.findById(fichaMedicaDTO.getMascotaId())
                .orElseThrow(() -> new RuntimeException("MASCOTA NO ENCONTRADA"));

        Veterinario veterinario = veterinarioRepository.findById(fichaMedicaDTO.getVeterinarioId())
                .orElseThrow(() -> new RuntimeException("VETERINARIO NO ENCONTRADO"));

        FichaMedica fichaMedica = FichaMedicaMapper.toEntity(fichaMedicaDTO, mascota, veterinario);
        fichaMedicaRepository.save(fichaMedica);

        return FichaMedicaMapper.toDTO(fichaMedica);

    }

    @Override
    public List<FichaMedicaResponseDTO> listarFichasPorMascotas(Long mascotaId) {
        List<FichaMedica> fichas = fichaMedicaRepository.findByMascotaId(mascotaId);

        return fichas.stream().map(FichaMedicaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<FichaMedicaResponseDTO> obtenerPorId(Long id) {
        Optional<FichaMedica> ficha = fichaMedicaRepository.findById(id);

        return  ficha.map(FichaMedicaMapper::toDTO);
    }

    @Override
    public List<FichaMedicaResponseDTO> listarFichasPorVeterinario(Long veterinarioId) {
        List<FichaMedica> fichas = fichaMedicaRepository.findByVeterinarioId(veterinarioId);

        return fichas.stream().map(FichaMedicaMapper::toDTO).collect(Collectors.toList());
    }
}
