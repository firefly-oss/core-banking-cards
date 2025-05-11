package com.catalis.core.banking.cards.models.entities.fee.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Card Fee entity.
 * Represents fees associated with a card, such as annual fees, late payment fees, and transaction fees.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_fee")
public class CardFee extends BaseEntity {

    @Id
    @Column("fee_id")
    private Long feeId;

    @Column("card_id")
    private Long cardId;

    @Column("customer_id")
    private Long customerId;

    @Column("account_id")
    private Long accountId;

    @Column("transaction_id")
    private Long transactionId;

    @Column("statement_id")
    private Long statementId;

    @Column("program_id")
    private Long programId;

    @Column("fee_reference")
    private String feeReference;

    @Column("fee_type")
    private String feeType;

    @Column("fee_name")
    private String feeName;

    @Column("fee_description")
    private String feeDescription;

    @Column("fee_amount")
    private BigDecimal feeAmount;

    @Column("fee_percentage")
    private BigDecimal feePercentage;

    @Column("currency_code")
    private String currencyCode;

    @Column("fee_status")
    private String feeStatus;

    @Column("is_recurring")
    private Boolean isRecurring;

    @Column("recurrence_frequency")
    private String recurrenceFrequency;

    @Column("next_recurrence_date")
    private LocalDateTime nextRecurrenceDate;

    @Column("is_prorated")
    private Boolean isProrated;

    @Column("proration_rule")
    private String prorationRule;

    @Column("is_waived")
    private Boolean isWaived;

    @Column("waiver_reason")
    private String waiverReason;

    @Column("waiver_reference")
    private String waiverReference;

    @Column("waiver_timestamp")
    private LocalDateTime waiverTimestamp;

    @Column("waiver_expiry")
    private LocalDateTime waiverExpiry;

    @Column("waiver_authorized_by")
    private String waiverAuthorizedBy;

    @Column("is_refunded")
    private Boolean isRefunded;

    @Column("refund_reason")
    private String refundReason;

    @Column("refund_reference")
    private String refundReference;

    @Column("refund_timestamp")
    private LocalDateTime refundTimestamp;

    @Column("refund_amount")
    private BigDecimal refundAmount;

    @Column("refund_authorized_by")
    private String refundAuthorizedBy;

    @Column("charge_timestamp")
    private LocalDateTime chargeTimestamp;

    @Column("posting_timestamp")
    private LocalDateTime postingTimestamp;

    @Column("value_date")
    private LocalDateTime valueDate;

    @Column("tax_amount")
    private BigDecimal taxAmount;

    @Column("tax_rate")
    private BigDecimal taxRate;

    @Column("tax_type")
    private String taxType;

    @Column("total_amount")
    private BigDecimal totalAmount;

    @Column("is_billed")
    private Boolean isBilled;

    @Column("billing_timestamp")
    private LocalDateTime billingTimestamp;

    @Column("is_paid")
    private Boolean isPaid;

    @Column("payment_timestamp")
    private LocalDateTime paymentTimestamp;

    @Column("payment_reference")
    private String paymentReference;

    @Column("notes")
    private String notes;
}