package com.firefly.core.banking.cards.interfaces.dtos.gateway.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Card Gateway.
 * Contains the essential information about a Card Gateway that needs to be exposed through the API.
 * A Card Gateway represents payment gateways that facilitate online card transactions,
 * including gateway details, capabilities, and integration information.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardGatewayDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long gatewayId;
    
    private String gatewayReference;
    
    private String gatewayName;
    
    private String gatewayLegalName;
    
    private String gatewayCode;
    
    private String gatewayDescription;
    
    private String gatewayType;
    
    private String gatewayStatus;
    
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
    
    @FilterableId
    private Long processorId;
    
    private String processorName;
    
    @FilterableId
    private Long acquirerId;
    
    private String acquirerName;
    
    private String apiBaseUrl;
    
    private String apiVersion;
    
    private String apiKey;
    
    private String apiSecret;
    
    private String apiUsername;
    
    private String apiPassword;
    
    private String apiCertificate;
    
    private String webhookUrl;
    
    private String webhookSecret;
    
    private String redirectUrl;
    
    private String callbackUrl;
    
    private String hostedPageUrl;
    
    private Integer merchantCount;
    
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
    
    private Boolean supportsTokenization;
    
    private Boolean supports3dSecure;
    
    private Boolean supportsRecurringPayments;
    
    private Boolean supportsSplitPayments;
    
    private Boolean supportsRefunds;
    
    private Boolean supportsPartialRefunds;
    
    private Boolean supportsVoid;
    
    private Boolean supportsCapture;
    
    private Boolean supportsPreAuth;
    
    private Boolean supportsApplePay;
    
    private Boolean supportsGooglePay;
    
    private Boolean supportsSamsungPay;
    
    private Boolean supportsPaypal;
    
    private String integrationType;
    
    private String integrationComplexity;
    
    private String integrationDocumentationUrl;
    
    private Boolean sdkAvailable;
    
    private String sdkPlatforms;
    
    private String sdkVersion;
    
    private String sdkDocumentationUrl;
    
    private LocalDateTime lastAuditDate;
    
    private LocalDateTime nextAuditDate;
}