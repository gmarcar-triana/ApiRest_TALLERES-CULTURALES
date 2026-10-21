package com.salesianostriana.dam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Participante {

    @Id @GeneratedValue
    private Long id;
    private Stroke nombreCompleto;


}

/*
id (Long, PK).
nombreCompleto (String).
Asociación: Un Participante puede inscribirse
en muchos Talleres y un Taller puede tener muchos Participantes (M:M).

 */