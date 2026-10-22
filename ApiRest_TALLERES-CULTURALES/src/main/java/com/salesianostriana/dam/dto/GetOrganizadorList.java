package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Organizador;

public record GetOrganizadorList(
        Long id,
        String nombre,
        String email
) {

    public static GetOrganizadorList of(Organizador o) {
        return new GetOrganizadorList(o.getId(), o.getNombre(), o.getEmail());
    }
}