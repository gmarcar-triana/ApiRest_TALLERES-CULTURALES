package com.salesianostriana.dam.model;

import jakarta.persistence.*;
import jakarta.servlet.http.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Taller {

    @Id @GeneratedValue
    private Long id;
    private String titulo;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    @ManyToOne
    private Organizador organizador;

    @ManyToMany
    private HashSet<Participante> participantes = new HashSet<>();

    //@ManyToMany
    //private List<Participante> participantes = new ArrayList<>();

}


/*
id (Long, PK).
titulo (String, único).
fechaHoraInicio (LocalDateTime).
fechaHoraFin (LocalDateTime).
Asociación: Pertenece a un Organizador y
puede tener muchos Participantes.
 */