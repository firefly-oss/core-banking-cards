package com.firefly.core.banking.cards.models.repositories.reward.v1;

import com.firefly.core.banking.cards.models.entities.reward.v1.CardReward;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Repository for managing CardReward entities.
 */
@Repository
public interface CardRewardRepository extends BaseRepository<CardReward, UUID> {
    /**
     * Find a CardReward by its ID.
     *
     * @param rewardId the ID of the CardReward to find
     * @return a Mono emitting the CardReward if found, or empty if not found
     */
    Mono<CardReward> findByRewardId(UUID rewardId);

    /**
     * Find CardRewards by card ID.
     *
     * @param cardId the card ID to search for
     * @return a Flux emitting the CardRewards for the specified card
     */
    Flux<CardReward> findByCardId(UUID cardId);

    /**
     * Find CardRewards by card ID with pagination.
     *
     * @param cardId the card ID to search for
     * @param pageable the pagination information
     * @return a Flux emitting the CardRewards for the specified card with pagination
     */
    Flux<CardReward> findByCardId(UUID cardId, Pageable pageable);

    /**
     * Count CardRewards by card ID.
     *
     * @param cardId the card ID to count for
     * @return a Mono emitting the count of CardRewards for the specified card
     */
    Mono<Long> countByCardId(UUID cardId);

    /**
     * Find CardRewards by transaction ID.
     *
     * @param transactionId the transaction ID to search for
     * @return a Flux emitting the CardRewards for the specified transaction
     */
    Flux<CardReward> findByTransactionId(UUID transactionId);

    /**
     * Find CardRewards by party ID.
     *
     * @param partyId the party ID to search for
     * @return a Flux emitting the CardRewards for the specified party
     */
    Flux<CardReward> findByPartyId(UUID partyId);

    /**
     * Find CardRewards by account ID.
     *
     * @param accountId the account ID to search for
     * @return a Flux emitting the CardRewards for the specified account
     */
    Flux<CardReward> findByAccountId(UUID accountId);

    /**
     * Find CardRewards by program ID.
     *
     * @param programId the program ID to search for
     * @return a Flux emitting the CardRewards for the specified program
     */
    Flux<CardReward> findByProgramId(UUID programId);

    /**
     * Find CardRewards by reward type.
     *
     * @param rewardType the reward type to search for
     * @return a Flux emitting the CardRewards of the specified type
     */
    Flux<CardReward> findByRewardType(String rewardType);

    /**
     * Find CardRewards by reward category.
     *
     * @param rewardCategory the reward category to search for
     * @return a Flux emitting the CardRewards of the specified category
     */
    Flux<CardReward> findByRewardCategory(String rewardCategory);

    /**
     * Find CardRewards by reward status.
     *
     * @param rewardStatus the reward status to search for
     * @return a Flux emitting the CardRewards with the specified status
     */
    Flux<CardReward> findByRewardStatus(String rewardStatus);

    /**
     * Find earning CardRewards.
     *
     * @return a Flux emitting all earning CardRewards
     */
    Flux<CardReward> findByIsEarningTrue();

    /**
     * Find redemption CardRewards.
     *
     * @return a Flux emitting all redemption CardRewards
     */
    Flux<CardReward> findByIsRedemptionTrue();

    /**
     * Find adjustment CardRewards.
     *
     * @return a Flux emitting all adjustment CardRewards
     */
    Flux<CardReward> findByIsAdjustmentTrue();

    /**
     * Find expiration CardRewards.
     *
     * @return a Flux emitting all expiration CardRewards
     */
    Flux<CardReward> findByIsExpirationTrue();

    /**
     * Find promotional CardRewards.
     *
     * @return a Flux emitting all promotional CardRewards
     */
    Flux<CardReward> findByIsPromotionalTrue();

    /**
     * Find CardRewards by promotion ID.
     *
     * @param promotionId the promotion ID to search for
     * @return a Flux emitting the CardRewards for the specified promotion
     */
    Flux<CardReward> findByPromotionId(UUID promotionId);

    /**
     * Find CardRewards by transaction date range.
     *
     * @param startDate the start date of the range
     * @param endDate the end date of the range
     * @return a Flux emitting the CardRewards within the specified date range
     */
    Flux<CardReward> findByTransactionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
