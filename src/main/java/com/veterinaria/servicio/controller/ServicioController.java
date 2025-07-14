package com.veterinaria.servicio.controller;

import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.servicio.service.ServicioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicio")
public class ServicioController {

    private final ServicioServiceImpl servicioServiceImpl;

    public ServicioController(ServicioServiceImpl servicioServiceImpl) {
        this.servicioServiceImpl = servicioServiceImpl;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Servicio>> listarServicios() {
        return ResponseEntity.ok(servicioServiceImpl.listarServicios());
    }

    @PostMapping("/crearServicio")
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioServiceImpl.crearServicio(servicio));
    }

    @GetMapping("/servicioPorId/{id}")
    public ResponseEntity<Servicio> obtenerPorId(@PathVariable Long id) {
        return servicioServiceImpl.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Servicio>> servicioPorVeterinario(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(servicioServiceImpl.listarPorVeterinario(veterinarioId));
    }

}
