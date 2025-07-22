package com.veterinaria.cita.controller;

import com.veterinaria.cita.dto.CitaDTO;
import com.veterinaria.cita.dto.CitaResponseDTO;
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
    public ResponseEntity<CitaResponseDTO> crearCita(@RequestBody CitaDTO citaDTO) {
        return ResponseEntity.ok(citaService.crearCita(citaDTO));
    }

    @GetMapping("/listarCitas")
    public ResponseEntity<List<CitaResponseDTO>> listarCitas() {
        return ResponseEntity.ok(citaService.listarCitas());
    }

    @GetMapping("/listarPorVeterinario/{veterinarioId}")
    public ResponseEntity<List<CitaResponseDTO>> listarPorVeterinario(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(citaService.listarPorVeterinario(veterinarioId));
    }

    @GetMapping("/listarPorMascota/{mascotaId}")
    public ResponseEntity<List<CitaResponseDTO>> listarPorMascota(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(citaService.listarPorMascota(mascotaId));
    }

    @GetMapping("/obetenerPorId/{id}")
    public ResponseEntity<CitaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return citaService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/modificarEstado/{citaId}/nuevoEstado")
    public ResponseEntity<Cita> modificarEstado(@PathVariable Long citaId,
                                                @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(citaService.modificarEstado(citaId, nuevoEstado));
    }

}
