package com.catalis.core.banking.cards.interfaces.dtos.activity.v1;

import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Card Activity.
 * Contains the essential information about a Card Activity that needs to be exposed through the API.
 * A Card Activity represents a log of all activities related to a card, including status changes,
 * configuration changes, and other non-financial activities.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardActivityDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long activityId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long accountId;

    private String activityReference;

    private String activityType;

    private String activityCategory;

    private String activityDescription;

    private LocalDateTime activityTimestamp;

    private String activityChannel;

    private String activitySource;

    private String activityStatus;

    private String activityResult;

    private String activityDetails;

    private String previousValue;

    private String newValue;

    private String changeReason;

    private String changeAuthorizedBy;

    private String ipAddress;

    private String deviceId;

    private String deviceType;

    private String deviceOs;

    private String browserType;

    private String userAgent;

    private String geolocation;

    private Boolean isPartyInitiated;

    private Boolean isSystemInitiated;

    private Boolean isAgentInitiated;

    private String agentId;

    private String agentName;

    private Boolean isSuccessful;

    private String failureReason;

    private String failureCode;

    private Boolean isNotificationSent;

    private String notificationChannel;

    private LocalDateTime notificationTimestamp;

    private String notificationRecipient;

    private String relatedEntityType;

    private Long relatedEntityId;

    private String notes;
}