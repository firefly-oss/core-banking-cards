package com.firefly.core.banking.cards.models.repositories.promotion.v1;

import com.firefly.core.banking.cards.models.entities.promotion.v1.CardPromotion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CardPromotionRepository extends ReactiveCrudRepository<CardPromotion, Long> {

    Mono<CardPromotion> findByPromotionId(Long promotionId);

    Flux<CardPromotion> findByCardId(Long cardId, Pageable pageable);

    @Query("SELECT COUNT(*) FROM card_promotion WHERE card_id = :cardId")
    Mono<Long> countByCardId(Long cardId);
}