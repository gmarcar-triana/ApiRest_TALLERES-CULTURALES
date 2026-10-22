package com.salesianostriana.dam.error.notfound;

public class TallerNotFoundException extends EntityNotFoundException {
    public TallerNotFoundException(String message) {
        super (message);
    }

    public TallerNotFoundException() {
        super("Taller not found");
    }

}
