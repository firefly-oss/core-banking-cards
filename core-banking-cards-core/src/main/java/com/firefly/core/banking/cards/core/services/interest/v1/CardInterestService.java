package com.firefly.core.banking.cards.core.services.interest.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.interest.v1.CardInterestDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardInterestService {

    /**
     * List all interest records (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardInterestDTO>> listInterests(
            UUID cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new interest record for a specific card.
     */
    Mono<CardInterestDTO> createInterest(UUID cardId, CardInterestDTO interestDTO);

    /**
     * Retrieve a specific interest record by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardInterestDTO> getInterest(UUID cardId, UUID interestId);

    /**
     * Update an existing interest record for a specific card.
     */
    Mono<CardInterestDTO> updateInterest(UUID cardId, UUID interestId, CardInterestDTO interestDTO);

    /**
     * Delete an interest record by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deleteInterest(UUID cardId, UUID interestId);
}
