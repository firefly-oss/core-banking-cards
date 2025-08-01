package com.catalis.core.banking.cards.models.entities.enrollment.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Card Enrollment entity.
 * Represents a cardholder's enrollment in a specific promotion, program, or feature.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_enrollment")
public class CardEnrollment extends BaseEntity {

    @Id
    @Column("enrollment_id")
    private Long enrollmentId;

    @Column("card_id")
    private Long cardId;

    @Column("party_id")
    private Long partyId;

    @Column("account_id")
    private Long accountId;

    @Column("enrollment_type")
    private String enrollmentType;

    @Column("enrollment_reference")
    private String enrollmentReference;

    @Column("enrollment_status")
    private String enrollmentStatus;

    @Column("enrollment_timestamp")
    private LocalDateTime enrollmentTimestamp;

    @Column("enrollment_channel")
    private String enrollmentChannel;

    @Column("enrollment_source")
    private String enrollmentSource;

    @Column("enrollment_agent")
    private String enrollmentAgent;

    @Column("promotion_id")
    private Long promotionId;

    @Column("program_id")
    private Long programId;

    @Column("feature_id")
    private Long featureId;

    @Column("feature_code")
    private String featureCode;

    @Column("feature_name")
    private String featureName;

    @Column("is_auto_enrolled")
    private Boolean isAutoEnrolled;

    @Column("is_opt_in")
    private Boolean isOptIn;

    @Column("is_opt_out")
    private Boolean isOptOut;

    @Column("opt_in_timestamp")
    private LocalDateTime optInTimestamp;

    @Column("opt_out_timestamp")
    private LocalDateTime optOutTimestamp;

    @Column("opt_out_reason")
    private String optOutReason;

    @Column("start_date")
    private LocalDateTime startDate;

    @Column("end_date")
    private LocalDateTime endDate;

    @Column("is_active")
    private Boolean isActive;

    @Column("activation_timestamp")
    private LocalDateTime activationTimestamp;

    @Column("deactivation_timestamp")
    private LocalDateTime deactivationTimestamp;

    @Column("deactivation_reason")
    private String deactivationReason;

    @Column("is_recurring")
    private Boolean isRecurring;

    @Column("recurrence_frequency")
    private String recurrenceFrequency;

    @Column("next_recurrence_date")
    private LocalDateTime nextRecurrenceDate;

    @Column("last_recurrence_date")
    private LocalDateTime lastRecurrenceDate;

    @Column("terms_accepted")
    private Boolean termsAccepted;

    @Column("terms_accepted_timestamp")
    private LocalDateTime termsAcceptedTimestamp;

    @Column("terms_version")
    private String termsVersion;

    @Column("confirmation_code")
    private String confirmationCode;

    @Column("confirmation_sent")
    private Boolean confirmationSent;

    @Column("confirmation_channel")
    private String confirmationChannel;

    @Column("confirmation_timestamp")
    private LocalDateTime confirmationTimestamp;

    @Column("confirmation_recipient")
    private String confirmationRecipient;

    @Column("notes")
    private String notes;
}