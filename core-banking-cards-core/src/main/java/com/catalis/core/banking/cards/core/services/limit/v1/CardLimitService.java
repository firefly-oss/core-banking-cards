package com.catalis.core.banking.cards.core.services.limit.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.limit.v1.CardLimitDTO;
import reactor.core.publisher.Mono;

public interface CardLimitService {

    /**
     * Retrieve a paginated list of limits for a specified card.
     */
    Mono<PaginationResponse<CardLimitDTO>> listLimits(Long cardId, PaginationRequest paginationRequest);

    /**
     * Create a new limit record for a specified card.
     */
    Mono<CardLimitDTO> createLimit(Long cardId, CardLimitDTO limitDTO);

    /**
     * Retrieve a specific limit by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<CardLimitDTO> getLimit(Long cardId, Long limitId);

    /**
     * Update an existing limit record for a specific card.
     */
    Mono<CardLimitDTO> updateLimit(Long cardId, Long limitId, CardLimitDTO limitDTO);

    /**
     * Delete a limit by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<Void> deleteLimit(Long cardId, Long limitId);
}