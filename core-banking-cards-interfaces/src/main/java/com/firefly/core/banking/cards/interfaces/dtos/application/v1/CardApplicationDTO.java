package com.firefly.core.banking.cards.interfaces.dtos.application.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Card Application.
 * Contains the essential information about a Card Application that needs to be exposed through the API.
 * A Card Application represents an application for a new card, including application details, status, and approval information.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardApplicationDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long applicationId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long accountId;

    private String applicationReference;

    private String applicationType;

    @FilterableId
    private Long cardTypeId;

    @FilterableId
    private Long programId;

    @FilterableId
    private Long designId;

    private String applicationStatus;

    private String applicationStage;

    private String applicationChannel;

    private LocalDateTime applicationTimestamp;

    private String applicantFirstName;

    private String applicantMiddleName;

    private String applicantLastName;

    private String applicantEmail;

    private String applicantPhone;

    private String applicantAddressLine1;

    private String applicantAddressLine2;

    private String applicantCity;

    private String applicantState;

    private String applicantPostalCode;

    private String applicantCountry;

    private String applicantIdType;

    private String applicantIdNumber;

    private LocalDateTime applicantIdExpiry;

    private LocalDateTime applicantDateOfBirth;

    private String applicantNationality;

    private String employmentStatus;

    private String employerName;

    private String employmentPosition;

    private BigDecimal annualIncome;

    private String incomeCurrency;

    private BigDecimal requestedCreditLimit;

    private BigDecimal approvedCreditLimit;

    private String currencyCode;

    private Boolean isPreApproved;

    private Boolean isInstantIssuance;

    private Boolean isDigitalOnly;

    private Boolean requiresPhysicalCard;

    private Boolean shippingAddressSameAsResidential;

    private String shippingAddressLine1;

    private String shippingAddressLine2;

    private String shippingCity;

    private String shippingState;

    private String shippingPostalCode;

    private String shippingCountry;

    private String shippingMethod;

    private Integer creditScore;

    private String creditScoreProvider;

    private LocalDateTime creditCheckTimestamp;

    private String creditCheckReference;

    private String kycStatus;

    private String kycReference;

    private LocalDateTime kycTimestamp;

    private String amlStatus;

    private String amlReference;

    private LocalDateTime amlTimestamp;

    private LocalDateTime approvalTimestamp;

    private String approvedBy;

    private String rejectionReason;

    private LocalDateTime rejectionTimestamp;

    private String rejectionCode;

    @FilterableId
    private Long cardId;

    private LocalDateTime cardIssuanceTimestamp;

    private Boolean termsAccepted;

    private LocalDateTime termsAcceptedTimestamp;

    private String termsVersion;

    private String supportingDocuments;
}