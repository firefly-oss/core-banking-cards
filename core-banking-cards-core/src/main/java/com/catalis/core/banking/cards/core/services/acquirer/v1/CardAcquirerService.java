package com.catalis.core.banking.cards.core.services.acquirer.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.acquirer.v1.CardAcquirerDTO;
import reactor.core.publisher.Mono;

public interface CardAcquirerService {

    /**
     * List all card acquirers (paginated).
     */
    Mono<PaginationResponse<CardAcquirerDTO>> listAcquirers(PaginationRequest paginationRequest);

    /**
     * Create a new card acquirer.
     */
    Mono<CardAcquirerDTO> createAcquirer(CardAcquirerDTO acquirerDTO);

    /**
     * Retrieve a specific card acquirer by ID.
     */
    Mono<CardAcquirerDTO> getAcquirer(Long acquirerId);

    /**
     * Update an existing card acquirer by ID.
     */
    Mono<CardAcquirerDTO> updateAcquirer(Long acquirerId, CardAcquirerDTO acquirerDTO);

    /**
     * Delete a card acquirer by its unique ID.
     */
    Mono<Void> deleteAcquirer(Long acquirerId);
}
