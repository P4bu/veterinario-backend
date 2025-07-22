package com.veterinaria.mascota.dto;

import com.veterinaria.shared.PacienteInfoDTO;

public class MascotaResponseDTO {

    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private String fechaRegistro;
    private PacienteInfoDTO paciente;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public PacienteInfoDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteInfoDTO paciente) {
        this.paciente = paciente;
    }
}
