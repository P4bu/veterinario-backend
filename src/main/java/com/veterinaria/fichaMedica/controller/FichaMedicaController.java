package com.veterinaria.fichaMedica.controller;

import com.veterinaria.fichaMedica.dto.FichaMedicaDTO;
import com.veterinaria.fichaMedica.dto.FichaMedicaResponseDTO;
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
    public ResponseEntity<FichaMedicaResponseDTO> crearFicha(@RequestBody FichaMedicaDTO fichaMedicaDTO) {
        return ResponseEntity.ok(fichaMedicaService.crearFichaMedica(fichaMedicaDTO));
    }

    @GetMapping("/listarPorMascota/{mascotaId}")
    public ResponseEntity<List<FichaMedicaResponseDTO>> listarPorMascota(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(fichaMedicaService.listarFichasPorMascotas(mascotaId));
    }

    @GetMapping("/ficha/{id}")
    public ResponseEntity<FichaMedicaResponseDTO> obtenerFicha(@PathVariable Long id){
        return fichaMedicaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/listarPorVeterinario/{veterinarioId}")
    public ResponseEntity<List<FichaMedicaResponseDTO>> listarPorVeterinario(@PathVariable Long veterinarioId) {
        return ResponseEntity.ok(fichaMedicaService.listarFichasPorVeterinario(veterinarioId));
    }

}
