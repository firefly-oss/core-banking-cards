package com.firefly.core.banking.cards.models.entities.statement.v1;

import com.firefly.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Card Statement entity.
 * Represents a periodic statement of card activity, including transactions, fees, and balances.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_statement")
public class CardStatement extends BaseEntity {

    @Id
    @Column("statement_id")
    private Long statementId;

    @Column("card_id")
    private Long cardId;

    @Column("party_id")
    private Long partyId;

    @Column("account_id")
    private Long accountId;

    @Column("statement_reference")
    private String statementReference;

    @Column("statement_date")
    private LocalDateTime statementDate;

    @Column("statement_period_start")
    private LocalDateTime statementPeriodStart;

    @Column("statement_period_end")
    private LocalDateTime statementPeriodEnd;

    @Column("due_date")
    private LocalDateTime dueDate;

    @Column("closing_balance")
    private BigDecimal closingBalance;

    @Column("opening_balance")
    private BigDecimal openingBalance;

    @Column("minimum_payment_due")
    private BigDecimal minimumPaymentDue;

    @Column("total_payment_due")
    private BigDecimal totalPaymentDue;

    @Column("currency_code")
    private String currencyCode;

    @Column("total_purchases")
    private BigDecimal totalPurchases;

    @Column("total_cash_advances")
    private BigDecimal totalCashAdvances;

    @Column("total_fees")
    private BigDecimal totalFees;

    @Column("total_interest")
    private BigDecimal totalInterest;

    @Column("total_credits")
    private BigDecimal totalCredits;

    @Column("total_payments")
    private BigDecimal totalPayments;

    @Column("total_adjustments")
    private BigDecimal totalAdjustments;

    @Column("total_rewards_earned")
    private BigDecimal totalRewardsEarned;

    @Column("total_rewards_redeemed")
    private BigDecimal totalRewardsRedeemed;

    @Column("available_credit")
    private BigDecimal availableCredit;

    @Column("credit_limit")
    private BigDecimal creditLimit;

    @Column("cash_advance_limit")
    private BigDecimal cashAdvanceLimit;

    @Column("interest_rate")
    private BigDecimal interestRate;

    @Column("annual_percentage_rate")
    private BigDecimal annualPercentageRate;

    @Column("payment_status")
    private String paymentStatus;

    @Column("is_generated")
    private Boolean isGenerated;

    @Column("generation_timestamp")
    private LocalDateTime generationTimestamp;

    @Column("is_delivered")
    private Boolean isDelivered;

    @Column("delivery_method")
    private String deliveryMethod;

    @Column("delivery_timestamp")
    private LocalDateTime deliveryTimestamp;

    @Column("delivery_address")
    private String deliveryAddress;

    @Column("is_viewed")
    private Boolean isViewed;

    @Column("view_timestamp")
    private LocalDateTime viewTimestamp;

    @Column("document_url")
    private String documentUrl;

    @Column("notes")
    private String notes;
}