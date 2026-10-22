package com.salesianostriana.dam.service;

import com.salesianostriana.dam.dto.EditParticipanteDto;
import com.salesianostriana.dam.error.notfound.ParticipanteNotFoundException;
import com.salesianostriana.dam.model.Participante;
import com.salesianostriana.dam.repo.ParticipanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    public List<Participante> getAll(){
        return participanteRepository.findAll();
    }

    public Participante getById(Long id){
        return participanteRepository.findById(id)
                .orElseThrow(() -> new ParticipanteNotFoundException(id));
    }

    public Participante addParticipante(EditParticipanteDto dto){
        return participanteRepository.save(dto.to());
    }

}
