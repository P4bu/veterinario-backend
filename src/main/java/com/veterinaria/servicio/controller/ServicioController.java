package com.veterinaria.servicio.controller;

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
    public ResponseEntity<List<Servicio>> listarServicios() {
        return ResponseEntity.ok(servicioService.listarServicios());
    }

    @PostMapping("/crearServicio")
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioService.crearServicio(servicio));
    }

    @GetMapping("/servicioPorId/{id}")
    public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id) {
        return servicioService.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Servicio>> servicioPorVeterinario(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(servicioService.listarPorVeterinario(veterinarioId));
    }

}
