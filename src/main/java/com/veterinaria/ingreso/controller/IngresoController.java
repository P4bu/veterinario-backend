package com.veterinaria.ingreso.controller;

import com.veterinaria.ingreso.model.Ingreso;
import com.veterinaria.ingreso.service.IngresoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoController {

    private final IngresoService ingresoService;

    public IngresoController(IngresoService ingresoService) {
        this.ingresoService = ingresoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Ingreso> registrar(@RequestBody Ingreso ingreso) {
        return ResponseEntity.ok(ingresoService.registrarIngreso(ingreso));
    }

    @GetMapping("/Veterinario/{veterinarioId}")
    public ResponseEntity<List<Ingreso>> listarIngresoPorVeterinario(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(ingresoService.listarIngresosPorVeterinario(veterinarioId));
    }

    @GetMapping("/porRango/{veterinarioId}/rango")
    public ResponseEntity<List<Ingreso>> listarPorFecha(@PathVariable Long veterinarioId,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
                                                        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(ingresoService.listarIngresosPorRango(veterinarioId, inicio, fin));
    }

    @GetMapping("/totalIngreso/{veterinarioId}")
    public ResponseEntity<Double> totalIngreso(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(ingresoService.calcularTotalIngresos(veterinarioId));
    }


}
