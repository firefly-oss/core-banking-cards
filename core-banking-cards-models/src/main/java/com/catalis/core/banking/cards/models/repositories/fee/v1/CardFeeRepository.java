package com.catalis.core.banking.cards.models.repositories.fee.v1;

import com.catalis.core.banking.cards.models.entities.fee.v1.CardFee;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardFee entities.
 */
@Repository
public interface CardFeeRepository extends BaseRepository<CardFee, Long> {
    /**
     * Find a CardFee by its ID.
     *
     * @param feeId the ID of the CardFee to find
     * @return a Mono emitting the CardFee if found, or empty if not found
     */
    Mono<CardFee> findByFeeId(Long feeId);
    
    /**
     * Find a CardFee by its reference.
     *
     * @param feeReference the reference of the CardFee to find
     * @return a Mono emitting the CardFee if found, or empty if not found
     */
    Mono<CardFee> findByFeeReference(String feeReference);
    
    /**
     * Find CardFees by card ID.
     *
     * @param cardId the card ID to search for
     * @return a Flux emitting the CardFees for the specified card
     */
    Flux<CardFee> findByCardId(Long cardId);
    
    /**
     * Find CardFees by customer ID.
     *
     * @param customerId the customer ID to search for
     * @return a Flux emitting the CardFees for the specified customer
     */
    Flux<CardFee> findByCustomerId(Long customerId);
    
    /**
     * Find CardFees by account ID.
     *
     * @param accountId the account ID to search for
     * @return a Flux emitting the CardFees for the specified account
     */
    Flux<CardFee> findByAccountId(Long accountId);
    
    /**
     * Find CardFees by transaction ID.
     *
     * @param transactionId the transaction ID to search for
     * @return a Flux emitting the CardFees for the specified transaction
     */
    Flux<CardFee> findByTransactionId(Long transactionId);
    
    /**
     * Find CardFees by statement ID.
     *
     * @param statementId the statement ID to search for
     * @return a Flux emitting the CardFees for the specified statement
     */
    Flux<CardFee> findByStatementId(Long statementId);
    
    /**
     * Find CardFees by program ID.
     *
     * @param programId the program ID to search for
     * @return a Flux emitting the CardFees for the specified program
     */
    Flux<CardFee> findByProgramId(Long programId);
    
    /**
     * Find CardFees by fee type.
     *
     * @param feeType the fee type to search for
     * @return a Flux emitting the CardFees of the specified type
     */
    Flux<CardFee> findByFeeType(String feeType);
    
    /**
     * Find CardFees by fee status.
     *
     * @param feeStatus the fee status to search for
     * @return a Flux emitting the CardFees with the specified status
     */
    Flux<CardFee> findByFeeStatus(String feeStatus);
    
    /**
     * Find recurring CardFees.
     *
     * @return a Flux emitting all recurring CardFees
     */
    Flux<CardFee> findByIsRecurringTrue();
    
    /**
     * Find waived CardFees.
     *
     * @return a Flux emitting all waived CardFees
     */
    Flux<CardFee> findByIsWaivedTrue();
    
    /**
     * Find refunded CardFees.
     *
     * @return a Flux emitting all refunded CardFees
     */
    Flux<CardFee> findByIsRefundedTrue();
    
    /**
     * Find billed CardFees.
     *
     * @return a Flux emitting all billed CardFees
     */
    Flux<CardFee> findByIsBilledTrue();
    
    /**
     * Find paid CardFees.
     *
     * @return a Flux emitting all paid CardFees
     */
    Flux<CardFee> findByIsPaidTrue();
    
    /**
     * Find CardFees by charge timestamp range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardFees within the specified date range
     */
    Flux<CardFee> findByChargeTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find CardFees with next recurrence date before a specific date.
     *
     * @param date the date to compare against
     * @return a Flux emitting the CardFees with next recurrence date before the specified date
     */
    Flux<CardFee> findByIsRecurringTrueAndNextRecurrenceDateBefore(LocalDateTime date);
}