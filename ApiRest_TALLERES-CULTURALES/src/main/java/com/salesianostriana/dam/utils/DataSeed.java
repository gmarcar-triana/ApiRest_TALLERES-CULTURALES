package com.salesianostriana.dam.utils;

import com.salesianostriana.dam.model.Organizador;
import com.salesianostriana.dam.model.Participante;
import com.salesianostriana.dam.model.Taller;
import com.salesianostriana.dam.repo.OrganizadorRepository;
import com.salesianostriana.dam.repo.ParticipanteRepository;
import com.salesianostriana.dam.repo.TallerRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final OrganizadorRepository organizadorRepository;
    private final ParticipanteRepository participanteRepository;
    private final TallerRepository tallerRepository;

    @PostConstruct
    public void initData() {

        Organizador o1 = Organizador.builder()
                .nombre("Centro Cultural Triana")
                .email("info@centrotriana.es")
                .build();

        Organizador o2 = Organizador.builder()
                .nombre("Asociación Artes Vivas")
                .email("contacto@artesvivas.es")
                .build();

        organizadorRepository.saveAll(List.of(o1, o2));

        Participante p1 = Participante.builder()
                .nombreCompleto("Ana García López")
                .build();

        Participante p2 = Participante.builder()
                .nombreCompleto("Luis Pérez Martín")
                .build();

        Participante p3 = Participante.builder()
                .nombreCompleto("Marta Ruiz Soto")
                .build();

        participanteRepository.saveAll(List.of(p1, p2, p3));

        Taller t1 = Taller.builder()
                .titulo("Cerámica")
                .fechaHoraInicio(LocalDateTime.of(2026, 11, 10, 17, 0))
                .fechaHoraFin(LocalDateTime.of(2026, 11, 10, 19, 0))
                .organizador(o1)
                .participantes(new HashSet<>(Set.of(p1, p2)))
                .build();

        Taller t2 = Taller.builder()
                .titulo("Pintura")
                .fechaHoraInicio(LocalDateTime.of(2026, 11, 12, 18, 0))
                .fechaHoraFin(LocalDateTime.of(2026, 11, 12, 20, 30))
                .organizador(o2)
                .build();

        tallerRepository.saveAll(List.of(t1, t2));
    }
}