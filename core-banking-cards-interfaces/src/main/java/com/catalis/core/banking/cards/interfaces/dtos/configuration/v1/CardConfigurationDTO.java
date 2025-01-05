package com.catalis.core.banking.cards.interfaces.dtos.configuration.v1;

import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.banking.cards.interfaces.enums.configuration.v1.ConfigTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CardConfigurationDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardConfigurationId;

    private Long cardId;
    private ConfigTypeEnum configType;
    private boolean configValue;
}

