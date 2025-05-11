package com.catalis.core.banking.cards.interfaces.dtos.alert.v1;

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
 * Data Transfer Object for Card Alert.
 * Contains the essential information about a Card Alert that needs to be exposed through the API.
 * A Card Alert represents an alert or notification related to a card or transaction.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardAlertDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long alertId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long transactionId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long accountId;

    private String alertReference;

    private String alertType;

    private String alertCategory;

    private String alertSeverity;

    private String alertStatus;

    private String alertTitle;

    private String alertMessage;

    private String alertDetails;

    private String triggerEvent;

    private LocalDateTime triggerTimestamp;

    private BigDecimal triggerAmount;

    private String triggerCurrency;

    private String triggerLocation;

    private String triggerMerchant;

    private String triggerMerchantCategory;

    private Boolean isPartyConfigurable;

    private Boolean isSystemGenerated;

    private Boolean isRead;

    private LocalDateTime readTimestamp;

    private String deliveryChannel;

    private String deliveryStatus;

    private LocalDateTime deliveryTimestamp;

    private String deliveryRecipient;

    private String deliveryDetails;

    private LocalDateTime expirationTimestamp;

    private Boolean actionRequired;

    private String actionType;

    private String actionTaken;

    private LocalDateTime actionTimestamp;

    private String actionBy;
}