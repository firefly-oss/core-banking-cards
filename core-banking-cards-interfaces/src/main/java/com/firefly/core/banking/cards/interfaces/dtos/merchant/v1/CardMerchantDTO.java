package com.firefly.core.banking.cards.interfaces.dtos.merchant.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Card Merchant.
 * Contains the essential information about a Card Merchant that needs to be exposed through the API.
 * A Card Merchant represents merchants that accept card payments, including merchant details, categories,
 * and relationships with card networks and issuers.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardMerchantDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long merchantId;

    private String merchantReference;

    private String merchantName;

    private String merchantLegalName;

    private String merchantDisplayName;

    private String merchantDescription;

    private String merchantCategoryCode;

    private String merchantCategoryName;

    private String merchantType;

    private String merchantStatus;

    private Boolean isActive;

    private LocalDateTime activationDate;

    private LocalDateTime deactivationDate;

    private String deactivationReason;

    private String taxId;

    private String registrationNumber;

    private String websiteUrl;

    private String logoUrl;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private String phone;

    private String email;

    private String contactPersonName;

    private String contactPersonTitle;

    private String contactPersonPhone;

    private String contactPersonEmail;

    private Boolean isOnline;

    private Boolean isPhysical;

    private Boolean isMobile;

    private Boolean isInternational;

    private String supportedCurrencies;

    private String defaultCurrency;

    private String supportedCardNetworks;

    private String supportedPaymentMethods;

    private Boolean isHighRisk;

    private String riskRating;

    private Integer riskScore;

    private LocalDateTime riskAssessmentDate;

    private Boolean isFraudSuspected;

    private String fraudReason;

    private LocalDateTime fraudReportDate;

    private Boolean isBlacklisted;

    private String blacklistReason;

    private LocalDateTime blacklistDate;

    private Boolean isSettlementEnabled;

    private String settlementFrequency;

    private Integer settlementDay;

    private String settlementBankName;

    private String settlementAccountNumber;

    private String settlementAccountName;

    private String settlementBankCode;

    private String settlementCurrency;

    private String acquirerId;

    private String acquirerName;

    private String processorId;

    private String processorName;

    private String terminalIds;

    private String notes;
}