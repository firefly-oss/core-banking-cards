package com.catalis.core.banking.cards.interfaces.dtos.fee.v1;

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
 * Data Transfer Object for Card Fee.
 * Contains the essential information about a Card Fee that needs to be exposed through the API.
 * A Card Fee represents fees associated with a card, such as annual fees, late payment fees, and transaction fees.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardFeeDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long feeId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long accountId;

    @FilterableId
    private Long transactionId;

    @FilterableId
    private Long statementId;

    @FilterableId
    private Long programId;

    private String feeReference;

    private String feeType;

    private String feeName;

    private String feeDescription;

    private BigDecimal feeAmount;

    private BigDecimal feePercentage;

    private String currencyCode;

    private String feeStatus;

    private Boolean isRecurring;

    private String recurrenceFrequency;

    private LocalDateTime nextRecurrenceDate;

    private Boolean isProrated;

    private String prorationRule;

    private Boolean isWaived;

    private String waiverReason;

    private String waiverReference;

    private LocalDateTime waiverTimestamp;

    private LocalDateTime waiverExpiry;

    private String waiverAuthorizedBy;

    private Boolean isRefunded;

    private String refundReason;

    private String refundReference;

    private LocalDateTime refundTimestamp;

    private BigDecimal refundAmount;

    private String refundAuthorizedBy;

    private LocalDateTime chargeTimestamp;

    private LocalDateTime postingTimestamp;

    private LocalDateTime valueDate;

    private BigDecimal taxAmount;

    private BigDecimal taxRate;

    private String taxType;

    private BigDecimal totalAmount;

    private Boolean isBilled;

    private LocalDateTime billingTimestamp;

    private Boolean isPaid;

    private LocalDateTime paymentTimestamp;

    private String paymentReference;
}