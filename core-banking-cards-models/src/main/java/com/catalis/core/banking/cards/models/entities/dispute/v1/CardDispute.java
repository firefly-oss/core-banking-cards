package com.catalis.core.banking.cards.models.entities.dispute.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Card Dispute entity.
 * Represents a dispute filed by a cardholder against a transaction.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_dispute")
public class CardDispute extends BaseEntity {

    @Id
    @Column("dispute_id")
    private Long disputeId;

    @Column("card_id")
    private Long cardId;

    @Column("transaction_id")
    private Long transactionId;

    @Column("party_id")
    private Long partyId;

    @Column("account_id")
    private Long accountId;

    @Column("dispute_reference")
    private String disputeReference;

    @Column("provider_reference")
    private String providerReference;

    @Column("network_reference")
    private String networkReference;

    @Column("dispute_reason_code")
    private String disputeReasonCode;

    @Column("dispute_reason_description")
    private String disputeReasonDescription;

    @Column("dispute_status")
    private String disputeStatus;

    @Column("dispute_stage")
    private String disputeStage;

    @Column("dispute_amount")
    private BigDecimal disputeAmount;

    @Column("dispute_currency")
    private String disputeCurrency;

    @Column("filing_timestamp")
    private LocalDateTime filingTimestamp;

    @Column("response_due_date")
    private LocalDateTime responseDueDate;

    @Column("resolution_timestamp")
    private LocalDateTime resolutionTimestamp;

    @Column("resolution_code")
    private String resolutionCode;

    @Column("resolution_description")
    private String resolutionDescription;

    @Column("is_cardholder_credited")
    private Boolean isCardholderCredited;

    @Column("credit_timestamp")
    private LocalDateTime creditTimestamp;

    @Column("credit_amount")
    private BigDecimal creditAmount;

    @Column("credit_currency")
    private String creditCurrency;

    @Column("is_merchant_debited")
    private Boolean isMerchantDebited;

    @Column("debit_timestamp")
    private LocalDateTime debitTimestamp;

    @Column("debit_amount")
    private BigDecimal debitAmount;

    @Column("debit_currency")
    private String debitCurrency;

    @Column("cardholder_statement")
    private String cardholderStatement;

    @Column("merchant_response")
    private String merchantResponse;

    @Column("evidence_documents")
    private String evidenceDocuments;

    @Column("assigned_agent_id")
    private Long assignedAgentId;

    @Column("assigned_agent_name")
    private String assignedAgentName;

    @Column("last_updated_timestamp")
    private LocalDateTime lastUpdatedTimestamp;

    @Column("notes")
    private String notes;
}