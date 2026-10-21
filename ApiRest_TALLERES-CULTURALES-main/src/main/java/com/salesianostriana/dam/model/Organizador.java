package com.salesianostriana.dam.model;

import com.sun.jdi.StringReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organizador {

    @Id @GeneratedValue
    private Long id;
    private String nombre;
    private String email;

}


/*
id (Long, PK).
nombre (String).
email (String).
Asociación: Un Organizador puede organizar muchos Talleres.
Cada Taller debe tener un único Organizador.

*/