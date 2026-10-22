package com.salesianostriana.dam.repo;

import com.salesianostriana.dam.model.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

import java.util.List;

public interface TallerRepository extends JpaRepository<Taller, Long> {
    boolean existsByTituloIgnoreCase(String titulo);
    boolean existsByTituloIgnoreCaseAndIdNot(String titulo, Long id);
}
