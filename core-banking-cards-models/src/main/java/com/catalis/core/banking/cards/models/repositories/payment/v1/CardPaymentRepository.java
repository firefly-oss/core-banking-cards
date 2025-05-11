package com.catalis.core.banking.cards.models.repositories.payment.v1;

import com.catalis.core.banking.cards.models.entities.payment.v1.CardPayment;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardPayment entities.
 */
@Repository
public interface CardPaymentRepository extends BaseRepository<CardPayment, Long> {
    /**
     * Find a CardPayment by its ID.
     *
     * @param paymentId the ID of the CardPayment to find
     * @return a Mono emitting the CardPayment if found, or empty if not found
     */
    Mono<CardPayment> findByPaymentId(Long paymentId);

    /**
     * Find a CardPayment by its reference.
     *
     * @param paymentReference the reference of the CardPayment to find
     * @return a Mono emitting the CardPayment if found, or empty if not found
     */
    Mono<CardPayment> findByPaymentReference(String paymentReference);

    /**
     * Find a CardPayment by its external reference.
     *
     * @param externalReference the external reference of the CardPayment to find
     * @return a Mono emitting the CardPayment if found, or empty if not found
     */
    Mono<CardPayment> findByExternalReference(String externalReference);

    /**
     * Find CardPayments by card ID.
     *
     * @param cardId the card ID to search for
     * @return a Flux emitting the CardPayments for the specified card
     */
    Flux<CardPayment> findByCardId(Long cardId);

    /**
     * Find CardPayments by card ID with pagination.
     *
     * @param cardId the card ID to search for
     * @param pageable the pagination information
     * @return a Flux emitting the CardPayments for the specified card with pagination
     */
    Flux<CardPayment> findByCardId(Long cardId, org.springframework.data.domain.Pageable pageable);

    /**
     * Count CardPayments by card ID.
     *
     * @param cardId the card ID to count for
     * @return a Mono emitting the count of CardPayments for the specified card
     */
    @org.springframework.data.r2dbc.repository.Query("SELECT COUNT(*) FROM card_payment WHERE card_id = :cardId")
    Mono<Long> countByCardId(Long cardId);

    /**
     * Find CardPayments by customer ID.
     *
     * @param customerId the customer ID to search for
     * @return a Flux emitting the CardPayments for the specified customer
     */
    Flux<CardPayment> findByCustomerId(Long customerId);

    /**
     * Find CardPayments by account ID.
     *
     * @param accountId the account ID to search for
     * @return a Flux emitting the CardPayments for the specified account
     */
    Flux<CardPayment> findByAccountId(Long accountId);

    /**
     * Find CardPayments by statement ID.
     *
     * @param statementId the statement ID to search for
     * @return a Flux emitting the CardPayments for the specified statement
     */
    Flux<CardPayment> findByStatementId(Long statementId);

    /**
     * Find CardPayments by payment status.
     *
     * @param paymentStatus the payment status to search for
     * @return a Flux emitting the CardPayments with the specified payment status
     */
    Flux<CardPayment> findByPaymentStatus(String paymentStatus);

    /**
     * Find CardPayments by payment method.
     *
     * @param paymentMethod the payment method to search for
     * @return a Flux emitting the CardPayments with the specified payment method
     */
    Flux<CardPayment> findByPaymentMethod(String paymentMethod);

    /**
     * Find CardPayments by payment channel.
     *
     * @param paymentChannel the payment channel to search for
     * @return a Flux emitting the CardPayments with the specified payment channel
     */
    Flux<CardPayment> findByPaymentChannel(String paymentChannel);

    /**
     * Find CardPayments by payment timestamp range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardPayments within the specified date range
     */
    Flux<CardPayment> findByPaymentTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find auto payments.
     *
     * @return a Flux emitting all auto payments
     */
    Flux<CardPayment> findByIsAutoPaymentTrue();

    /**
     * Find minimum payments.
     *
     * @return a Flux emitting all minimum payments
     */
    Flux<CardPayment> findByIsMinimumPaymentTrue();

    /**
     * Find full payments.
     *
     * @return a Flux emitting all full payments
     */
    Flux<CardPayment> findByIsFullPaymentTrue();

    /**
     * Find scheduled payments.
     *
     * @return a Flux emitting all scheduled payments
     */
    Flux<CardPayment> findByIsScheduledPaymentTrue();

    /**
     * Find scheduled payments for a specific date.
     *
     * @param scheduledDate the scheduled date to search for
     * @return a Flux emitting the scheduled payments for the specified date
     */
    Flux<CardPayment> findByIsScheduledPaymentTrueAndScheduledDate(LocalDateTime scheduledDate);
}