package com.salesianostriana.dam.controllers;

import com.salesianostriana.dam.model.Organizador;
import com.salesianostriana.dam.repo.OrganizadorRepository;
import com.salesianostriana.dam.service.OrganizadorService;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.TableGenerator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organizador")
@Validated
@Tag(name="Organizador Controller", description = "Un controlador de Organizadores")
public class OrganizadorController {

    private final OrganizadorRepository organizadorRepository;
    private final OrganizadorService organizadorService;

    @PostMapping
    public ResponseEntity<Organizador> addOrganizador(
            @Valid @RequestBody Organizador organizador){
        if (StringUtils.isBlank(organizador.getNombre())){
            return ResponseEntity.status(201)
                    .body(pro)
        }
    }


}
