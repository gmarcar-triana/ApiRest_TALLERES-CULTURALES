package com.salesianostriana.dam.error.notfound;

public class ParticipanteNotFoundException extends EntityNotFoundException {
    public ParticipanteNotFoundException(String message){
      super(message);
    }

    public ParticipanteNotFoundException(){
      super("Taller not found");
    }

}
