package com.firefly.core.banking.cards.interfaces.dtos.design.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.*;
import java.util.UUID;

/**
 * Data Transfer Object for Card Design.
 * Contains the essential information about a Card Design that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardDesignDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID designId;

    @NotBlank(message = "Design name is required")
    @Size(min = 2, max = 100, message = "Design name must be between 2 and 100 characters")
    private String designName;

    @NotBlank(message = "Design code is required")
    @Pattern(regexp = "^[A-Z0-9_]{2,20}$", message = "Design code must be 2-20 characters, uppercase letters, numbers, and underscores only")
    private String designCode;

    @Pattern(regexp = "^https?://.*\\.(jpg|jpeg|png|gif|svg)$", message = "Front image URL must be a valid HTTP/HTTPS URL pointing to an image file")
    private String frontImageUrl;

    @Pattern(regexp = "^https?://.*\\.(jpg|jpeg|png|gif|svg)$", message = "Back image URL must be a valid HTTP/HTTPS URL pointing to an image file")
    private String backImageUrl;

    @FilterableId
    @NotNull(message = "Card type ID is required")
    private UUID cardTypeId;

    @FilterableId
    @NotNull(message = "Issuer ID is required")
    private UUID issuerId;

    @FilterableId
    @NotNull(message = "Card network ID is required")
    private UUID cardNetworkId;

    @NotNull(message = "Customizable flag is required")
    private Boolean isCustomizable;

    @NotNull(message = "Default flag is required")
    private Boolean isDefault;

    @NotNull(message = "Active flag is required")
    private Boolean isActive;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
}