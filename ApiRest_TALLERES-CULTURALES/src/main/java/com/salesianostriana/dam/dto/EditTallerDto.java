package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Taller;
import com.salesianostriana.dam.validation.FechasValidas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

@FechasValidas(message = "{editTallerDto.fechas.FechasValidas}")
public record EditTallerDto(
        @NotBlank(message = "{editTallerDto.titulo.notBlank}")
        String titulo,

        @NotNull(message = "{editTallerDto.fechaHoraInicio.notNull}")
        LocalDateTime fechaHoraInicio,

        @NotNull(message = "{editTallerDto.fechaHoraFin.notNull}")
        LocalDateTime fechaHoraFin,

        @NotNull(message = "{editTallerDto.organizadorId.notNull}")
        @Positive
        Long organizadorId
) {

    public Taller to() {
        return Taller.builder()
                .titulo(titulo.trim())
                .fechaHoraInicio(fechaHoraInicio)
                .fechaHoraFin(fechaHoraFin)
                .build();
    }
}