package com.salesianostriana.dam.service;

import com.salesianostriana.dam.dto.EditTallerDto;
import com.salesianostriana.dam.error.TallerYaExisteException;
import com.salesianostriana.dam.error.notfound.OrganizadorNotFoundException;
import com.salesianostriana.dam.error.notfound.ParticipanteNotFoundException;
import com.salesianostriana.dam.error.ParticipanteYaInscritoException;
import com.salesianostriana.dam.error.notfound.TallerNotFoundException;
import com.salesianostriana.dam.model.Organizador;
import com.salesianostriana.dam.model.Participante;
import com.salesianostriana.dam.model.Taller;
import com.salesianostriana.dam.repo.OrganizadorRepository;
import com.salesianostriana.dam.repo.ParticipanteRepository;
import com.salesianostriana.dam.repo.TallerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TallerService {

    private final TallerRepository tallerRepository;
    private final OrganizadorRepository organizadorRepository;
    private final ParticipanteRepository participanteRepository;

    public List<Taller> getAll() {
        return tallerRepository.findAll();
    }

    public Taller getById(Long id) {
        return tallerRepository.findById(id)
                .orElseThrow(() -> new TallerNotFoundException(id));
    }

    public Taller addTaller(EditTallerDto dto) {
        comprobarTituloUnico(dto.titulo(), null);

        Taller taller = dto.to();
        taller.setOrganizador(getOrganizador(dto.organizadorId()));
        return tallerRepository.save(taller);
    }

    public Taller updateTaller(Long id, EditTallerDto dto) {
        Taller taller = getById(id);
        comprobarTituloUnico(dto.titulo(), id);

        taller.setTitulo(dto.titulo().trim());
        taller.setFechaHoraInicio(dto.fechaHoraInicio());
        taller.setFechaHoraFin(dto.fechaHoraFin());
        taller.setOrganizador(getOrganizador(dto.organizadorId()));
        return tallerRepository.save(taller);
    }

    public void deleteTaller(Long id) {
        tallerRepository.delete(getById(id));
    }


    @Transactional
    public Taller inscribir(Long tallerId, Long participanteId) {
        Taller taller = getById(tallerId);
        Participante participante = getParticipante(participanteId);

        boolean yaInscrito = taller.getParticipantes().stream()
                .anyMatch(p -> p.getId().equals(participanteId));
        if (yaInscrito) {
            throw new ParticipanteYaInscritoException(tallerId, participanteId);
        }

        taller.getParticipantes().add(participante);
        return tallerRepository.save(taller);
    }

    @Transactional(readOnly = true)
    public List<Participante> getParticipantes(Long tallerId) {

        return List.copyOf(getById(tallerId).getParticipantes());
    }

    @Transactional
    public void desinscribir(Long tallerId, Long participanteId) {
        Taller taller = getById(tallerId);
        getParticipante(participanteId);

        taller.getParticipantes().removeIf(p -> p.getId().equals(participanteId));
        tallerRepository.save(taller);
    }


    private void comprobarTituloUnico(String titulo, Long idActual) {
        String limpio = titulo.trim();
        boolean existe = (idActual == null)
                ? tallerRepository.existsByTituloIgnoreCase(limpio)
                : tallerRepository.existsByTituloIgnoreCaseAndIdNot(limpio, idActual);
        if (existe) {
            throw new TallerYaExisteException(limpio);
        }
    }

    private Organizador getOrganizador(Long id) {
        return organizadorRepository.findById(id)
                .orElseThrow(() -> new OrganizadorNotFoundException(id));
    }

    private Participante getParticipante(Long id) {
        return participanteRepository.findById(id)
                .orElseThrow(() -> new ParticipanteNotFoundException(id));
    }
}