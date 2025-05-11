package com.catalis.core.banking.cards.models.repositories.statement.v1;

import com.catalis.core.banking.cards.models.entities.statement.v1.CardStatement;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardStatement entities.
 */
@Repository
public interface CardStatementRepository extends BaseRepository<CardStatement, Long> {
    /**
     * Find a CardStatement by its ID.
     *
     * @param statementId the ID of the CardStatement to find
     * @return a Mono emitting the CardStatement if found, or empty if not found
     */
    Mono<CardStatement> findByStatementId(Long statementId);
    
    /**
     * Find a CardStatement by its reference.
     *
     * @param statementReference the reference of the CardStatement to find
     * @return a Mono emitting the CardStatement if found, or empty if not found
     */
    Mono<CardStatement> findByStatementReference(String statementReference);
    
    /**
     * Find CardStatements by card ID.
     *
     * @param cardId the card ID to search for
     * @return a Flux emitting the CardStatements for the specified card
     */
    Flux<CardStatement> findByCardId(Long cardId);
    
    /**
     * Find CardStatements by customer ID.
     *
     * @param customerId the customer ID to search for
     * @return a Flux emitting the CardStatements for the specified customer
     */
    Flux<CardStatement> findByCustomerId(Long customerId);
    
    /**
     * Find CardStatements by account ID.
     *
     * @param accountId the account ID to search for
     * @return a Flux emitting the CardStatements for the specified account
     */
    Flux<CardStatement> findByAccountId(Long accountId);
    
    /**
     * Find CardStatements by statement date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardStatements within the specified date range
     */
    Flux<CardStatement> findByStatementDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find CardStatements by due date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardStatements within the specified due date range
     */
    Flux<CardStatement> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Find CardStatements by payment status.
     *
     * @param paymentStatus the payment status to search for
     * @return a Flux emitting the CardStatements with the specified payment status
     */
    Flux<CardStatement> findByPaymentStatus(String paymentStatus);
    
    /**
     * Find generated CardStatements.
     *
     * @return a Flux emitting all generated CardStatements
     */
    Flux<CardStatement> findByIsGeneratedTrue();
    
    /**
     * Find delivered CardStatements.
     *
     * @return a Flux emitting all delivered CardStatements
     */
    Flux<CardStatement> findByIsDeliveredTrue();
    
    /**
     * Find viewed CardStatements.
     *
     * @return a Flux emitting all viewed CardStatements
     */
    Flux<CardStatement> findByIsViewedTrue();
    
    /**
     * Find CardStatements by delivery method.
     *
     * @param deliveryMethod the delivery method to search for
     * @return a Flux emitting the CardStatements with the specified delivery method
     */
    Flux<CardStatement> findByDeliveryMethod(String deliveryMethod);
}