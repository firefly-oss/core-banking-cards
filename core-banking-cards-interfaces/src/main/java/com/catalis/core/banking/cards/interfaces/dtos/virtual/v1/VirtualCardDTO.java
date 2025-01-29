package com.catalis.core.banking.cards.interfaces.dtos.virtual.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.banking.cards.interfaces.enums.virtual.v1.VirtualCardStatusEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class VirtualCardDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long virtualCardId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private String deviceId;

    private String virtualCardNumber;
    private VirtualCardStatusEnum virtualCardStatus;
    private LocalDateTime creationTimestamp;
    private LocalDateTime updateTimestamp;
}