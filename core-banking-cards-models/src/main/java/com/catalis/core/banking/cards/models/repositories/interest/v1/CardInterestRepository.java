package com.catalis.core.banking.cards.models.repositories.interest.v1;

import com.catalis.core.banking.cards.models.entities.interest.v1.CardInterest;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardInterest entities.
 */
@Repository
public interface CardInterestRepository extends BaseRepository<CardInterest, Long> {
    /**
     * Find a CardInterest by its ID.
     *
     * @param interestId the ID of the CardInterest to find
     * @return a Mono emitting the CardInterest if found, or empty if not found
     */
    Mono<CardInterest> findByInterestId(Long interestId);

    /**
     * Find a CardInterest by its reference.
     *
     * @param interestReference the reference of the CardInterest to find
     * @return a Mono emitting the CardInterest if found, or empty if not found
     */
    Mono<CardInterest> findByInterestReference(String interestReference);

    /**
     * Find CardInterests by card ID.
     *
     * @param cardId the card ID to search for
     * @return a Flux emitting the CardInterests for the specified card
     */
    Flux<CardInterest> findByCardId(Long cardId);

    /**
     * Find CardInterests by card ID with pagination.
     *
     * @param cardId the card ID to search for
     * @param pageable the pagination information
     * @return a Flux emitting the CardInterests for the specified card with pagination
     */
    Flux<CardInterest> findByCardId(Long cardId, org.springframework.data.domain.Pageable pageable);

    /**
     * Count CardInterests by card ID.
     *
     * @param cardId the card ID to count for
     * @return a Mono emitting the count of CardInterests for the specified card
     */
    @org.springframework.data.r2dbc.repository.Query("SELECT COUNT(*) FROM card_interest WHERE card_id = :cardId")
    Mono<Long> countByCardId(Long cardId);

    /**
     * Find CardInterests by customer ID.
     *
     * @param customerId the customer ID to search for
     * @return a Flux emitting the CardInterests for the specified customer
     */
    Flux<CardInterest> findByCustomerId(Long customerId);

    /**
     * Find CardInterests by account ID.
     *
     * @param accountId the account ID to search for
     * @return a Flux emitting the CardInterests for the specified account
     */
    Flux<CardInterest> findByAccountId(Long accountId);

    /**
     * Find CardInterests by statement ID.
     *
     * @param statementId the statement ID to search for
     * @return a Flux emitting the CardInterests for the specified statement
     */
    Flux<CardInterest> findByStatementId(Long statementId);

    /**
     * Find CardInterests by program ID.
     *
     * @param programId the program ID to search for
     * @return a Flux emitting the CardInterests for the specified program
     */
    Flux<CardInterest> findByProgramId(Long programId);

    /**
     * Find CardInterests by interest type.
     *
     * @param interestType the interest type to search for
     * @return a Flux emitting the CardInterests of the specified type
     */
    Flux<CardInterest> findByInterestType(String interestType);

    /**
     * Find variable rate CardInterests.
     *
     * @return a Flux emitting all variable rate CardInterests
     */
    Flux<CardInterest> findByIsVariableRateTrue();

    /**
     * Find promotional rate CardInterests.
     *
     * @return a Flux emitting all promotional rate CardInterests
     */
    Flux<CardInterest> findByIsPromotionalRateTrue();

    /**
     * Find CardInterests in grace period.
     *
     * @return a Flux emitting all CardInterests in grace period
     */
    Flux<CardInterest> findByIsGracePeriodTrue();

    /**
     * Find charged CardInterests.
     *
     * @return a Flux emitting all charged CardInterests
     */
    Flux<CardInterest> findByIsChargedTrue();

    /**
     * Find waived CardInterests.
     *
     * @return a Flux emitting all waived CardInterests
     */
    Flux<CardInterest> findByIsWaivedTrue();

    /**
     * Find billed CardInterests.
     *
     * @return a Flux emitting all billed CardInterests
     */
    Flux<CardInterest> findByIsBilledTrue();

    /**
     * Find paid CardInterests.
     *
     * @return a Flux emitting all paid CardInterests
     */
    Flux<CardInterest> findByIsPaidTrue();

    /**
     * Find CardInterests by charge timestamp range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardInterests within the specified date range
     */
    Flux<CardInterest> findByChargeTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find CardInterests by accrual period.
     *
     * @param startDate the start date of the accrual period
     * @param endDate the end date of the accrual period
     * @return a Flux emitting the CardInterests within the specified accrual period
     */
    Flux<CardInterest> findByAccrualStartDateBeforeAndAccrualEndDateAfter(
            LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find CardInterests by promotion ID.
     *
     * @param promotionId the promotion ID to search for
     * @return a Flux emitting the CardInterests for the specified promotion
     */
    Flux<CardInterest> findByPromotionId(Long promotionId);
}