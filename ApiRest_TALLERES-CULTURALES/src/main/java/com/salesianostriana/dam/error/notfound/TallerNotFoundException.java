package com.salesianostriana.dam.error.notfound;

public class TallerNotFoundException extends EntityNotFoundException {
    public TallerNotFoundException(String message) {
        super (message);
    }
    public TallerNotFoundException(Long id) {
        super("Taller no encontrado con id " + id);
    }

}
