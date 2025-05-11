package com.catalis.core.banking.cards.interfaces.dtos.dispute.v1;

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
 * Data Transfer Object for Card Dispute.
 * Contains the essential information about a Card Dispute that needs to be exposed through the API.
 * A Card Dispute represents a dispute filed by a cardholder against a transaction.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardDisputeDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long disputeId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long transactionId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long accountId;

    private String disputeReference;

    private String providerReference;

    private String networkReference;

    private String disputeReasonCode;

    private String disputeReasonDescription;

    private String disputeStatus;

    private String disputeStage;

    private BigDecimal disputeAmount;

    private String disputeCurrency;

    private LocalDateTime filingTimestamp;

    private LocalDateTime responseDueDate;

    private LocalDateTime resolutionTimestamp;

    private String resolutionCode;

    private String resolutionDescription;

    private Boolean isCardholderCredited;

    private LocalDateTime creditTimestamp;

    private BigDecimal creditAmount;

    private String creditCurrency;

    private Boolean isMerchantDebited;

    private LocalDateTime debitTimestamp;

    private BigDecimal debitAmount;

    private String debitCurrency;

    private String cardholderStatement;

    private String merchantResponse;

    private String evidenceDocuments;

    private Long assignedAgentId;

    private String assignedAgentName;

    private LocalDateTime lastUpdatedTimestamp;

    private String notes;
}