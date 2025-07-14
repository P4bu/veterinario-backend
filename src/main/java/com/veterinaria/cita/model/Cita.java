package com.veterinaria.cita.model;

import com.veterinaria.mascota.model.Mascota;
import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.veterinario.model.Veterinario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado = EstadoCita.RESERVADA;

    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    public Cita(){}

    public Cita(Long id, EstadoCita estado, LocalDateTime fechaHora, Mascota mascota, Veterinario veterinario, Servicio servicio) {
        this.id = id;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.servicio = servicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora (LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
}
