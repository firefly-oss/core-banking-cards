package com.firefly.core.banking.cards.interfaces.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Custom validation annotation for card numbers.
 * Validates that the card number follows the Luhn algorithm and has the correct format.
 */
@Documented
@Constraint(validatedBy = CardNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CardNumber {
    String message() default "Invalid card number format or checksum";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
