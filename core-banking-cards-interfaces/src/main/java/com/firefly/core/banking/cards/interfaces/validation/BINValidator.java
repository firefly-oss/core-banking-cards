package com.firefly.core.banking.cards.interfaces.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator implementation for ValidBIN annotation.
 * Validates Bank Identification Numbers (BIN).
 */
public class BINValidator implements ConstraintValidator<ValidBIN, String> {

    @Override
    public void initialize(ValidBIN constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String binNumber, ConstraintValidatorContext context) {
        if (binNumber == null || binNumber.trim().isEmpty()) {
            return false;
        }

        String cleanBIN = binNumber.trim();

        // Check if it contains only digits
        if (!cleanBIN.matches("^[0-9]+$")) {
            return false;
        }

        // Check length (6-8 digits for BIN)
        if (cleanBIN.length() < 6 || cleanBIN.length() > 8) {
            return false;
        }

        // Additional BIN-specific validations can be added here
        // For example, checking against known BIN ranges for specific card networks
        
        return true;
    }
}
