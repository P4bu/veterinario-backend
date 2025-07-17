package com.veterinaria.ingreso.service;

import com.veterinaria.cita.model.Cita;
import com.veterinaria.ingreso.model.Ingreso;

import java.time.LocalDateTime;
import java.util.List;

public interface IngresoService {
    Ingreso registrarIngreso(Ingreso ingreso);
    List<Ingreso> listarIngresosPorVeterinario(Long veterinarioId);
    List<Ingreso> listarIngresosPorRango(Long veterinarioId, LocalDateTime inicio, LocalDateTime fin);
    Double calcularTotalIngresos(Long veterinarioId);
    void registrarIngresoDesdeCita(Cita cita);
}
