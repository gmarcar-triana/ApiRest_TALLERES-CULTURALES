package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Taller;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

@Schema(name = "GetTallerDetail", description = "Detalle de un taller")
public record GetTallerDetail(
        @Schema(example = "1") Long id,
        @Schema(example = "Cerámica para principiantes") String titulo,
        @Schema(example = "2026-11-10T17:00:00") LocalDateTime fechaHoraInicio,
        @Schema(example = "2026-11-10T19:00:00") LocalDateTime fechaHoraFin,
        GetOrganizadorList organizador,
        List<GetParticipanteList> participantes
) {

    public static GetTallerDetail of(Taller t) {
        return new GetTallerDetail(
                t.getId(),
                t.getTitulo(),
                t.getFechaHoraInicio(),
                t.getFechaHoraFin(),
                t.getOrganizador() != null ? GetOrganizadorList.of(t.getOrganizador()) : null,
                t.getParticipantes() == null
                        ? List.of()
                        : t.getParticipantes().stream().map(GetParticipanteList::of).toList()
        );
    }
}