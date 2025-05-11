package com.catalis.core.banking.cards.models.repositories.balance.v1;

import com.catalis.core.banking.cards.models.entities.balance.v1.CardBalance;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardBalance entities.
 */
@Repository
public interface CardBalanceRepository extends BaseRepository<CardBalance, Long> {
    /**
     * Find a CardBalance by its ID.
     *
     * @param balanceId the ID of the CardBalance to find
     * @return a Mono emitting the CardBalance if found, or empty if not found
     */
    Mono<CardBalance> findByBalanceId(Long balanceId);

    /**
     * Find CardBalances by card ID.
     *
     * @param cardId the card ID to search for
     * @return a Flux emitting the CardBalances for the specified card
     */
    Flux<CardBalance> findByCardId(Long cardId);

    /**
     * Find CardBalances by card ID with pagination.
     *
     * @param cardId the card ID to search for
     * @param pageable the pagination information
     * @return a Flux emitting the CardBalances for the specified card with pagination
     */
    Flux<CardBalance> findByCardId(Long cardId, org.springframework.data.domain.Pageable pageable);

    /**
     * Count CardBalances by card ID.
     *
     * @param cardId the card ID to count for
     * @return a Mono emitting the count of CardBalances for the specified card
     */
    @org.springframework.data.r2dbc.repository.Query("SELECT COUNT(*) FROM card_balance WHERE card_id = :cardId")
    Mono<Long> countByCardId(Long cardId);

    /**
     * Find CardBalances by party ID.
     *
     * @param partyId the party ID to search for
     * @return a Flux emitting the CardBalances for the specified party
     */
    Flux<CardBalance> findByPartyId(Long partyId);

    /**
     * Find CardBalances by account ID.
     *
     * @param accountId the account ID to search for
     * @return a Flux emitting the CardBalances for the specified account
     */
    Flux<CardBalance> findByAccountId(Long accountId);

    /**
     * Find CardBalances by statement ID.
     *
     * @param statementId the statement ID to search for
     * @return a Flux emitting the CardBalances for the specified statement
     */
    Flux<CardBalance> findByStatementId(Long statementId);

    /**
     * Find CardBalances by balance type.
     *
     * @param balanceType the balance type to search for
     * @return a Flux emitting the CardBalances of the specified type
     */
    Flux<CardBalance> findByBalanceType(String balanceType);

    /**
     * Find CardBalances by balance category.
     *
     * @param balanceCategory the balance category to search for
     * @return a Flux emitting the CardBalances of the specified category
     */
    Flux<CardBalance> findByBalanceCategory(String balanceCategory);

    /**
     * Find CardBalances by currency code.
     *
     * @param currencyCode the currency code to search for
     * @return a Flux emitting the CardBalances with the specified currency
     */
    Flux<CardBalance> findByCurrencyCode(String currencyCode);

    /**
     * Find CardBalances with promotional rate.
     *
     * @return a Flux emitting all CardBalances with promotional rate
     */
    Flux<CardBalance> findByIsPromotionalRateTrue();

    /**
     * Find CardBalances in grace period.
     *
     * @return a Flux emitting all CardBalances in grace period
     */
    Flux<CardBalance> findByIsInGracePeriodTrue();

    /**
     * Find delinquent CardBalances.
     *
     * @return a Flux emitting all delinquent CardBalances
     */
    Flux<CardBalance> findByIsDelinquentTrue();

    /**
     * Find charged off CardBalances.
     *
     * @return a Flux emitting all charged off CardBalances
     */
    Flux<CardBalance> findByIsChargedOffTrue();

    /**
     * Find written off CardBalances.
     *
     * @return a Flux emitting all written off CardBalances
     */
    Flux<CardBalance> findByIsWrittenOffTrue();

    /**
     * Find CardBalances in collection.
     *
     * @return a Flux emitting all CardBalances in collection
     */
    Flux<CardBalance> findByIsInCollectionTrue();

    /**
     * Find CardBalances by promotion ID.
     *
     * @param promotionId the promotion ID to search for
     * @return a Flux emitting the CardBalances for the specified promotion
     */
    Flux<CardBalance> findByPromotionId(Long promotionId);

    /**
     * Find CardBalances by as of date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardBalances within the specified date range
     */
    Flux<CardBalance> findByAsOfDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find CardBalances by last transaction ID.
     *
     * @param lastTransactionId the last transaction ID to search for
     * @return a Flux emitting the CardBalances with the specified last transaction
     */
    Flux<CardBalance> findByLastTransactionId(Long lastTransactionId);
}