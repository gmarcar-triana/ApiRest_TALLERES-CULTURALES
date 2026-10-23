package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Participante;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetParticipanteDetail", description = "Detalle de un participante")
public record GetParticipanteDetail(
        @Schema(example = "1") Long id,
        @Schema(example = "Ana García López") String nombreCompleto
) {

    public static GetParticipanteDetail of(Participante p) {
        return new GetParticipanteDetail(p.getId(), p.getNombreCompleto());
    }
}
