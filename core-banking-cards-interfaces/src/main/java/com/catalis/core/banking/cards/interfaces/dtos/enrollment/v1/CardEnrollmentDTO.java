package com.catalis.core.banking.cards.interfaces.dtos.enrollment.v1;

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
 * Data Transfer Object for Card Enrollment.
 * Contains the essential information about a Card Enrollment that needs to be exposed through the API.
 * A Card Enrollment represents a cardholder's enrollment in a specific promotion, program, or feature.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardEnrollmentDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long enrollmentId;

    @FilterableId
    private Long cardId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long accountId;

    private String enrollmentType;

    private String enrollmentReference;

    private String enrollmentStatus;

    private LocalDateTime enrollmentTimestamp;

    private String enrollmentChannel;

    private String enrollmentSource;

    private String enrollmentAgent;

    @FilterableId
    private Long promotionId;

    @FilterableId
    private Long programId;

    @FilterableId
    private Long featureId;

    private String featureCode;

    private String featureName;

    private Boolean isAutoEnrolled;

    private Boolean isOptIn;

    private Boolean isOptOut;

    private LocalDateTime optInTimestamp;

    private LocalDateTime optOutTimestamp;

    private String optOutReason;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isActive;

    private LocalDateTime activationTimestamp;

    private LocalDateTime deactivationTimestamp;

    private String deactivationReason;

    private Boolean isRecurring;

    private String recurrenceFrequency;

    private LocalDateTime nextRecurrenceDate;

    private LocalDateTime lastRecurrenceDate;

    private Boolean termsAccepted;

    private LocalDateTime termsAcceptedTimestamp;

    private String termsVersion;

    private String confirmationCode;

    private Boolean confirmationSent;

    private String confirmationChannel;

    private LocalDateTime confirmationTimestamp;

    private String confirmationRecipient;
}