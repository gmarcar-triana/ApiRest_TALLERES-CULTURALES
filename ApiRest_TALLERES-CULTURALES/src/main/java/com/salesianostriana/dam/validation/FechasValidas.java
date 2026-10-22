package com.salesianostriana.dam.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FechasValidasValidator.class)
@Documented
public @interface FechasValidas {
    String message() default "La fecha de fin debe ser posterior a la de inicio";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
