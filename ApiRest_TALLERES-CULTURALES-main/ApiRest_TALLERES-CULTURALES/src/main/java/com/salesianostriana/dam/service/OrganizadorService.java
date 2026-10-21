package com.salesianostriana.dam.service;

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
        List<Organizador> organizadores = organizadorRepository.findAll();
        if (organizadores.isEmpty()) {
            throw new OrganizadorNotFoundException();
        }
        return organizadores;
    }

    public Organizador getById(Long id){
        return organizadorRepository.findById(id)
                .orElseThrow(OrganizadorNotFoundException::new);
    }

    public Organizador addOrganizador(Organizador organizador){
        return organizadorRepository.save(organizador);
    }

    public Organizador updateOrganizador(Long id, Organizador organizador){
        if (!organizadorRepository.existsById(id)) {
            throw new OrganizadorNotFoundException();
        }
        organizador.setNombre(organizador.getNombre());
        return organizadorRepository.save(organizador);
    }

    public void deleteOrganizador(Long id){
        organizadorRepository.deleteById(id);
    }


}
