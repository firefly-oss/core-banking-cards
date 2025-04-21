package com.catalis.core.banking.cards.interfaces.dtos.limit.v1;

import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.banking.cards.interfaces.enums.limit.v1.LimitTypeEnum;
import com.catalis.core.banking.cards.interfaces.enums.limit.v1.ResetPeriodEnum;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CardLimitDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardLimitId;

    @FilterableId
    private Long cardId;

    private LimitTypeEnum limitType;
    private BigDecimal limitAmount;
    private BigDecimal currentUsage;
    private ResetPeriodEnum resetPeriod;
}