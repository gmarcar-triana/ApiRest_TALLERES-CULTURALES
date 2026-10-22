package com.salesianostriana.dam.dto;

import com.salesianostriana.dam.model.Organizador;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EditOrganizadorDto(
        @NotBlank(message = "{editOrganizadorDto.nombre.notBlank}")
        String name,

        @Email(message = "editOrganizadorDto.email.email")
        @NotBlank(message = "editOrganizadorDto.email.notBlank")
        String email
) {
    public Organizador to() {
        return Organizador.builder()
                .nombre(name)
                .email(email)
                .build();
    }
}
