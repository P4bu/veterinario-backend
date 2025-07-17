package com.veterinaria.fichaMedica.controller;

import com.veterinaria.fichaMedica.model.FichaMedica;
import com.veterinaria.fichaMedica.service.FichaMedicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fichaMedica")
public class FichaMedicaController {

    private final FichaMedicaService fichaMedicaService;

    public FichaMedicaController(FichaMedicaService fichaMedicaService) {
        this.fichaMedicaService = fichaMedicaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<FichaMedica> crearFicha(@RequestBody FichaMedica fichaMedica) {
        return ResponseEntity.ok(fichaMedicaService.crearFichaMedica(fichaMedica));
    }

    @GetMapping("/listarPorMascota/{mascotaId}")
    public ResponseEntity<List<FichaMedica>> listarPorMascota(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(fichaMedicaService.listarFichasPorMascotas(mascotaId));
    }

    @GetMapping("/ficha/{id}")
    public ResponseEntity<FichaMedica> obtenerFicha(@PathVariable Long id){
        return fichaMedicaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
