package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Taller;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "GetTallerList", description = "Taller en un listado")
public record GetTallerList(
        @Schema(example = "1") Long id,
        @Schema(example = "Cerámica para principiantes") String titulo,
        @Schema(example = "2026-11-10T17:00:00") LocalDateTime fechaHoraInicio,
        @Schema(example = "2026-11-10T19:00:00") LocalDateTime fechaHoraFin
) {

    public static GetTallerList of(Taller t) {
        return new GetTallerList(t.getId(), t.getTitulo(),
                t.getFechaHoraInicio(), t.getFechaHoraFin());
    }
}