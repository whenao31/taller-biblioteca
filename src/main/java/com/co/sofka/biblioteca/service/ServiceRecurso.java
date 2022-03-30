package com.co.sofka.biblioteca.service;

import com.co.sofka.biblioteca.collection.Recurso;
import com.co.sofka.biblioteca.dto.RecursoDTO;
import com.co.sofka.biblioteca.mapper.RecursoMapper;
import com.co.sofka.biblioteca.repository.RecursoRepository;
import com.co.sofka.biblioteca.utils.AreaTematica;
import com.co.sofka.biblioteca.utils.TipoRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceRecurso {
    @Autowired
    RecursoRepository recursoRepository;

    RecursoMapper mapper = new RecursoMapper();

    public List<RecursoDTO> findAll(){
        List<Recurso> recurso = recursoRepository.findAll();
        return mapper.fromCollectionList(recurso);
    }

    public RecursoDTO findById(String id){
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(recurso);
    }

    public RecursoDTO save(RecursoDTO recursoDTO){
        Recurso recurso = mapper.fromDTO(recursoDTO);
        return mapper.fromCollection(recursoRepository.save(recurso));
    }

    public RecursoDTO update(RecursoDTO recursoDTO){
        Recurso recurso = mapper.fromDTO(recursoDTO);
        recursoRepository.findById(recurso.getId())
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        return mapper.fromCollection(recursoRepository.save(recurso));
    }

    public void delete(String id){
        recursoRepository.deleteById(id);
    }

    public String checkAvailability(String nombre){
        List<Recurso> recursos = recursoRepository.findByNombre(nombre);
        if (recursos.size() < 0){ throw new RuntimeException("Recurso no encontrado"); }
        if (recursos.stream().filter(recurso -> !recurso.isPrestado()).count() > 0){
            return String.format("El recurso %s si esta disponible.", nombre);
        }
        var ultimoRecurso = recursos.stream()
                .filter(recurso -> recurso.isPrestado())
                .sorted(Comparator.comparing(Recurso::getFechaPrestamo))
                .collect(Collectors.toList()).get(0);
        return String.format("El recurso %s con id %s NO esta disponible.\n La fecha de prestamo fue %s"
                        , ultimoRecurso.getNombre(), ultimoRecurso.getId(), ultimoRecurso.getFechaPrestamo());
    }

    public String lend(String id){
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        if (!recurso.isPrestado()){
            recurso.setPrestado(true);
            recurso.setFechaPrestamo(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            save(mapper.fromCollection(recurso));
            return String.format("Prestamo exitoso de recurso con id %s.", id);
        }
        return String.format("El recurso con id %s ya se encuentra en prestamo", id);
    }

    public List<RecursoDTO> recommend(AreaTematica areaTematica, TipoRecurso tipoRecurso){
        return findAll().stream()
                .filter(recursoDTO -> recursoDTO.getTipoRecurso().equals(tipoRecurso) || recursoDTO.getAreaTematica().equals(areaTematica))
                .collect(Collectors.toList());
    }

    public String returnResource(String id){
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        if (recurso.isPrestado()){
            recurso.setPrestado(false);
            save(mapper.fromCollection(recurso));
            return String.format("Retorno exitoso de recurso con id %s.", id);
        }
        return String.format("El recurso con id %s ya se encuentra devuelto.", id);
    }
}
