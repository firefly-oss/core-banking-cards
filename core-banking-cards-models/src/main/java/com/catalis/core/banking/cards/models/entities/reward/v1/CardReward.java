package com.catalis.core.banking.cards.models.entities.reward.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Card Reward entity.
 * Represents rewards earned and redeemed through card usage.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_reward")
public class CardReward extends BaseEntity {

    @Id
    @Column("reward_id")
    private Long rewardId;

    @Column("card_id")
    private Long cardId;

    @Column("transaction_id")
    private Long transactionId;

    @Column("party_id")
    private Long partyId;

    @Column("account_id")
    private Long accountId;

    @Column("program_id")
    private Long programId;

    @Column("reward_reference")
    private String rewardReference;

    @Column("reward_type")
    private String rewardType;

    @Column("reward_category")
    private String rewardCategory;

    @Column("reward_description")
    private String rewardDescription;

    @Column("reward_status")
    private String rewardStatus;

    @Column("is_earning")
    private Boolean isEarning;

    @Column("is_redemption")
    private Boolean isRedemption;

    @Column("is_adjustment")
    private Boolean isAdjustment;

    @Column("is_expiration")
    private Boolean isExpiration;

    @Column("points_earned")
    private BigDecimal pointsEarned;

    @Column("points_redeemed")
    private BigDecimal pointsRedeemed;

    @Column("points_adjusted")
    private BigDecimal pointsAdjusted;

    @Column("points_expired")
    private BigDecimal pointsExpired;

    @Column("points_balance")
    private BigDecimal pointsBalance;

    @Column("cash_value")
    private BigDecimal cashValue;

    @Column("currency_code")
    private String currencyCode;

    @Column("earning_rate")
    private BigDecimal earningRate;

    @Column("earning_multiplier")
    private BigDecimal earningMultiplier;

    @Column("earning_reason")
    private String earningReason;

    @Column("redemption_type")
    private String redemptionType;

    @Column("redemption_description")
    private String redemptionDescription;

    @Column("redemption_value")
    private BigDecimal redemptionValue;

    @Column("adjustment_reason")
    private String adjustmentReason;

    @Column("adjustment_description")
    private String adjustmentDescription;

    @Column("merchant_id")
    private String merchantId;

    @Column("merchant_name")
    private String merchantName;

    @Column("merchant_category")
    private String merchantCategory;

    @Column("transaction_amount")
    private BigDecimal transactionAmount;

    @Column("transaction_currency")
    private String transactionCurrency;

    @Column("transaction_date")
    private LocalDateTime transactionDate;

    @Column("posting_date")
    private LocalDateTime postingDate;

    @Column("expiration_date")
    private LocalDateTime expirationDate;

    @Column("is_promotional")
    private Boolean isPromotional;

    @Column("promotion_id")
    private Long promotionId;

    @Column("promotion_name")
    private String promotionName;

    @Column("is_transferable")
    private Boolean isTransferable;

    @Column("transfer_to_party_id")
    private Long transferToPartyId;

    @Column("transfer_from_party_id")
    private Long transferFromPartyId;

    @Column("transfer_date")
    private LocalDateTime transferDate;

    @Column("notes")
    private String notes;
}