package com.firefly.core.banking.cards.interfaces.dtos.bin.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.*;
import com.firefly.core.banking.cards.interfaces.validation.ValidBIN;
import java.util.UUID;

/**
 * Data Transfer Object for Bank Identification Number (BIN).
 * Contains the essential information about a BIN that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BINDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID binId;

    @NotBlank(message = "BIN number is required")
    @ValidBIN
    private String binNumber;

    @NotNull(message = "BIN length is required")
    @Min(value = 6, message = "BIN length must be at least 6")
    @Max(value = 8, message = "BIN length cannot exceed 8")
    private Integer binLength;

    @FilterableId
    @NotNull(message = "Issuer ID is required")
    private UUID issuerId;

    @FilterableId
    @NotNull(message = "Card network ID is required")
    private UUID cardNetworkId;

    @FilterableId
    @NotNull(message = "Card type ID is required")
    private UUID cardTypeId;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Z]{2}$", message = "Country code must be a 2-letter ISO code")
    private String countryCode;

    @NotBlank(message = "Currency code is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code must be a 3-letter ISO code")
    private String currencyCode;

    @NotNull(message = "Active flag is required")
    private Boolean isActive;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
}