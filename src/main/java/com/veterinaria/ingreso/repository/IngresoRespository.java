package com.veterinaria.ingreso.repository;

import com.veterinaria.ingreso.model.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IngresoRespository extends JpaRepository<Ingreso, Long> {
    List<Ingreso> findByVeterinarioId(Long veterinarioId);
    List<Ingreso> findByVeterinarioIdAndFechaBetween(Long veterinarioId, LocalDateTime inicio, LocalDateTime fin);
    @Query("select count(i) > 0 from Ingreso i " +
            "where i.veterinario.id = :veterinarioId " +
            "and i.servicio.id = :servicioId " +
            "and DATE(i.fecha) = current_date")
    boolean existsIngresoParaHoy(@Param("veterinarioId") Long veterinarioId,
                                 @Param("servicioId") Long servicioId);
}
