package com.catalis.core.banking.cards.interfaces.dtos.transaction.v1;

import com.catalis.common.core.filters.FilterableId;
import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.banking.cards.interfaces.enums.transaction.v1.TransactionStatusEnum;
import com.catalis.core.banking.cards.interfaces.enums.transaction.v1.TransactionTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CardTransactionDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardTransactionId;

    @FilterableId
    private Long cardId;

    private TransactionTypeEnum transactionType;
    private String merchantInfo;
    private TransactionStatusEnum transactionStatus;
    private String cardAuthCode;
    private String cardMerchantCategoryCode;
    private String cardMerchantName;
    private String cardPosEntryMode;
    private String cardTransactionReference;

    @FilterableId
    private String cardTerminalId;

    private String cardHolderCountry;
    private boolean cardPresentFlag;
    private LocalDateTime cardTransactionTimestamp;
    private boolean cardFraudFlag;
    private BigDecimal cardCurrencyConversionRate;
    private BigDecimal cardFeeAmount;
    private String cardFeeCurrency;
    private String cardInstallmentPlan;
}
