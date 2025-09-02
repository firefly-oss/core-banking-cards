package com.firefly.core.banking.cards.interfaces.dtos.cardtype.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.*;
import java.util.UUID;

/**
 * Data Transfer Object for Card Type.
 * Contains the essential information about a Card Type that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardTypeDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID cardTypeId;

    @NotBlank(message = "Type name is required")
    @Size(min = 2, max = 100, message = "Type name must be between 2 and 100 characters")
    private String typeName;

    @NotBlank(message = "Type code is required")
    @Pattern(regexp = "^[A-Z0-9_]{2,20}$", message = "Type code must be 2-20 characters, uppercase letters, numbers, and underscores only")
    private String typeCode;

    @NotNull(message = "Credit card flag is required")
    private Boolean isCredit;

    @NotNull(message = "Debit card flag is required")
    private Boolean isDebit;

    @NotNull(message = "Prepaid card flag is required")
    private Boolean isPrepaid;

    @NotNull(message = "Virtual card flag is required")
    private Boolean isVirtual;

    @NotNull(message = "Commercial card flag is required")
    private Boolean isCommercial;

    @NotNull(message = "Gift card flag is required")
    private Boolean isGift;

    @DecimalMin(value = "0.0", inclusive = false, message = "Default credit limit must be greater than 0")
    @DecimalMax(value = "99999999.99", message = "Default credit limit cannot exceed 99,999,999.99")
    private Double defaultCreditLimit;

    @DecimalMin(value = "0.0", inclusive = false, message = "Default daily limit must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Default daily limit cannot exceed 999,999.99")
    private Double defaultDailyLimit;

    @DecimalMin(value = "0.0", inclusive = false, message = "Default monthly limit must be greater than 0")
    @DecimalMax(value = "9999999.99", message = "Default monthly limit cannot exceed 9,999,999.99")
    private Double defaultMonthlyLimit;

    @NotNull(message = "Active flag is required")
    private Boolean isActive;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
}