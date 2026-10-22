package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Taller;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditTallerDto(
        @NotNull(message = "{editTallerDto.organiadorId.notNull}")
        Long organizadorId,


        @NotBlank(message = "{editTallerDto.titulo.notBlank}")
        String titulo,

        @NotNull(message = "{editTallerDto.fechaInicio.notNull}")
        LocalDateTime fechaHoraInicio,

        @NotNull(message = "{editTallerDto.fechaFin.notNull}")
        LocalDateTime fechaHoraFin

) {
    public Taller to() {
        return Taller.builder()
                .titulo(titulo)
                .fechaHoraInicio(fechaHoraInicio)
                .fechaHoraFin(fechaHoraFin)
                .build();
    }

}
