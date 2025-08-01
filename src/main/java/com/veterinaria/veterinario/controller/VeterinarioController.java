package com.veterinaria.veterinario.controller;

import com.veterinaria.veterinario.dto.VeterinarioDTO;
import com.veterinaria.veterinario.dto.VeterinarioResponseDTO;
import com.veterinaria.veterinario.model.Veterinario;
import com.veterinaria.veterinario.service.VeterinarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<VeterinarioResponseDTO>> listarVeterinario() {
        return ResponseEntity.ok(veterinarioService.obtenerVeterinarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return veterinarioService.obtenerVeterinarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Veterinario> crearVeterinario(@RequestBody VeterinarioDTO veterinarioDTO) {
        return ResponseEntity.ok(veterinarioService.crearVeterinario(veterinarioDTO));
    }

}
