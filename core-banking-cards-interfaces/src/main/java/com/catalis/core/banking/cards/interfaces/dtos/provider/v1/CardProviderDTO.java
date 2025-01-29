package com.catalis.core.banking.cards.interfaces.dtos.provider.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.banking.cards.interfaces.enums.provider.v1.ProviderStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CardProviderDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardProviderId;

    @FilterableId
    private Long cardId;
    
    private String providerName;
    private String externalReference;
    private ProviderStatusEnum status;
}