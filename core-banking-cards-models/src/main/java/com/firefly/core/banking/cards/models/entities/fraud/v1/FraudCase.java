package com.firefly.core.banking.cards.models.entities.fraud.v1;

import com.firefly.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Fraud Case entity.
 * Represents a fraud case related to a card or transaction.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("fraud_case")
public class FraudCase extends BaseEntity {

    @Id
    @Column("fraud_case_id")
    private Long fraudCaseId;

    @Column("card_id")
    private Long cardId;

    @Column("transaction_id")
    private Long transactionId;

    @Column("party_id")
    private Long partyId;

    @Column("account_id")
    private Long accountId;

    @Column("case_reference")
    private String caseReference;

    @Column("provider_reference")
    private String providerReference;

    @Column("network_reference")
    private String networkReference;

    @Column("fraud_type")
    private String fraudType;

    @Column("fraud_reason_code")
    private String fraudReasonCode;

    @Column("fraud_reason_description")
    private String fraudReasonDescription;

    @Column("fraud_status")
    private String fraudStatus;

    @Column("fraud_score")
    private Integer fraudScore;

    @Column("risk_level")
    private String riskLevel;

    @Column("detection_source")
    private String detectionSource;

    @Column("detection_timestamp")
    private LocalDateTime detectionTimestamp;

    @Column("reported_by_party")
    private Boolean reportedByParty;

    @Column("reported_timestamp")
    private LocalDateTime reportedTimestamp;

    @Column("affected_amount")
    private BigDecimal affectedAmount;

    @Column("affected_currency")
    private String affectedCurrency;

    @Column("is_card_blocked")
    private Boolean isCardBlocked;

    @Column("block_timestamp")
    private LocalDateTime blockTimestamp;

    @Column("is_party_notified")
    private Boolean isPartyNotified;

    @Column("notification_timestamp")
    private LocalDateTime notificationTimestamp;

    @Column("notification_method")
    private String notificationMethod;

    @Column("is_cardholder_credited")
    private Boolean isCardholderCredited;

    @Column("credit_timestamp")
    private LocalDateTime creditTimestamp;

    @Column("credit_amount")
    private BigDecimal creditAmount;

    @Column("credit_currency")
    private String creditCurrency;

    @Column("resolution_timestamp")
    private LocalDateTime resolutionTimestamp;

    @Column("resolution_code")
    private String resolutionCode;

    @Column("resolution_description")
    private String resolutionDescription;

    @Column("is_police_report_filed")
    private Boolean isPoliceReportFiled;

    @Column("police_report_number")
    private String policeReportNumber;

    @Column("police_report_date")
    private LocalDateTime policeReportDate;

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