package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Participante;

public record GetParticipanteList(
        Long id,
        String nombreCompleto
) {

    public static GetParticipanteList of(Participante p) {
        return new GetParticipanteList(p.getId(), p.getNombreCompleto());
    }
}