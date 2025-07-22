package com.veterinaria.ingreso.service;

import com.veterinaria.cita.model.Cita;
import com.veterinaria.ingreso.dto.IngresoDTO;
import com.veterinaria.ingreso.mapper.IngresoMapper;
import com.veterinaria.ingreso.model.Ingreso;
import com.veterinaria.ingreso.repository.IngresoRespository;
import com.veterinaria.servicio.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IngresoServiceImpl implements IngresoService {

    private final IngresoRespository ingresoRespository;
    private final ServicioRepository servicioRepository;

    public IngresoServiceImpl(IngresoRespository ingresoRespository, ServicioRepository servicioRepository) {
        this.ingresoRespository = ingresoRespository;
        this.servicioRepository = servicioRepository;
    }

    @Override
    public Ingreso registrarIngreso(Ingreso ingreso) {
        return ingresoRespository.save(ingreso);
    }

    @Override
    public List<Ingreso> listarIngresosPorVeterinario(Long veterinarioId) {
        return ingresoRespository.findByVeterinarioId(veterinarioId);
    }

    @Override
    public List<Ingreso> listarIngresosPorRango(Long veterinarioId, LocalDateTime inicio, LocalDateTime fin) {
        return ingresoRespository.findByVeterinarioIdAndFechaBetween(veterinarioId, inicio, fin);
    }

    @Override
    public Double calcularTotalIngresos(Long veterinarioId) {
        return ingresoRespository.findByVeterinarioId(veterinarioId)
                .stream()
                .mapToDouble(Ingreso::getMonto)
                .sum();
    }

    @Override
    public IngresoDTO registrarIngresoDesdeCita(Cita cita) {
        if(cita == null || cita.getServicio() == null || cita.getVeterinario() == null) {
            throw new IllegalArgumentException("CITA, SERVICIO O VETERINARIO NO VALIDO PARA GENERAR INGRESO");
        }

        Ingreso ingresoGuardado = new Ingreso();
        ingresoGuardado.setMonto(cita.getServicio().getPrecio());
        ingresoGuardado.setFecha(cita.getFechaHora());
        ingresoGuardado.setServicio(cita.getServicio());
        ingresoGuardado.setVeterinario(cita.getVeterinario());
        ingresoGuardado.setCita(cita);

        ingresoGuardado = ingresoRespository.save(ingresoGuardado);

        System.out.println("Servicio: " + cita.getServicio());
        System.out.println("Precio: " + cita.getServicio().getPrecio());
        return IngresoMapper.toDTO(ingresoGuardado);

    }
}
