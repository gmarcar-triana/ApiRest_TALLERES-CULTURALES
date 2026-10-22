package com.salesianostriana.dam.error.notfound;

public class OrganizadorNotFoundException extends EntityNotFoundException {
    public OrganizadorNotFoundException(String message) {
        super(message);
    }

    public OrganizadorNotFoundException() {
        super("Organizadores no encontrados");
    }

    public OrganizadorNotFoundException(Long id) {
        super("Organizador no encontrado con id " + id);
    }

}
