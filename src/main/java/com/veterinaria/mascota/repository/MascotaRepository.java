package com.veterinaria.mascota.repository;

import com.veterinaria.mascota.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByPacienteId(Long pacienteId);
}
