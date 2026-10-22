package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Participante;
import jakarta.validation.constraints.NotBlank;

public record EditParticipanteDto(
        @NotBlank(message = "{editParticipanteDto.nombre.notBlank}")
        String nombreCompleto

) {
    public Participante to() {
        return Participante.builder()
                .nombreCompleto(nombreCompleto)
                .build();
    }
}
