package com.catalis.core.banking.cards.interfaces.dtos.physical.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PhysicalCardDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long physicalCardId;

    @FilterableId
    private Long cardId;

    private String cardNumber;
    private String cardCvv;
    private String cardHolderName;
    private String cardNetwork;
    private String cardDesign;
    private LocalDateTime shipmentDate;
    private LocalDateTime deliveryDate;
}
