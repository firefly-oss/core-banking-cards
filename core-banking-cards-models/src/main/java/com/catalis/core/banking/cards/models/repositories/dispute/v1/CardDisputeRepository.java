package com.catalis.core.banking.cards.models.repositories.dispute.v1;

import com.catalis.core.banking.cards.models.entities.dispute.v1.CardDispute;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for managing CardDispute entities.
 */
@Repository
public interface CardDisputeRepository extends BaseRepository<CardDispute, Long> {
    /**
     * Find a CardDispute by its ID.
     *
     * @param disputeId the ID of the CardDispute to find
     * @return a Mono emitting the CardDispute if found, or empty if not found
     */
    Mono<CardDispute> findByDisputeId(Long disputeId);

    /**
     * Find a CardDispute by its reference.
     *
     * @param disputeReference the reference of the CardDispute to find
     * @return a Mono emitting the CardDispute if found, or empty if not found
     */
    Mono<CardDispute> findByDisputeReference(String disputeReference);

    /**
     * Find CardDisputes by card ID.
     *
     * @param cardId the card ID to search for
     * @return a Flux emitting the CardDisputes for the specified card
     */
    Flux<CardDispute> findByCardId(Long cardId);

    /**
     * Find CardDisputes by card ID with pagination.
     *
     * @param cardId the card ID to search for
     * @param pageable the pagination information
     * @return a Flux emitting the CardDisputes for the specified card with pagination
     */
    Flux<CardDispute> findByCardId(Long cardId, org.springframework.data.domain.Pageable pageable);

    /**
     * Count CardDisputes by card ID.
     *
     * @param cardId the card ID to count for
     * @return a Mono emitting the count of CardDisputes for the specified card
     */
    @org.springframework.data.r2dbc.repository.Query("SELECT COUNT(*) FROM card_dispute WHERE card_id = :cardId")
    Mono<Long> countByCardId(Long cardId);

    /**
     * Find CardDisputes by transaction ID.
     *
     * @param transactionId the transaction ID to search for
     * @return a Flux emitting the CardDisputes for the specified transaction
     */
    Flux<CardDispute> findByTransactionId(Long transactionId);

    /**
     * Find CardDisputes by customer ID.
     *
     * @param customerId the customer ID to search for
     * @return a Flux emitting the CardDisputes for the specified customer
     */
    Flux<CardDispute> findByCustomerId(Long customerId);

    /**
     * Find CardDisputes by account ID.
     *
     * @param accountId the account ID to search for
     * @return a Flux emitting the CardDisputes for the specified account
     */
    Flux<CardDispute> findByAccountId(Long accountId);

    /**
     * Find CardDisputes by provider reference.
     *
     * @param providerReference the provider reference to search for
     * @return a Flux emitting the CardDisputes with the specified provider reference
     */
    Flux<CardDispute> findByProviderReference(String providerReference);

    /**
     * Find CardDisputes by network reference.
     *
     * @param networkReference the network reference to search for
     * @return a Flux emitting the CardDisputes with the specified network reference
     */
    Flux<CardDispute> findByNetworkReference(String networkReference);

    /**
     * Find CardDisputes by dispute reason code.
     *
     * @param disputeReasonCode the dispute reason code to search for
     * @return a Flux emitting the CardDisputes with the specified reason code
     */
    Flux<CardDispute> findByDisputeReasonCode(String disputeReasonCode);

    /**
     * Find CardDisputes by dispute status.
     *
     * @param disputeStatus the dispute status to search for
     * @return a Flux emitting the CardDisputes with the specified status
     */
    Flux<CardDispute> findByDisputeStatus(String disputeStatus);

    /**
     * Find CardDisputes by dispute stage.
     *
     * @param disputeStage the dispute stage to search for
     * @return a Flux emitting the CardDisputes at the specified stage
     */
    Flux<CardDispute> findByDisputeStage(String disputeStage);

    /**
     * Find CardDisputes by filing timestamp range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardDisputes within the specified filing date range
     */
    Flux<CardDispute> findByFilingTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find CardDisputes by response due date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardDisputes within the specified due date range
     */
    Flux<CardDispute> findByResponseDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find CardDisputes by resolution timestamp range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardDisputes within the specified resolution date range
     */
    Flux<CardDispute> findByResolutionTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find CardDisputes where the cardholder has been credited.
     *
     * @return a Flux emitting all CardDisputes where the cardholder has been credited
     */
    Flux<CardDispute> findByIsCardholderCreditedTrue();

    /**
     * Find CardDisputes where the merchant has been debited.
     *
     * @return a Flux emitting all CardDisputes where the merchant has been debited
     */
    Flux<CardDispute> findByIsMerchantDebitedTrue();

    /**
     * Find CardDisputes by assigned agent ID.
     *
     * @param assignedAgentId the assigned agent ID to search for
     * @return a Flux emitting the CardDisputes assigned to the specified agent
     */
    Flux<CardDispute> findByAssignedAgentId(Long assignedAgentId);
}