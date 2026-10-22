package com.salesianostriana.dam.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTallerTittleValidator.class)
@Documented
public @interface UniqueTallerTittle {

    String message() default "El título del taller debe de ser único";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
