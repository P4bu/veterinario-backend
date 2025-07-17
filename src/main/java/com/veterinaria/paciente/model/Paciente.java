package com.veterinaria.paciente.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.veterinaria.auth.model.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime fechaRegistro;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    public Paciente() {
    }

    public Paciente(Long id, LocalDateTime fechaRegistro, Usuario usuario) {
        this.id = id;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", fechaRegistro=" + fechaRegistro +
                ", usuario=" + usuario +
                '}';
    }
}
