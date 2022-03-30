package com.co.sofka.biblioteca.collection;

import com.co.sofka.biblioteca.utils.AreaTematica;
import com.co.sofka.biblioteca.utils.TipoRecurso;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "recursos")
public class Recurso {

    @Id
    private String id = UUID.randomUUID().toString().substring(0, 10);
    private String nombre;
    private TipoRecurso tipoRecurso;
    private AreaTematica areaTematica;
    private Boolean prestado = false;
    private String fechaPrestamo;

    public Recurso() {
    }

    public Recurso(String id, String nombre, TipoRecurso tipoRecurso, AreaTematica areaTematica, Boolean prestado, String fechaPrestamo) {
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
    public String toString() {
        return "Recurso{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipoRecurso=" + tipoRecurso +
                ", areaTematica=" + areaTematica +
                ", prestado=" + prestado +
                ", fechaPrestamo='" + fechaPrestamo + '\'' +
                '}';
    }
}
