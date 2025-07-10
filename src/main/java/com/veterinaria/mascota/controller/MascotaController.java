package com.veterinaria.mascota.controller;

import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.mascota.service.MascotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping("/crearMascota/{pacienteId}")
    public ResponseEntity<Mascota> crear(@RequestBody Mascota mascota, @PathVariable Long pacienteId) {
        return ResponseEntity.ok(mascotaService.crearMascota(mascota, pacienteId));
    }

    @GetMapping("/{pacienteId}")
    public ResponseEntity<List<Mascota>> obtenerMascotaPorPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(mascotaService.obtenerMascotaPorPaciente(pacienteId));
    }

}
