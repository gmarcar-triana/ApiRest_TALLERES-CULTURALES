package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Organizador;
import com.salesianostriana.dam.model.Taller;

import java.util.List;

public record GetOrganizadorDetail(
        Long id,
        String nombre
        String email,
        List<>
) {
    public static GetOrganizadorDetail from(Organizador organizador) {
        return new GetOrganizadorDetail(
                organizador.getId(),
                organizador.getNombre()
        )
    }

}
