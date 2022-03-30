package com.co.sofka.biblioteca.controller;

import com.co.sofka.biblioteca.collection.Recurso;
import com.co.sofka.biblioteca.dto.RecursoDTO;
import com.co.sofka.biblioteca.service.ServiceRecurso;
import com.co.sofka.biblioteca.utils.AreaTematica;
import com.co.sofka.biblioteca.utils.TipoRecurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/biblioteca/recurso")
public class RecursoController {

    @Autowired
    ServiceRecurso serviceRecurso;

    @GetMapping
    public ResponseEntity<List<RecursoDTO>> findAll(){
        return new ResponseEntity<List<RecursoDTO>>(serviceRecurso.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoDTO> findbyId(@PathVariable("id") String id){
        return new ResponseEntity<RecursoDTO>(serviceRecurso.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecursoDTO> save(@RequestBody RecursoDTO recursoDTO){
        return new ResponseEntity<RecursoDTO>(serviceRecurso.save(recursoDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RecursoDTO> update(@RequestBody RecursoDTO recursoDTO){
        if (recursoDTO.getId() != null){
            return new ResponseEntity<RecursoDTO>(serviceRecurso.save(recursoDTO), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        try {
            serviceRecurso.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/disponibilidad/{nombre}")
    public ResponseEntity<String> checkAvailability(@PathVariable("nombre") String nombre){
        return new ResponseEntity<String>(serviceRecurso.checkAvailability(nombre), HttpStatus.OK);
    }

    @PutMapping("/prestar/{id}")
    public ResponseEntity<String> lend(@PathVariable("id") String id){
        return new ResponseEntity<String>(serviceRecurso.lend(id), HttpStatus.OK);
    }

    @GetMapping("/recomendar/{tematica}/{tipo}")
    public ResponseEntity<List<RecursoDTO>> recommend(@PathVariable("tematica") AreaTematica tematica, @PathVariable("tipo") TipoRecurso tipo){
        return new ResponseEntity<List<RecursoDTO>>(serviceRecurso.recommend(tematica, tipo), HttpStatus.OK);
    }

    @PutMapping("/devolver/{id}")
    public ResponseEntity<String> returnResource(@PathVariable("id") String id){
        return new ResponseEntity<String>(serviceRecurso.returnResource(id), HttpStatus.OK);
    }
}
