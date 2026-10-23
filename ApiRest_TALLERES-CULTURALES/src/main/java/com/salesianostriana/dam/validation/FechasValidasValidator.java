package com.salesianostriana.dam.validation;

import com.salesianostriana.dam.dto.EditTallerDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FechasValidasValidator implements ConstraintValidator<FechasValidas, EditTallerDto> {
    @Override
    public boolean isValid(EditTallerDto dto, ConstraintValidatorContext context) {
        if (dto.fechaHoraInicio() == null || dto.fechaHoraFin() == null) {
            return true;
        }
        return dto.fechaHoraFin().isAfter(dto.fechaHoraInicio());
    }


}
