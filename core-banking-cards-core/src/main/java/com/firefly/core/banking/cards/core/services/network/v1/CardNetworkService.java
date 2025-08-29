package com.firefly.core.banking.cards.core.services.network.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.network.v1.CardNetworkDTO;
import reactor.core.publisher.Mono;

public interface CardNetworkService {

    /**
     * List all card networks (paginated).
     */
    Mono<PaginationResponse<CardNetworkDTO>> listNetworks(PaginationRequest paginationRequest);

    /**
     * Create a new card network.
     */
    Mono<CardNetworkDTO> createNetwork(CardNetworkDTO networkDTO);

    /**
     * Retrieve a specific card network by ID.
     */
    Mono<CardNetworkDTO> getNetwork(Long networkId);

    /**
     * Update an existing card network by ID.
     */
    Mono<CardNetworkDTO> updateNetwork(Long networkId, CardNetworkDTO networkDTO);

    /**
     * Delete a card network by its unique ID.
     */
    Mono<Void> deleteNetwork(Long networkId);
}
