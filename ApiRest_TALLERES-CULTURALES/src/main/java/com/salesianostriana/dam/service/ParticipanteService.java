package com.salesianostriana.dam.service;

import com.salesianostriana.dam.error.notfound.ParticipanteNotFoundException;
import com.salesianostriana.dam.model.Participante;
import com.salesianostriana.dam.repo.ParticipanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipanteService {

    private ParticipanteRepository participanteRepository;

    public List<Participante> getAll(){
        List<Participante> participantes = participanteRepository.findAll();
        if (participantes.isEmpty()) {
            throw new ParticipanteNotFoundException();
        }
        return participantes;
    }

    public Participante getById(Long id){
        return participanteRepository.findById(id)
                .orElseThrow(ParticipanteNotFoundException::new);
    }

    public Participante addParticipante(Participante participante){
        return participanteRepository.save(participante);
    }

    public Participante updateParticipante(Long id, Participante participante){
        if (!participanteRepository.existsById(id)) {
            throw new ParticipanteNotFoundException();
        }
        participante.setNombreCompleto(participante.getNombreCompleto());
        return participanteRepository.save(participante);
    }

    public void deleteParticipante(Long id){
        participanteRepository.deleteById(id);
    }



}
