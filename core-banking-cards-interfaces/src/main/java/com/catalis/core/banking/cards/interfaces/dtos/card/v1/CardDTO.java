package com.catalis.core.banking.cards.interfaces.dtos.card.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.banking.cards.interfaces.enums.card.v1.CardStatusEnum;
import com.catalis.core.banking.cards.interfaces.enums.card.v1.CardTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardId;

    @FilterableId
    private Long contractId;

    @FilterableId
    private Long accountId;

    private CardTypeEnum cardType;
    private CardStatusEnum cardStatus;
    private String issuingBank;
    private LocalDateTime issuanceDate;
    private LocalDateTime expirationDate;
    private boolean physicalFlag;
}
