package com.veterinaria.servicio.controller;

import com.veterinaria.servicio.dto.ServicioDTO;
import com.veterinaria.servicio.dto.ServicioResponseDTO;
import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.servicio.service.ServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<ServicioResponseDTO>> listarServicios() {
        return ResponseEntity.ok(servicioService.listarServicios());
    }

    @PostMapping("/crearServicio")
    public ResponseEntity<ServicioDTO> crearServicio(@RequestBody ServicioDTO servicioDTO) {
        return ResponseEntity.ok(servicioService.crearServicio(servicioDTO));
    }

    @GetMapping("/servicioPorId/{id}")
    public ResponseEntity<ServicioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return servicioService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/servicioPorVeterinario/{veterinarioId}")
    public ResponseEntity<List<ServicioResponseDTO>> servicioPorVeterinario(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(servicioService.listarPorVeterinario(veterinarioId));
    }

}
