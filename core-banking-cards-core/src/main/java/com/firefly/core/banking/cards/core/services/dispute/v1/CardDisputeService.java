package com.firefly.core.banking.cards.core.services.dispute.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.dispute.v1.CardDisputeDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardDisputeService {

    /**
     * List all disputes (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardDisputeDTO>> listDisputes(
            UUID cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new dispute for a specific card.
     */
    Mono<CardDisputeDTO> createDispute(UUID cardId, CardDisputeDTO disputeDTO);

    /**
     * Retrieve a specific dispute by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardDisputeDTO> getDispute(UUID cardId, UUID disputeId);

    /**
     * Update an existing dispute for a specific card.
     */
    Mono<CardDisputeDTO> updateDispute(UUID cardId, UUID disputeId, CardDisputeDTO disputeDTO);

    /**
     * Delete a dispute by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deleteDispute(UUID cardId, UUID disputeId);
}
