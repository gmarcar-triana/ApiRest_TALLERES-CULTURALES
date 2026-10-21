package com.salesianostriana.dam.dto;

import java.time.LocalDateTime;
import java.util.List;

public record GetTallerDetail(
        Long id,
        String titulo,
        LocalDateTime fechaHoraInicio,
        LocalDateTime fechaHoraFin,
        GetOrganizadorList organizador,
        List<GetParticipante>
) {
}
