package com.salesianostriana.dam.error.notfound;

public class OrganizadorNotFoundException extends EntityNotFoundException {
    public OrganizadorNotFoundException(String message) {
        super(message);
    }

    public OrganizadorNotFoundException() {
        super("Organizador not found");
    }

}
