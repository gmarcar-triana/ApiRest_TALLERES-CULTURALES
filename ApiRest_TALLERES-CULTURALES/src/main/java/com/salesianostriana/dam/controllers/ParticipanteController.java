package com.salesianostriana.dam.controllers;

import com.salesianostriana.dam.dto.EditParticipanteDto;
import com.salesianostriana.dam.dto.GetParticipanteDetail;
import com.salesianostriana.dam.dto.GetParticipanteList;
import com.salesianostriana.dam.service.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/participantes")
@Tag(name = "Participante Controller", description = "Alta y consulta de participantes")
public class ParticipanteController {

    private final ParticipanteService participanteService;

    @Operation(summary = "Listar participantes", description = "Lista vacía si no hay ninguno")
    @ApiResponse(responseCode = "200", description = "Listado de participantes")
    @GetMapping
    public ResponseEntity<List<GetParticipanteList>> getAll() {
        return ResponseEntity.ok(
                participanteService.getAll().stream().map(GetParticipanteList::of).toList());
    }

    @Operation(summary = "Crear un participante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Participante creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<GetParticipanteDetail> add(@Valid @RequestBody EditParticipanteDto dto) {
        return ResponseEntity.status(201)
                .body(GetParticipanteDetail.of(participanteService.addParticipante(dto)));
    }
}