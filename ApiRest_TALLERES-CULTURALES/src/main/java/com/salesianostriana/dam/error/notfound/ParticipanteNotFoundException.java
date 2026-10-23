package com.salesianostriana.dam.error.notfound;

public class ParticipanteNotFoundException extends EntityNotFoundException {
    public ParticipanteNotFoundException(String message){
      super(message);
    }

    public ParticipanteNotFoundException(){
      super("Participantes no encontrados");
    }

    public ParticipanteNotFoundException(Long id) {
        super("Participante no encontrado con id " + id);
    }

}
