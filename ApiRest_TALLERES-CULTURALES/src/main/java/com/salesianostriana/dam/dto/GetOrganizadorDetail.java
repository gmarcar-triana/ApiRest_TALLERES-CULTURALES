package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Organizador;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name = "GetOrganizadorDetail", description = "Detalle de un organizador")
public record GetOrganizadorDetail(
        @Schema(example = "1") Long id,
        @Schema(example = "Centro Cultural Triana") String nombre,
        @Schema(example = "info@centro.es") String email
) {
    public static GetOrganizadorDetail of(Organizador organizador) {
        return new GetOrganizadorDetail(
                organizador.getId(),
                organizador.getNombre(),
                organizador.getEmail()
        );
    }

}
