package com.catalis.core.banking.cards.interfaces.dtos.customer.v1;

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
 * Data Transfer Object for Card Customer.
 * Contains the essential information about a Card Customer that needs to be exposed through the API.
 * A Card Customer represents the relationship between a customer and their cards, including customer preferences,
 * risk profiles, and card-specific customer information.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardCustomerDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardCustomerId;
    
    @FilterableId
    private Long customerId;
    
    private String customerReference;
    
    private String customerType;
    
    private String customerStatus;
    
    private String firstName;
    
    private String middleName;
    
    private String lastName;
    
    private String email;
    
    private String phone;
    
    private LocalDateTime dateOfBirth;
    
    private String nationality;
    
    private String idType;
    
    private String idNumber;
    
    private LocalDateTime idExpiryDate;
    
    private String addressLine1;
    
    private String addressLine2;
    
    private String city;
    
    private String state;
    
    private String postalCode;
    
    private String country;
    
    private String preferredLanguage;
    
    private String preferredCommunicationChannel;
    
    private Boolean isMarketingConsent;
    
    private LocalDateTime marketingConsentTimestamp;
    
    private Boolean isTermsAccepted;
    
    private LocalDateTime termsAcceptedTimestamp;
    
    private String termsVersion;
    
    private LocalDateTime customerSince;
    
    private LocalDateTime lastActivityDate;
    
    private Integer totalCardsCount;
    
    private Integer activeCardsCount;
    
    private Integer creditScore;
    
    private String creditScoreProvider;
    
    private LocalDateTime creditScoreDate;
    
    private String riskRating;
    
    private Integer riskScore;
    
    private LocalDateTime riskAssessmentDate;
    
    private String kycStatus;
    
    private LocalDateTime kycVerificationDate;
    
    private String kycReference;
    
    private String amlStatus;
    
    private LocalDateTime amlCheckDate;
    
    private String amlReference;
    
    private BigDecimal totalCreditLimit;
    
    private BigDecimal availableCreditLimit;
    
    private String currencyCode;
    
    private String defaultPaymentMethod;
    
    private String defaultPaymentAccountId;
    
    private String defaultPaymentAccountType;
    
    private Boolean isAutoPaymentEnabled;
    
    private String autoPaymentType;
    
    private String autoPaymentAmount;
    
    private Integer autoPaymentDay;
    
    private Boolean isPaperlessStatements;
    
    private Boolean isTransactionAlerts;
    
    private Boolean isSecurityAlerts;
    
    private Boolean isPaymentReminders;
    
    private Boolean isRewardNotifications;
    
    private Boolean isPromotionNotifications;
    
    private Boolean isVip;
    
    private String vipTier;
    
    private LocalDateTime vipSince;
    
    @FilterableId
    private Long loyaltyProgramId;
    
    private String loyaltyProgramStatus;
    
    private BigDecimal loyaltyPointsBalance;
}