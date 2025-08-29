package com.firefly.core.banking.cards.interfaces.dtos.program.v1;

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
 * Data Transfer Object for Card Program.
 * Contains the essential information about a Card Program that needs to be exposed through the API.
 * A Card Program represents a set of rules, configurations, and features for a group of cards.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardProgramDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long programId;

    private String programName;

    private String programCode;

    @FilterableId
    private Long issuerId;

    @FilterableId
    private Long binId;

    @FilterableId
    private Long cardTypeId;

    @FilterableId
    private Long cardNetworkId;

    @FilterableId
    private Long defaultDesignId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isActive;

    private Integer maxCardsPerParty;

    private Double defaultDailyLimit;

    private Double defaultMonthlyLimit;

    private Double defaultCreditLimit;

    private Integer defaultCardValidityYears;

    private Boolean supportsPhysicalCards;

    private Boolean supportsVirtualCards;

    private Boolean supportsContactless;

    private Boolean supportsInternational;

    private Boolean supportsAtmWithdrawal;

    private Boolean supportsOnlineTransactions;

    private Boolean supportsRecurringPayments;

    private Boolean supportsApplePay;

    private Boolean supportsGooglePay;

    private Boolean supportsSamsungPay;

    private Boolean requiresPin;

    private Boolean requiresActivation;

    private String currencyCode;

    private String countryCode;

    private String termsAndConditionsUrl;

    private String description;
}