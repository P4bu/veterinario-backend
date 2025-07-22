package com.veterinaria.ingreso.model;

import com.veterinaria.cita.model.Cita;
import com.veterinaria.servicio.model.Servicio;
import com.veterinaria.veterinario.model.Veterinario;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingreso")
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double monto;
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "veterinario_id", nullable = false)
    private Veterinario veterinario;

    @ManyToOne
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    @OneToOne
    @JoinColumn(name = "cita_id")
    private Cita cita;

    public Ingreso(){}

    public Ingreso(Long id, Double monto, LocalDateTime fecha, Veterinario veterinario, Servicio servicio, Cita cita) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.veterinario = veterinario;
        this.servicio = servicio;
        this.cita = cita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }
}
