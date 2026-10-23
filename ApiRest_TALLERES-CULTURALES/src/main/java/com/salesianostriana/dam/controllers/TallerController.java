package com.salesianostriana.dam.controllers;

import com.salesianostriana.dam.dto.EditTallerDto;
import com.salesianostriana.dam.dto.GetParticipanteList;
import com.salesianostriana.dam.dto.GetTallerDetail;
import com.salesianostriana.dam.dto.GetTallerList;
import com.salesianostriana.dam.service.TallerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/talleres")
@Validated
@Tag(name = "Taller Controller", description = "Gestión de talleres y de sus inscripciones")
public class TallerController {

    private final TallerService tallerService;

    @Operation(summary = "Listar talleres", description = "Devuelve todos los talleres (lista vacía si no hay)")
    @ApiResponse(responseCode = "200", description = "Listado de talleres",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = GetTallerList.class))))
    @GetMapping
    public ResponseEntity<List<GetTallerList>> getAll() {
        return ResponseEntity.ok(
                tallerService.getAll().stream().map(GetTallerList::of).toList());
    }

    @Operation(summary = "Obtener un taller", description = "Devuelve el detalle de un taller con su organizador y participantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Taller encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTallerDetail.class))),
            @ApiResponse(responseCode = "404", description = "Taller no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetTallerDetail> getById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(GetTallerDetail.of(tallerService.getById(id)));
    }

    @Operation(summary = "Crear un taller")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Taller creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTallerDetail.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Organizador no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "Ya existe un taller con ese título",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<GetTallerDetail> add(@Valid @RequestBody EditTallerDto dto) {
        return ResponseEntity.status(201)
                .body(GetTallerDetail.of(tallerService.addTaller(dto)));
    }

    @Operation(summary = "Modificar un taller")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Taller modificado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTallerDetail.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Taller u organizador no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "El título pertenece a otro taller",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetTallerDetail> update(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody EditTallerDto dto) {
        return ResponseEntity.ok(GetTallerDetail.of(tallerService.updateTaller(id, dto)));
    }

    @Operation(summary = "Borrar un taller")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Taller borrado"),
            @ApiResponse(responseCode = "404", description = "Taller no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        tallerService.deleteTaller(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Inscribir un participante en un taller")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Inscripción realizada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTallerDetail.class))),
            @ApiResponse(responseCode = "404", description = "Taller o participante no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "El participante ya está inscrito",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping("/{tallerId}/participantes/{participanteId}")
    public ResponseEntity<GetTallerDetail> inscribir(
            @PathVariable @Min(1) Long tallerId,
            @PathVariable @Min(1) Long participanteId) {
        return ResponseEntity.status(201)
                .body(GetTallerDetail.of(tallerService.inscribir(tallerId, participanteId)));
    }

    @Operation(summary = "Listar los participantes de un taller",
            description = "Lista vacía si el taller existe pero no tiene inscritos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Participantes inscritos",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GetParticipanteList.class)))),
            @ApiResponse(responseCode = "404", description = "Taller no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/{tallerId}/participantes")
    public ResponseEntity<List<GetParticipanteList>> getParticipantes(
            @PathVariable @Min(1) Long tallerId) {
        return ResponseEntity.ok(
                tallerService.getParticipantes(tallerId).stream()
                        .map(GetParticipanteList::of).toList());
    }

    @Operation(summary = "Eliminar una inscripción",
            description = "Conserva taller y participante. Si no estaban vinculados, no cambia nada.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Inscripción eliminada (o inexistente)"),
            @ApiResponse(responseCode = "404", description = "Taller o participante no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping("/{tallerId}/participantes/{participanteId}")
    public ResponseEntity<Void> desinscribir(
            @PathVariable @Min(1) Long tallerId,
            @PathVariable @Min(1) Long participanteId) {
        tallerService.desinscribir(tallerId, participanteId);
        return ResponseEntity.noContent().build();
    }
}