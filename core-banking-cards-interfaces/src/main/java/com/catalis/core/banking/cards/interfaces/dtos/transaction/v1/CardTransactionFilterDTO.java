package com.catalis.core.banking.cards.interfaces.dtos.transaction.v1;

import com.catalis.core.banking.cards.interfaces.enums.transaction.v1.TransactionStatusEnum;
import com.catalis.core.banking.cards.interfaces.enums.transaction.v1.TransactionTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CardTransactionFilterDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardId;

    private TransactionTypeEnum transactionType;
    private TransactionStatusEnum transactionStatus;
    private String cardMerchantName;
    private LocalDateTime fromTimestamp;
    private LocalDateTime toTimestamp;
    private Boolean cardFraudFlag;
    private String cardHolderCountry;

    private BigDecimal minAmount;
    private BigDecimal maxAmount;
}