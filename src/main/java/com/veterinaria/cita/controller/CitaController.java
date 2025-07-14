package com.veterinaria.cita.controller;

import com.veterinaria.cita.model.Cita;
import com.veterinaria.cita.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<Cita> crearCita(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.crearCita(cita));
    }

    @GetMapping("/listarCitas")
    public ResponseEntity<List<Cita>> listarCitas() {
        return ResponseEntity.ok(citaService.listarCitas());
    }

    @GetMapping("/listarPorVeterinario/{veterinarioId}")
    public ResponseEntity<List<Cita>> listarPorVeterinario(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(citaService.listarPorVeterinario(veterinarioId));
    }

    @GetMapping("/listarPorMascota/{mascotaId}")
    public ResponseEntity<List<Cita>> listarPorMascota(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(citaService.listarPorMascota(mascotaId));
    }

    @GetMapping("/obetenerPorId/{id}")
    public ResponseEntity<Cita> obtenerPorId(@PathVariable Long id) {
        return citaService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
