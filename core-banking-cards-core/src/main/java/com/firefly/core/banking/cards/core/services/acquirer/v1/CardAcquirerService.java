package com.firefly.core.banking.cards.core.services.acquirer.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.acquirer.v1.CardAcquirerDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
    Mono<CardAcquirerDTO> getAcquirer(UUID acquirerId);

    /**
     * Update an existing card acquirer by ID.
     */
    Mono<CardAcquirerDTO> updateAcquirer(UUID acquirerId, CardAcquirerDTO acquirerDTO);

    /**
     * Delete a card acquirer by its unique ID.
     */
    Mono<Void> deleteAcquirer(UUID acquirerId);
}
