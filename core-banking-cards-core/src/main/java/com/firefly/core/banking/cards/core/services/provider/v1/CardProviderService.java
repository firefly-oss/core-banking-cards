package com.firefly.core.banking.cards.core.services.provider.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
import reactor.core.publisher.Mono;

public interface CardProviderService {

    /**
     * Retrieve a paginated list of card providers for a specified card.
     */
    Mono<PaginationResponse<CardProviderDTO>> listProviders(Long cardId, PaginationRequest paginationRequest);

    /**
     * Create a new card provider record for a specified card.
     */
    Mono<CardProviderDTO> createProvider(Long cardId, CardProviderDTO providerDTO);

    /**
     * Retrieve a specific card provider by its unique ID,
     * ensuring it belongs to the specified card.
     */
    Mono<CardProviderDTO> getProvider(Long cardId, Long providerId);

    /**
     * Update an existing card provider by ID, ensuring it belongs to the specified card.
     */
    Mono<CardProviderDTO> updateProvider(Long cardId, Long providerId, CardProviderDTO providerDTO);

    /**
     * Delete a card provider record by its unique ID,
     * ensuring it belongs to the specified card.
     */
    Mono<Void> deleteProvider(Long cardId, Long providerId);
}
