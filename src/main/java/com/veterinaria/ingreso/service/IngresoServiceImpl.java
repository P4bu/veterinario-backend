package com.veterinaria.ingreso.service;

import com.veterinaria.cita.model.Cita;
import com.veterinaria.ingreso.model.Ingreso;
import com.veterinaria.ingreso.repository.IngresoRespository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IngresoServiceImpl implements IngresoService {

    private final IngresoRespository ingresoRespository;

    public IngresoServiceImpl(IngresoRespository ingresoRespository) {
        this.ingresoRespository = ingresoRespository;
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
    public void registrarIngresoDesdeCita(Cita cita) {
        Long veterinarioId = cita.getVeterinario().getId();
        Long servicioId = cita.getServicio().getId();

        boolean yaRegistrado = ingresoRespository.existsIngresoParaHoy(veterinarioId, servicioId);

        if(yaRegistrado) {
            System.out.print("INGRESO YA REGISTRADO PARA ESTA CITA HOY. NO SE PUEDE DUPLICAR");
            return;
        }

        Ingreso ingreso = new Ingreso();
        ingreso.setFecha(LocalDateTime.now());
        ingreso.setMonto(cita.getServicio().getPrecio());
        ingreso.setVeterinario(cita.getVeterinario());
        ingreso.setServicio(cita.getServicio());

        ingresoRespository.save(ingreso);
    }
}
