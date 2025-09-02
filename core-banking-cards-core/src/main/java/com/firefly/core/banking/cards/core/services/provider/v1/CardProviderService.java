package com.firefly.core.banking.cards.core.services.provider.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardProviderService {

    /**
     * Retrieve a paginated list of card providers for a specified card.
     */
    Mono<PaginationResponse<CardProviderDTO>> listProviders(UUID cardId, PaginationRequest paginationRequest);

    /**
     * Create a new card provider record for a specified card.
     */
    Mono<CardProviderDTO> createProvider(UUID cardId, CardProviderDTO providerDTO);

    /**
     * Retrieve a specific card provider by its unique ID,
     * ensuring it belongs to the specified card.
     */
    Mono<CardProviderDTO> getProvider(UUID cardId, UUID providerId);

    /**
     * Update an existing card provider by ID, ensuring it belongs to the specified card.
     */
    Mono<CardProviderDTO> updateProvider(UUID cardId, UUID providerId, CardProviderDTO providerDTO);

    /**
     * Delete a card provider record by its unique ID,
     * ensuring it belongs to the specified card.
     */
    Mono<Void> deleteProvider(UUID cardId, UUID providerId);
}
