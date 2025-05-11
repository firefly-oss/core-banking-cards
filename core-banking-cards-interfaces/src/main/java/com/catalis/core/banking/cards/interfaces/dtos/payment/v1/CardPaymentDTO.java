package com.catalis.core.banking.cards.interfaces.dtos.payment.v1;

import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Card Payment.
 * Contains the essential information about a Card Payment that needs to be exposed through the API.
 * A Card Payment represents a payment made towards a card balance.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardPaymentDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long paymentId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long accountId;

    @FilterableId
    private Long statementId;

    private String paymentReference;

    private String externalReference;

    private BigDecimal paymentAmount;

    private String currencyCode;

    private String paymentMethod;

    private String paymentChannel;

    private String paymentStatus;

    private LocalDateTime paymentTimestamp;

    private LocalDateTime postingTimestamp;

    private LocalDateTime valueDate;

    private Boolean isAutoPayment;

    private Boolean isMinimumPayment;

    private Boolean isFullPayment;

    private Boolean isScheduledPayment;

    private LocalDateTime scheduledDate;

    private String recurrencePattern;

    private String sourceAccountId;

    private String sourceAccountType;

    private String sourceBankCode;

    private String sourceBankName;

    private String sourceAccountHolder;

    private String paymentProcessor;

    private BigDecimal processorFee;

    private String processorReference;

    private String confirmationCode;

    private String failureReason;

    private String failureCode;

    private Integer retryCount;

    private LocalDateTime lastRetryTimestamp;

    private LocalDateTime nextRetryTimestamp;

    private String receiptUrl;

    private String notes;
}