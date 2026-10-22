package com.salesianostriana.dam.error;

public class TallerYaExisteException extends RuntimeException {
    public TallerYaExisteException(String titulo) {
        super("Ya existe un taller con el título '" + titulo + "'");
    }
}
