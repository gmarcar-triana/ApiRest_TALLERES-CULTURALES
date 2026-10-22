package com.salesianostriana.dam.validation;

import com.salesianostriana.dam.repo.TallerRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class UniqueTallerTittleValidator implements ConstraintValidator<UniqueTallerTittle, String> {

    @Autowired
    private TallerRepository tallerRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && !tallerRepository.existsByTittle(value);
    }

}
