package com.veterinaria.veterinario.controller;

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
    public ResponseEntity<List<Veterinario>> listarVeterinario() {
        return ResponseEntity.ok(veterinarioService.obtenerVeterinarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Veterinario> obtenerPorId(@PathVariable Long id) {
        return veterinarioService.obtenerVeterinarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/crear")
    public ResponseEntity<Veterinario> crearVeterinario(@RequestBody Veterinario veterinario) {
        return ResponseEntity.ok(veterinarioService.crearVeterinario(veterinario));
    }

}
