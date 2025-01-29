package com.catalis.core.banking.cards.interfaces.dtos.security.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.banking.cards.interfaces.enums.security.v1.SecurityFeatureEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CardSecurityDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardSecurityId;

    @FilterableId
    private Long cardId;

    private SecurityFeatureEnum securityFeature;
    private boolean securityStatus;
}