package com.firefly.core.banking.cards.interfaces.dtos.configuration.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for Card Configuration.
 * Contains the essential information about a Card Configuration that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardConfigurationDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID cardConfigurationId;

    @FilterableId
    private UUID cardId;

    @FilterableId
    private UUID programId;

    private String configKey;

    private String configValue;

    private String configType;

    private Boolean isActive;

    private LocalDateTime activationDate;

    private LocalDateTime expirationDate;

    private Boolean isSystemDefault;

    private Boolean isProgramDefault;

    private Boolean isPartyConfigurable;

    private String allowedValues;

    private String minValue;

    private String maxValue;

    private String description;
}