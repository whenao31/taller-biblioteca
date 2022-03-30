package com.co.sofka.biblioteca.dto;

import com.co.sofka.biblioteca.utils.AreaTematica;
import com.co.sofka.biblioteca.utils.TipoRecurso;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RecursoDTO {

    private String id;
    @NotBlank
    private String nombre;
    @NotBlank
    private TipoRecurso tipoRecurso;
    @NotBlank
    private AreaTematica areaTematica;
    private Boolean prestado = false;
    private String fechaPrestamo;

    public RecursoDTO() {
    }

    public RecursoDTO(String id, String nombre, TipoRecurso tipoRecurso, AreaTematica areaTematica, Boolean prestado, String fechaPrestamo) {
        this.id = id;
        this.nombre = nombre;
        this.tipoRecurso = tipoRecurso;
        this.areaTematica = areaTematica;
        this.prestado = prestado;
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public AreaTematica getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(AreaTematica areaTematica) {
        this.areaTematica = areaTematica;
    }

    public Boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(Boolean prestado) {
        this.prestado = prestado;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecursoDTO)) return false;
        RecursoDTO that = (RecursoDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && tipoRecurso == that.tipoRecurso && areaTematica == that.areaTematica && Objects.equals(prestado, that.prestado) && Objects.equals(fechaPrestamo, that.fechaPrestamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, tipoRecurso, areaTematica, prestado, fechaPrestamo);
    }

    @Override
    public String toString() {
        return "RecursoDTO{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoRecurso=" + tipoRecurso +
                ", areaTematica=" + areaTematica +
                ", prestado=" + prestado +
                ", fechaPrestamo='" + fechaPrestamo + '\'' +
                '}';
    }
}
