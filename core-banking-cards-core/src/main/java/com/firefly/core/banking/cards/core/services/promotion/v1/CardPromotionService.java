package com.firefly.core.banking.cards.core.services.promotion.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.promotion.v1.CardPromotionDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardPromotionService {

    /**
     * List all promotions (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardPromotionDTO>> listPromotions(
            UUID cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new promotion for a specific card.
     */
    Mono<CardPromotionDTO> createPromotion(UUID cardId, CardPromotionDTO promotionDTO);

    /**
     * Retrieve a specific promotion by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardPromotionDTO> getPromotion(UUID cardId, UUID promotionId);

    /**
     * Update an existing promotion for a specific card.
     */
    Mono<CardPromotionDTO> updatePromotion(UUID cardId, UUID promotionId, CardPromotionDTO promotionDTO);

    /**
     * Delete a promotion by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deletePromotion(UUID cardId, UUID promotionId);
}
