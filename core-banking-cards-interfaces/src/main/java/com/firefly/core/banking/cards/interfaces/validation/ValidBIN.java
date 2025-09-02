package com.firefly.core.banking.cards.interfaces.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom validation annotation for Bank Identification Numbers (BIN).
 * Validates that the BIN follows the correct format and length requirements.
 */
@Documented
@Constraint(validatedBy = BINValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBIN {
    String message() default "Invalid BIN format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
