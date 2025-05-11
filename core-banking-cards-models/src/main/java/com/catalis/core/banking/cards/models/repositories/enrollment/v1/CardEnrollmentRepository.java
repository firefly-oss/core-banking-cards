package com.catalis.core.banking.cards.models.repositories.enrollment.v1;

import com.catalis.core.banking.cards.models.entities.enrollment.v1.CardEnrollment;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardEnrollment entities.
 */
@Repository
public interface CardEnrollmentRepository extends BaseRepository<CardEnrollment, Long> {
    /**
     * Find a CardEnrollment by its ID.
     *
     * @param enrollmentId the ID of the CardEnrollment to find
     * @return a Mono emitting the CardEnrollment if found, or empty if not found
     */
    Mono<CardEnrollment> findByEnrollmentId(Long enrollmentId);

    /**
     * Find a CardEnrollment by its reference.
     *
     * @param enrollmentReference the reference of the CardEnrollment to find
     * @return a Mono emitting the CardEnrollment if found, or empty if not found
     */
    Mono<CardEnrollment> findByEnrollmentReference(String enrollmentReference);

    /**
     * Find CardEnrollments by card ID.
     *
     * @param cardId the card ID to search for
     * @return a Flux emitting the CardEnrollments for the specified card
     */
    Flux<CardEnrollment> findByCardId(Long cardId);

    /**
     * Find CardEnrollments by card ID with pagination.
     *
     * @param cardId the card ID to search for
     * @param pageable the pagination information
     * @return a Flux emitting the CardEnrollments for the specified card with pagination
     */
    Flux<CardEnrollment> findByCardId(Long cardId, org.springframework.data.domain.Pageable pageable);

    /**
     * Count CardEnrollments by card ID.
     *
     * @param cardId the card ID to count for
     * @return a Mono emitting the count of CardEnrollments for the specified card
     */
    @org.springframework.data.r2dbc.repository.Query("SELECT COUNT(*) FROM card_enrollment WHERE card_id = :cardId")
    Mono<Long> countByCardId(Long cardId);

    /**
     * Find CardEnrollments by customer ID.
     *
     * @param customerId the customer ID to search for
     * @return a Flux emitting the CardEnrollments for the specified customer
     */
    Flux<CardEnrollment> findByCustomerId(Long customerId);

    /**
     * Find CardEnrollments by account ID.
     *
     * @param accountId the account ID to search for
     * @return a Flux emitting the CardEnrollments for the specified account
     */
    Flux<CardEnrollment> findByAccountId(Long accountId);

    /**
     * Find CardEnrollments by enrollment type.
     *
     * @param enrollmentType the enrollment type to search for
     * @return a Flux emitting the CardEnrollments of the specified type
     */
    Flux<CardEnrollment> findByEnrollmentType(String enrollmentType);

    /**
     * Find CardEnrollments by enrollment status.
     *
     * @param enrollmentStatus the enrollment status to search for
     * @return a Flux emitting the CardEnrollments with the specified status
     */
    Flux<CardEnrollment> findByEnrollmentStatus(String enrollmentStatus);

    /**
     * Find CardEnrollments by enrollment timestamp range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardEnrollments within the specified date range
     */
    Flux<CardEnrollment> findByEnrollmentTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find CardEnrollments by enrollment channel.
     *
     * @param enrollmentChannel the enrollment channel to search for
     * @return a Flux emitting the CardEnrollments from the specified channel
     */
    Flux<CardEnrollment> findByEnrollmentChannel(String enrollmentChannel);

    /**
     * Find CardEnrollments by enrollment source.
     *
     * @param enrollmentSource the enrollment source to search for
     * @return a Flux emitting the CardEnrollments from the specified source
     */
    Flux<CardEnrollment> findByEnrollmentSource(String enrollmentSource);

    /**
     * Find CardEnrollments by promotion ID.
     *
     * @param promotionId the promotion ID to search for
     * @return a Flux emitting the CardEnrollments for the specified promotion
     */
    Flux<CardEnrollment> findByPromotionId(Long promotionId);

    /**
     * Find CardEnrollments by program ID.
     *
     * @param programId the program ID to search for
     * @return a Flux emitting the CardEnrollments for the specified program
     */
    Flux<CardEnrollment> findByProgramId(Long programId);

    /**
     * Find CardEnrollments by feature ID.
     *
     * @param featureId the feature ID to search for
     * @return a Flux emitting the CardEnrollments for the specified feature
     */
    Flux<CardEnrollment> findByFeatureId(Long featureId);

    /**
     * Find CardEnrollments by feature code.
     *
     * @param featureCode the feature code to search for
     * @return a Flux emitting the CardEnrollments for the specified feature code
     */
    Flux<CardEnrollment> findByFeatureCode(String featureCode);

    /**
     * Find auto-enrolled CardEnrollments.
     *
     * @return a Flux emitting all auto-enrolled CardEnrollments
     */
    Flux<CardEnrollment> findByIsAutoEnrolledTrue();

    /**
     * Find opt-in CardEnrollments.
     *
     * @return a Flux emitting all opt-in CardEnrollments
     */
    Flux<CardEnrollment> findByIsOptInTrue();

    /**
     * Find opt-out CardEnrollments.
     *
     * @return a Flux emitting all opt-out CardEnrollments
     */
    Flux<CardEnrollment> findByIsOptOutTrue();

    /**
     * Find active CardEnrollments.
     *
     * @return a Flux emitting all active CardEnrollments
     */
    Flux<CardEnrollment> findByIsActiveTrue();

    /**
     * Find recurring CardEnrollments.
     *
     * @return a Flux emitting all recurring CardEnrollments
     */
    Flux<CardEnrollment> findByIsRecurringTrue();

    /**
     * Find CardEnrollments with accepted terms.
     *
     * @return a Flux emitting all CardEnrollments with accepted terms
     */
    Flux<CardEnrollment> findByTermsAcceptedTrue();

    /**
     * Find CardEnrollments with next recurrence date before a specific date.
     *
     * @param date the date to compare against
     * @return a Flux emitting the CardEnrollments with next recurrence date before the specified date
     */
    Flux<CardEnrollment> findByIsRecurringTrueAndNextRecurrenceDateBefore(LocalDateTime date);
}