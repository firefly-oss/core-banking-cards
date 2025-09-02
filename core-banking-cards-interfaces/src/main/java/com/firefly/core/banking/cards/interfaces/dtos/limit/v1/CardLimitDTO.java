package com.firefly.core.banking.cards.interfaces.dtos.limit.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.banking.cards.interfaces.enums.limit.v1.LimitTypeEnum;
import com.firefly.core.banking.cards.interfaces.enums.limit.v1.ResetPeriodEnum;
import com.firefly.core.utils.annotations.FilterableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardLimitDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID cardLimitId;

    @FilterableId
    @NotNull(message = "Card ID is required")
    private UUID cardId;

    @NotNull(message = "Limit type is required")
    private LimitTypeEnum limitType;

    @NotNull(message = "Limit amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Limit amount must be greater than 0")
    @DecimalMax(value = "99999999.99", message = "Limit amount cannot exceed 99,999,999.99")
    private BigDecimal limitAmount;

    @NotNull(message = "Current usage is required")
    @DecimalMin(value = "0.0", message = "Current usage cannot be negative")
    private BigDecimal currentUsage;

    @NotNull(message = "Reset period is required")
    private ResetPeriodEnum resetPeriod;
}