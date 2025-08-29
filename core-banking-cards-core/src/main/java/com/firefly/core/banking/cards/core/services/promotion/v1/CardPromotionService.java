package com.firefly.core.banking.cards.core.services.promotion.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.promotion.v1.CardPromotionDTO;
import reactor.core.publisher.Mono;

public interface CardPromotionService {

    /**
     * List all promotions (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardPromotionDTO>> listPromotions(
            Long cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new promotion for a specific card.
     */
    Mono<CardPromotionDTO> createPromotion(Long cardId, CardPromotionDTO promotionDTO);

    /**
     * Retrieve a specific promotion by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardPromotionDTO> getPromotion(Long cardId, Long promotionId);

    /**
     * Update an existing promotion for a specific card.
     */
    Mono<CardPromotionDTO> updatePromotion(Long cardId, Long promotionId, CardPromotionDTO promotionDTO);

    /**
     * Delete a promotion by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deletePromotion(Long cardId, Long promotionId);
}
