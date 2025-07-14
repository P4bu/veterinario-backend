package com.veterinaria.cita.repository;

import com.veterinaria.cita.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitaRepository  extends JpaRepository<Cita, Long> {
    List<Cita> findByVeterinarioId(Long veterinarioId);
    List<Cita> findByMascotaId(Long mascotaId);
}
