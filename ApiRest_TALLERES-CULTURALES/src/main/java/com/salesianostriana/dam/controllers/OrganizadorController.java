package com.salesianostriana.dam.controllers;

import com.salesianostriana.dam.dto.EditOrganizadorDto;
import com.salesianostriana.dam.dto.GetOrganizadorDetail;
import com.salesianostriana.dam.dto.GetOrganizadorList;
import com.salesianostriana.dam.service.OrganizadorService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/organizadores")
@Validated
@Tag(name = "Organizador Controller", description = "Gestión de organizadores de talleres")
public class OrganizadorController {

    private final OrganizadorService organizadorService;

    @Operation(summary = "Listar organizadores", description = "Lista vacía si no hay ninguno")
    @ApiResponse(responseCode = "200", description = "Listado de organizadores")
    @GetMapping
    public ResponseEntity<List<GetOrganizadorList>> getAll() {
        return ResponseEntity.ok(
                organizadorService.getAll().stream().map(GetOrganizadorList::of).toList());
    }

    @Operation(summary = "Obtener un organizador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Organizador encontrado"),
            @ApiResponse(responseCode = "404", description = "Organizador no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<GetOrganizadorDetail> getById(@PathVariable @Min(1) Long id) {
        return ResponseEntity.ok(GetOrganizadorDetail.of(organizadorService.getById(id)));
    }

    @Operation(summary = "Crear un organizador")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Organizador creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<GetOrganizadorDetail> add(@Valid @RequestBody EditOrganizadorDto dto) {
        return ResponseEntity.status(201)
                .body(GetOrganizadorDetail.of(organizadorService.addOrganizador(dto)));
    }

    @Operation(summary = "Modificar un organizador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Organizador modificado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Organizador no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<GetOrganizadorDetail> update(
            @PathVariable @Min(1) Long id,
            @Valid @RequestBody EditOrganizadorDto dto) {
        return ResponseEntity.ok(
                GetOrganizadorDetail.of(organizadorService.updateOrganizador(id, dto)));
    }

    @Operation(summary = "Borrar un organizador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Organizador borrado"),
            @ApiResponse(responseCode = "404", description = "Organizador no encontrado",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "409", description = "El organizador tiene talleres asociados",
                    content = @Content(mediaType = "application/problem+json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        organizadorService.deleteOrganizador(id);
        return ResponseEntity.noContent().build();
    }
}