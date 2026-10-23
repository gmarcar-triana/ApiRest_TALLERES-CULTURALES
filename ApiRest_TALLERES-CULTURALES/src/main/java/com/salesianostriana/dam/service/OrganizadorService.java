package com.salesianostriana.dam.service;

import com.salesianostriana.dam.dto.EditOrganizadorDto;
import com.salesianostriana.dam.error.notfound.OrganizadorNotFoundException;
import com.salesianostriana.dam.model.Organizador;
import com.salesianostriana.dam.repo.OrganizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizadorService {

    private final OrganizadorRepository organizadorRepository;

    public List<Organizador> getAll(){
        return organizadorRepository.findAll();
    }

    public Organizador getById(Long id){
        return organizadorRepository.findById(id)
                .orElseThrow(() -> new OrganizadorNotFoundException(id));
    }

    public Organizador addOrganizador(EditOrganizadorDto dto) {
        return organizadorRepository.save(dto.to());
    }

    public Organizador updateOrganizador(Long id, EditOrganizadorDto dto) {
        Organizador organizador = getById(id);
        organizador.setNombre(dto.nombre().trim());
        organizador.setEmail(dto.email().trim());
        return organizadorRepository.save(organizador);
    }

    public void deleteOrganizador(Long id){
        organizadorRepository.delete(getById(id));
    }

}
