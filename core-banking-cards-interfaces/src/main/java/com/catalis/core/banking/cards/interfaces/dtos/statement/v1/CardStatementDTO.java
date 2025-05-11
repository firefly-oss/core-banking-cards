package com.catalis.core.banking.cards.interfaces.dtos.statement.v1;

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
 * Data Transfer Object for Card Statement.
 * Contains the essential information about a Card Statement that needs to be exposed through the API.
 * A Card Statement represents a periodic statement of card activity, including transactions, fees, and balances.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardStatementDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long statementId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long customerId;

    @FilterableId
    private Long accountId;

    private String statementReference;

    private LocalDateTime statementDate;

    private LocalDateTime statementPeriodStart;

    private LocalDateTime statementPeriodEnd;

    private LocalDateTime dueDate;

    private BigDecimal closingBalance;

    private BigDecimal openingBalance;

    private BigDecimal minimumPaymentDue;

    private BigDecimal totalPaymentDue;

    private String currencyCode;

    private BigDecimal totalPurchases;

    private BigDecimal totalCashAdvances;

    private BigDecimal totalFees;

    private BigDecimal totalInterest;

    private BigDecimal totalCredits;

    private BigDecimal totalPayments;

    private BigDecimal totalAdjustments;

    private BigDecimal totalRewardsEarned;

    private BigDecimal totalRewardsRedeemed;

    private BigDecimal availableCredit;

    private BigDecimal creditLimit;

    private BigDecimal cashAdvanceLimit;

    private BigDecimal interestRate;

    private BigDecimal annualPercentageRate;

    private String paymentStatus;

    private Boolean isGenerated;

    private LocalDateTime generationTimestamp;

    private Boolean isDelivered;

    private String deliveryMethod;

    private LocalDateTime deliveryTimestamp;

    private String deliveryAddress;

    private Boolean isViewed;

    private LocalDateTime viewTimestamp;

    private String documentUrl;

    private String notes;
}