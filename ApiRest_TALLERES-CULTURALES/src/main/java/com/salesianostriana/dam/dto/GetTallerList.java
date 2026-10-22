package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Taller;

import java.time.LocalDateTime;

public record GetTallerList(
        Long id,
        String titulo,
        LocalDateTime fechaHoraInicio,
        LocalDateTime fechaHoraFin,
        String nombreOrganizador
) {
    public static GetTallerList from(Taller taller) {
        return new GetTallerList(
                taller.getId(),
                taller.getTitulo(),
                taller.getFechaHoraInicio(),
                taller.getFechaHoraFin(),
                taller.getOrganizador() != null ? taller.getOr
        )
    }

}
