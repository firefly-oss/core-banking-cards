package com.catalis.core.banking.cards.interfaces.dtos.reward.v1;

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
 * Data Transfer Object for Card Reward.
 * Contains the essential information about a Card Reward that needs to be exposed through the API.
 * A Card Reward represents rewards earned and redeemed through card usage.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardRewardDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long rewardId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long transactionId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long accountId;

    @FilterableId
    private Long programId;

    private String rewardReference;

    private String rewardType;

    private String rewardCategory;

    private String rewardDescription;

    private String rewardStatus;

    private Boolean isEarning;

    private Boolean isRedemption;

    private Boolean isAdjustment;

    private Boolean isExpiration;

    private BigDecimal pointsEarned;

    private BigDecimal pointsRedeemed;

    private BigDecimal pointsAdjusted;

    private BigDecimal pointsExpired;

    private BigDecimal pointsBalance;

    private BigDecimal cashValue;

    private String currencyCode;

    private BigDecimal earningRate;

    private BigDecimal earningMultiplier;

    private String earningReason;

    private String redemptionType;

    private String redemptionDescription;

    private BigDecimal redemptionValue;

    private String adjustmentReason;

    private String adjustmentDescription;

    private String merchantId;

    private String merchantName;

    private String merchantCategory;

    private BigDecimal transactionAmount;

    private String transactionCurrency;

    private LocalDateTime transactionDate;

    private LocalDateTime postingDate;

    private LocalDateTime expirationDate;

    private Boolean isPromotional;

    @FilterableId
    private Long promotionId;

    private String promotionName;

    private Boolean isTransferable;

    private Long transferToPartyId;

    private Long transferFromPartyId;

    private LocalDateTime transferDate;
}