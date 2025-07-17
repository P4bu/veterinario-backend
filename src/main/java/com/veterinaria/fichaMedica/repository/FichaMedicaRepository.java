package com.veterinaria.fichaMedica.repository;

import com.veterinaria.fichaMedica.model.FichaMedica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FichaMedicaRepository extends JpaRepository<FichaMedica, Long> {
    List<FichaMedica> findByMascotaId(Long mascotaId);
}
