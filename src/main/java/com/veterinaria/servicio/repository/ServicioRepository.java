package com.veterinaria.servicio.repository;

import com.veterinaria.servicio.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {
    List<Servicio> findByVeterinarioId(Long veterinarioId);
}
