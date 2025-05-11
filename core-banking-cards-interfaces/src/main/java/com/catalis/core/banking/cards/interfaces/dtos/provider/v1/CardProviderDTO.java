package com.catalis.core.banking.cards.interfaces.dtos.provider.v1;

import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.banking.cards.interfaces.enums.provider.v1.ProviderStatusEnum;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardProviderDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardProviderId;

    @FilterableId
    private Long cardId;

    private String providerName;
    private String providerCode;
    private String providerType;
    private String apiBaseUrl;
    private String apiVersion;
    private String apiKey;
    private String apiSecret;
    private String apiUsername;
    private String apiPassword;
    private String apiCertificate;
    private String webhookUrl;
    private String webhookSecret;
    private Boolean supportsPhysicalCards;
    private Boolean supportsVirtualCards;
    private Boolean supportsTokenization;
    private Boolean supports3dSecure;
    private Boolean supportsApplePay;
    private Boolean supportsGooglePay;
    private Boolean supportsSamsungPay;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String supportUrl;
    private String supportEmail;
    private String supportPhone;
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    private ProviderStatusEnum status;
    private LocalDateTime lastConnectionDate;
    private String lastConnectionStatus;
    private String description;
}