package com.co.sofka.biblioteca.repository;

import com.co.sofka.biblioteca.collection.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecursoRepository extends MongoRepository<Recurso, String> {
    public List<Recurso> findByNombre(String nombre);
}
