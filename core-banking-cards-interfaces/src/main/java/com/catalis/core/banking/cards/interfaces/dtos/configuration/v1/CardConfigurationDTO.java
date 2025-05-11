package com.catalis.core.banking.cards.interfaces.dtos.configuration.v1;

import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

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
    private Long cardConfigurationId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long programId;

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