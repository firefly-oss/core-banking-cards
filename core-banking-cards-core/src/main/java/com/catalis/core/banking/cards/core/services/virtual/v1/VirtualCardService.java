package com.catalis.core.banking.cards.core.services.virtual.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
import reactor.core.publisher.Mono;

public interface VirtualCardService {

    /**
     * Retrieve a paginated list of virtual cards for a specific card.
     */
    Mono<PaginationResponse<VirtualCardDTO>> listVirtualCards(Long cardId, PaginationRequest paginationRequest);

    /**
     * Create a new virtual card record for a specific card.
     */
    Mono<VirtualCardDTO> createVirtualCard(Long cardId, VirtualCardDTO virtualCardDTO);

    /**
     * Retrieve a specific virtual card by its ID, ensuring it belongs to the specified card.
     */
    Mono<VirtualCardDTO> getVirtualCard(Long cardId, Long virtualCardId);

    /**
     * Update an existing virtual card associated with the specified card.
     */
    Mono<VirtualCardDTO> updateVirtualCard(Long cardId, Long virtualCardId, VirtualCardDTO virtualCardDTO);

    /**
     * Delete a virtual card by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<Void> deleteVirtualCard(Long cardId, Long virtualCardId);
}