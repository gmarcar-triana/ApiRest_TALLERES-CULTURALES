package com.salesianostriana.dam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

}


/*
id (Long, PK).
titulo (String, único).
fechaHoraInicio (LocalDateTime).
fechaHoraFin (LocalDateTime).
Asociación: Pertenece a un Organizador y
puede tener muchos Participantes.
 */