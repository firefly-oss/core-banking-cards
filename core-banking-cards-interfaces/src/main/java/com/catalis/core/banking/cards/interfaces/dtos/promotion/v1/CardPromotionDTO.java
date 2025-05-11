package com.catalis.core.banking.cards.interfaces.dtos.promotion.v1;

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
 * Data Transfer Object for Card Promotion.
 * Contains the essential information about a Card Promotion that needs to be exposed through the API.
 * A Card Promotion represents special offers and promotional programs for cardholders, including
 * cashback offers, bonus points, and introductory rates.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardPromotionDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long promotionId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long programId;

    @FilterableId
    private Long cardTypeId;

    @FilterableId
    private Long issuerId;

    private String promotionCode;

    private String promotionName;

    private String promotionDescription;

    private String promotionType;

    private String promotionCategory;

    private String promotionStatus;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isActive;

    private Boolean isLimitedTime;

    private Boolean isTargeted;

    private String targetCriteria;

    private Boolean isOptInRequired;

    private String optInChannel;

    private String optInUrl;

    private Boolean isAutoEnrolled;

    private Integer maxEnrollments;

    private Integer currentEnrollments;

    private String benefitType;

    private BigDecimal benefitValue;

    private BigDecimal benefitPercentage;

    private String benefitDescription;

    private BigDecimal minimumSpend;

    private BigDecimal maximumBenefit;

    private String currencyCode;

    private String merchantCategoryCodes;

    private String merchantIds;

    private String countryCodes;

    private String transactionTypes;

    private String channelTypes;

    private Boolean isFirstTransactionOnly;

    private Boolean isNewCardsOnly;

    private Integer cardAgeMinDays;

    private Integer cardAgeMaxDays;

    private Boolean isBalanceTransfer;

    private BigDecimal balanceTransferFee;

    private BigDecimal balanceTransferFeePercentage;

    private BigDecimal balanceTransferMinAmount;

    private BigDecimal balanceTransferMaxAmount;

    private Boolean isIntroductoryRate;

    private BigDecimal introductoryRate;

    private BigDecimal postIntroductoryRate;

    private Integer introductoryPeriodMonths;

    private String termsAndConditions;

    private String termsUrl;

    private String marketingImageUrl;

    private String marketingDescription;

    private Boolean isCombinable;

    private String combinablePromotions;

    private String exclusionCriteria;
}