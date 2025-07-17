package com.veterinaria.veterinario.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.veterinaria.auth.model.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "veterinario")
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String especialidad;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaRegistro;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Veterinario() {
    }

    public Veterinario(Long id, String especialidad, LocalDateTime fechaRegistro, Usuario usuario) {
        this.id = id;
        this.especialidad = especialidad;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
    }

    @PrePersist
    public void prePersist() {
        if(this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "id=" + id +
                ", especialidad='" + especialidad + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuario=" + usuario +
                '}';
    }
}
