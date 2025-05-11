package com.catalis.core.banking.cards.interfaces.dtos.interest.v1;

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
 * Data Transfer Object for Card Interest.
 * Contains the essential information about Card Interest that needs to be exposed through the API.
 * A Card Interest represents interest charged on card balances, including rates, calculation methods, and accrual information.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardInterestDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long interestId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long accountId;

    @FilterableId
    private Long statementId;

    @FilterableId
    private Long programId;

    private String interestReference;

    private String interestType;

    private String interestName;

    private String interestDescription;

    private BigDecimal interestRate;

    private BigDecimal annualPercentageRate;

    private Boolean isVariableRate;

    private BigDecimal baseRate;

    private BigDecimal rateMargin;

    private BigDecimal rateCap;

    private BigDecimal rateFloor;

    private String rateChangeFrequency;

    private LocalDateTime nextRateChangeDate;

    private BigDecimal previousRate;

    private LocalDateTime rateChangeTimestamp;

    private String calculationMethod;

    private String compoundingFrequency;

    private String accrualFrequency;

    private String balanceType;

    private BigDecimal balanceAmount;

    private BigDecimal interestAmount;

    private BigDecimal accruedInterest;

    private String currencyCode;

    private LocalDateTime accrualStartDate;

    private LocalDateTime accrualEndDate;

    private Integer daysInPeriod;

    private Integer daysInYear;

    private Boolean isPromotionalRate;

    @FilterableId
    private Long promotionId;

    private LocalDateTime promotionStartDate;

    private LocalDateTime promotionEndDate;

    private BigDecimal postPromotionRate;

    private Boolean isGracePeriod;

    private Integer gracePeriodDays;

    private LocalDateTime gracePeriodEndDate;

    private Boolean isCharged;

    private LocalDateTime chargeTimestamp;

    private LocalDateTime postingTimestamp;

    private LocalDateTime valueDate;

    private Boolean isWaived;

    private String waiverReason;

    private String waiverReference;

    private LocalDateTime waiverTimestamp;

    private String waiverAuthorizedBy;

    private Boolean isBilled;

    private LocalDateTime billingTimestamp;

    private Boolean isPaid;

    private LocalDateTime paymentTimestamp;

    private String paymentReference;
}