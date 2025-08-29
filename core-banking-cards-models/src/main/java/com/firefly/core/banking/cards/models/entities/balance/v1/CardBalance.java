package com.firefly.core.banking.cards.models.entities.balance.v1;

import com.firefly.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Card Balance entity.
 * Represents the current balance of a card, including different types of balances like purchase balance,
 * cash advance balance, and balance transfers.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_balance")
public class CardBalance extends BaseEntity {

    @Id
    @Column("balance_id")
    private Long balanceId;

    @Column("card_id")
    private Long cardId;

    @Column("party_id")
    private Long partyId;

    @Column("account_id")
    private Long accountId;

    @Column("statement_id")
    private Long statementId;

    @Column("balance_type")
    private String balanceType;

    @Column("balance_category")
    private String balanceCategory;

    @Column("balance_description")
    private String balanceDescription;

    @Column("balance_amount")
    private BigDecimal balanceAmount;

    @Column("available_amount")
    private BigDecimal availableAmount;

    @Column("reserved_amount")
    private BigDecimal reservedAmount;

    @Column("pending_amount")
    private BigDecimal pendingAmount;

    @Column("currency_code")
    private String currencyCode;

    @Column("as_of_date")
    private LocalDateTime asOfDate;

    @Column("last_transaction_id")
    private Long lastTransactionId;

    @Column("last_transaction_timestamp")
    private LocalDateTime lastTransactionTimestamp;

    @Column("last_payment_id")
    private Long lastPaymentId;

    @Column("last_payment_timestamp")
    private LocalDateTime lastPaymentTimestamp;

    @Column("last_statement_id")
    private Long lastStatementId;

    @Column("last_statement_date")
    private LocalDateTime lastStatementDate;

    @Column("is_promotional_rate")
    private Boolean isPromotionalRate;

    @Column("promotion_id")
    private Long promotionId;

    @Column("promotion_end_date")
    private LocalDateTime promotionEndDate;

    @Column("interest_rate")
    private BigDecimal interestRate;

    @Column("annual_percentage_rate")
    private BigDecimal annualPercentageRate;

    @Column("accrued_interest")
    private BigDecimal accruedInterest;

    @Column("last_interest_accrual_date")
    private LocalDateTime lastInterestAccrualDate;

    @Column("next_interest_accrual_date")
    private LocalDateTime nextInterestAccrualDate;

    @Column("is_in_grace_period")
    private Boolean isInGracePeriod;

    @Column("grace_period_end_date")
    private LocalDateTime gracePeriodEndDate;

    @Column("minimum_payment_due")
    private BigDecimal minimumPaymentDue;

    @Column("minimum_payment_due_date")
    private LocalDateTime minimumPaymentDueDate;

    @Column("days_past_due")
    private Integer daysPastDue;

    @Column("is_delinquent")
    private Boolean isDelinquent;

    @Column("delinquency_start_date")
    private LocalDateTime delinquencyStartDate;

    @Column("delinquency_days")
    private Integer delinquencyDays;

    @Column("delinquency_stage")
    private String delinquencyStage;

    @Column("is_charged_off")
    private Boolean isChargedOff;

    @Column("charge_off_date")
    private LocalDateTime chargeOffDate;

    @Column("charge_off_amount")
    private BigDecimal chargeOffAmount;

    @Column("is_written_off")
    private Boolean isWrittenOff;

    @Column("write_off_date")
    private LocalDateTime writeOffDate;

    @Column("write_off_amount")
    private BigDecimal writeOffAmount;

    @Column("is_in_collection")
    private Boolean isInCollection;

    @Column("collection_start_date")
    private LocalDateTime collectionStartDate;

    @Column("collection_agency_id")
    private String collectionAgencyId;

    @Column("collection_reference")
    private String collectionReference;

    @Column("collection_status")
    private String collectionStatus;

    @Column("notes")
    private String notes;
}