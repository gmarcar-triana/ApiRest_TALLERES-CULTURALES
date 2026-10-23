package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Organizador;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EditOrganizadorDto(
        @NotBlank(message = "{editOrganizadorDto.nombre.notBlank}")
        String nombre,

        @NotBlank(message = "{editOrganizadorDto.email.notBlank}")
        @Email(message = "{editOrganizadorDto.email.email}")
        String email
) {
    public Organizador to() {
        return Organizador.builder()
                .nombre(nombre.trim())
                .email(email.trim())
                .build();
    }
}
