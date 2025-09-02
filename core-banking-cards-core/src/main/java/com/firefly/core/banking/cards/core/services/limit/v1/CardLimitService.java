package com.firefly.core.banking.cards.core.services.limit.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.limit.v1.CardLimitDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardLimitService {

    /**
     * Retrieve a paginated list of limits for a specified card.
     */
    Mono<PaginationResponse<CardLimitDTO>> listLimits(UUID cardId, PaginationRequest paginationRequest);

    /**
     * Create a new limit record for a specified card.
     */
    Mono<CardLimitDTO> createLimit(UUID cardId, CardLimitDTO limitDTO);

    /**
     * Retrieve a specific limit by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<CardLimitDTO> getLimit(UUID cardId, UUID limitId);

    /**
     * Update an existing limit record for a specific card.
     */
    Mono<CardLimitDTO> updateLimit(UUID cardId, UUID limitId, CardLimitDTO limitDTO);

    /**
     * Delete a limit by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<Void> deleteLimit(UUID cardId, UUID limitId);
}