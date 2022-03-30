package com.co.sofka.biblioteca.mapper;

import com.co.sofka.biblioteca.collection.Recurso;
import com.co.sofka.biblioteca.dto.RecursoDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(RecursoDTO recursoDTO){
        return new Recurso(
                recursoDTO.getId(),
                recursoDTO.getNombre(),
                recursoDTO.getTipoRecurso(),
                recursoDTO.getAreaTematica(),
                recursoDTO.isPrestado(),
                recursoDTO.getFechaPrestamo()
        );
    }

    public RecursoDTO fromCollection(Recurso recurso){
        return new RecursoDTO(
                recurso.getId(),
                recurso.getNombre(),
                recurso.getTipoRecurso(),
                recurso.getAreaTematica(),
                recurso.isPrestado(),
                recurso.getFechaPrestamo()
        );
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection){
        if (collection == null){
            return null;
        }
        List<RecursoDTO> list = new ArrayList<>(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Recurso recurso = (Recurso) listTracks.next();
            list.add(fromCollection(recurso));
        }

        return list;
    }
}
