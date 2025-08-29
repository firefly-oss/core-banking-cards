package com.firefly.core.banking.cards.interfaces.dtos.processor.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Card Processor.
 * Contains the essential information about a Card Processor that needs to be exposed through the API.
 * A Card Processor represents entities that process card transactions between acquirers, card networks, and issuers,
 * including processor details, capabilities, and relationships.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardProcessorDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long processorId;
    
    private String processorReference;
    
    private String processorName;
    
    private String processorLegalName;
    
    private String processorCode;
    
    private String processorDescription;
    
    private String processorType;
    
    private String processorStatus;
    
    private Boolean isActive;
    
    private LocalDateTime activationDate;
    
    private LocalDateTime deactivationDate;
    
    private String deactivationReason;
    
    private String taxId;
    
    private String registrationNumber;
    
    private String licenseNumber;
    
    private LocalDateTime licenseExpiryDate;
    
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
    
    private String supportedCardNetworks;
    
    private String supportedPaymentMethods;
    
    private String supportedCurrencies;
    
    private String defaultCurrency;
    
    private String supportedCountries;
    
    private Boolean isInternational;
    
    private Boolean isDomestic;
    
    private Boolean isIssuerProcessor;
    
    private Boolean isAcquirerProcessor;
    
    private Boolean isSwitchProcessor;
    
    private Boolean isGateway;
    
    private String apiBaseUrl;
    
    private String apiVersion;
    
    private String apiKey;
    
    private String apiSecret;
    
    private String apiUsername;
    
    private String apiPassword;
    
    private String apiCertificate;
    
    private String webhookUrl;
    
    private String webhookSecret;
    
    private Integer acquirerCount;
    
    private Integer issuerCount;
    
    private Integer merchantCount;
    
    private Integer terminalCount;
    
    private Long transactionVolumeDailyAvg;
    
    private Long transactionValueDailyAvg;
    
    private Integer processingTimeAvgMs;
    
    private Double uptimePercentage;
    
    private LocalDateTime lastDowntimeDate;
    
    private Integer lastDowntimeDurationMinutes;
    
    private String settlementFrequency;
    
    private Integer settlementDelayDays;
    
    private String feeStructure;
    
    private Double processingFeePercentage;
    
    private Double processingFeeFlat;
    
    private Double chargebackFee;
    
    private Double refundFee;
    
    private LocalDateTime contractStartDate;
    
    private LocalDateTime contractEndDate;
    
    private LocalDateTime contractRenewalDate;
    
    private String contractStatus;
    
    private Boolean isPciCompliant;
    
    private String pciComplianceLevel;
    
    private LocalDateTime pciComplianceDate;
    
    private LocalDateTime pciComplianceExpiry;
    
    private Boolean isEmvCompliant;
    
    private LocalDateTime emvComplianceDate;
    
    private Boolean isPaDssCompliant;
    
    private LocalDateTime paDssComplianceDate;
    
    private LocalDateTime paDssComplianceExpiry;
    
    private Boolean supportsTokenization;
    
    private Boolean supports3dSecure;
    
    private Boolean supportsEmv;
    
    private Boolean supportsContactless;
    
    private Boolean supportsApplePay;
    
    private Boolean supportsGooglePay;
    
    private Boolean supportsSamsungPay;
    
    private LocalDateTime lastAuditDate;
    
    private LocalDateTime nextAuditDate;
}