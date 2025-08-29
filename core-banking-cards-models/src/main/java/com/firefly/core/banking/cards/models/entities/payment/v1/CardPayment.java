package com.firefly.core.banking.cards.models.entities.payment.v1;

import com.firefly.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Card Payment entity.
 * Represents a payment made towards a card balance.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_payment")
public class CardPayment extends BaseEntity {

    @Id
    @Column("payment_id")
    private Long paymentId;

    @Column("card_id")
    private Long cardId;

    @Column("party_id")
    private Long partyId;

    @Column("account_id")
    private Long accountId;

    @Column("statement_id")
    private Long statementId;

    @Column("payment_reference")
    private String paymentReference;

    @Column("external_reference")
    private String externalReference;

    @Column("payment_amount")
    private BigDecimal paymentAmount;

    @Column("currency_code")
    private String currencyCode;

    @Column("payment_method")
    private String paymentMethod;

    @Column("payment_channel")
    private String paymentChannel;

    @Column("payment_status")
    private String paymentStatus;

    @Column("payment_timestamp")
    private LocalDateTime paymentTimestamp;

    @Column("posting_timestamp")
    private LocalDateTime postingTimestamp;

    @Column("value_date")
    private LocalDateTime valueDate;

    @Column("is_auto_payment")
    private Boolean isAutoPayment;

    @Column("is_minimum_payment")
    private Boolean isMinimumPayment;

    @Column("is_full_payment")
    private Boolean isFullPayment;

    @Column("is_scheduled_payment")
    private Boolean isScheduledPayment;

    @Column("scheduled_date")
    private LocalDateTime scheduledDate;

    @Column("recurrence_pattern")
    private String recurrencePattern;

    @Column("source_account_id")
    private String sourceAccountId;

    @Column("source_account_type")
    private String sourceAccountType;

    @Column("source_bank_code")
    private String sourceBankCode;

    @Column("source_bank_name")
    private String sourceBankName;

    @Column("source_account_holder")
    private String sourceAccountHolder;

    @Column("payment_processor")
    private String paymentProcessor;

    @Column("processor_fee")
    private BigDecimal processorFee;

    @Column("processor_reference")
    private String processorReference;

    @Column("confirmation_code")
    private String confirmationCode;

    @Column("failure_reason")
    private String failureReason;

    @Column("failure_code")
    private String failureCode;

    @Column("retry_count")
    private Integer retryCount;

    @Column("last_retry_timestamp")
    private LocalDateTime lastRetryTimestamp;

    @Column("next_retry_timestamp")
    private LocalDateTime nextRetryTimestamp;

    @Column("receipt_url")
    private String receiptUrl;

    @Column("notes")
    private String notes;
}