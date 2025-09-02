package com.firefly.core.banking.cards.interfaces.dtos.security.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.banking.cards.interfaces.enums.security.v1.SecurityFeatureEnum;
import com.firefly.core.utils.annotations.FilterableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardSecurityDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID cardSecurityId;

    @FilterableId
    private UUID cardId;

    private SecurityFeatureEnum securityFeature;
    private boolean securityStatus;
}