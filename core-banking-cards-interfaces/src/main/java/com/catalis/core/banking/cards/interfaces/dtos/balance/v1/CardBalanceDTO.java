package com.catalis.core.banking.cards.interfaces.dtos.balance.v1;

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
 * Data Transfer Object for Card Balance.
 * Contains the essential information about a Card Balance that needs to be exposed through the API.
 * A Card Balance represents the current balance of a card, including different types of balances
 * like purchase balance, cash advance balance, and balance transfers.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardBalanceDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long balanceId;
    
    @FilterableId
    private Long cardId;
    
    @FilterableId
    private Long customerId;
    
    @FilterableId
    private Long accountId;
    
    @FilterableId
    private Long statementId;
    
    private String balanceType;
    
    private String balanceCategory;
    
    private String balanceDescription;
    
    private BigDecimal balanceAmount;
    
    private BigDecimal availableAmount;
    
    private BigDecimal reservedAmount;
    
    private BigDecimal pendingAmount;
    
    private String currencyCode;
    
    private LocalDateTime asOfDate;
    
    @FilterableId
    private Long lastTransactionId;
    
    private LocalDateTime lastTransactionTimestamp;
    
    @FilterableId
    private Long lastPaymentId;
    
    private LocalDateTime lastPaymentTimestamp;
    
    @FilterableId
    private Long lastStatementId;
    
    private LocalDateTime lastStatementDate;
    
    private Boolean isPromotionalRate;
    
    @FilterableId
    private Long promotionId;
    
    private LocalDateTime promotionEndDate;
    
    private BigDecimal interestRate;
    
    private BigDecimal annualPercentageRate;
    
    private BigDecimal accruedInterest;
    
    private LocalDateTime lastInterestAccrualDate;
    
    private LocalDateTime nextInterestAccrualDate;
    
    private Boolean isInGracePeriod;
    
    private LocalDateTime gracePeriodEndDate;
    
    private BigDecimal minimumPaymentDue;
    
    private LocalDateTime minimumPaymentDueDate;
    
    private Integer daysPastDue;
    
    private Boolean isDelinquent;
    
    private LocalDateTime delinquencyStartDate;
    
    private Integer delinquencyDays;
    
    private String delinquencyStage;
    
    private Boolean isChargedOff;
    
    private LocalDateTime chargeOffDate;
    
    private BigDecimal chargeOffAmount;
    
    private Boolean isWrittenOff;
    
    private LocalDateTime writeOffDate;
    
    private BigDecimal writeOffAmount;
    
    private Boolean isInCollection;
    
    private LocalDateTime collectionStartDate;
    
    private String collectionAgencyId;
    
    private String collectionReference;
    
    private String collectionStatus;
}