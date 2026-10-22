package com.salesianostriana.dam.service;

import com.salesianostriana.dam.error.notfound.TallerNotFoundException;
import com.salesianostriana.dam.model.Taller;
import com.salesianostriana.dam.repo.TallerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TallerService {

    private TallerRepository tallerRepository;

    public List<Taller> getAll(){
        List<Taller> talleres = tallerRepository.findAll();
        if (talleres.isEmpty()) {
            throw new TallerNotFoundException();
        }
        return talleres;
    }

    public Taller getById(Long id){
        return tallerRepository.findById(id)
                .orElseThrow(TallerNotFoundException::new);
    }

    public Taller addTaller(Taller taller){
        return tallerRepository.save(taller);
    }

    public Taller updateTaller(Long id, Taller taller){
        if (!tallerRepository.existsById(id)) {
            throw new TallerNotFoundException();
        }
        taller.setTitulo(taller.getTitulo());
        return tallerRepository.save(taller);
    }

    public void deleteTaller(Long id) {
        tallerRepository.deleteById(id);
    }

}
