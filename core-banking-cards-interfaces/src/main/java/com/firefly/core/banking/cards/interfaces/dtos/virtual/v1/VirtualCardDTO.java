package com.firefly.core.banking.cards.interfaces.dtos.virtual.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.banking.cards.interfaces.enums.virtual.v1.VirtualCardStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
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