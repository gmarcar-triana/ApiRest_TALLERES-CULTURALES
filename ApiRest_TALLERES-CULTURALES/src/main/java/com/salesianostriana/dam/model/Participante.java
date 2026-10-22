package com.salesianostriana.dam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participante {

    @Id @GeneratedValue
    private Long id;
    private String nombreCompleto;

    @ManyToMany
    private Set<Taller> talleres = new HashSet<>();




}

/*
id (Long, PK).
nombreCompleto (String).
Asociación: Un Participante puede inscribirse
en muchos Talleres y un Taller puede tener muchos Participantes (M:M).

 */