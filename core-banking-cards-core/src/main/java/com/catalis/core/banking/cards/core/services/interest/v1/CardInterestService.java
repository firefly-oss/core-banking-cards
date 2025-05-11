package com.catalis.core.banking.cards.core.services.interest.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.interest.v1.CardInterestDTO;
import reactor.core.publisher.Mono;

public interface CardInterestService {

    /**
     * List all interest records (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardInterestDTO>> listInterests(
            Long cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new interest record for a specific card.
     */
    Mono<CardInterestDTO> createInterest(Long cardId, CardInterestDTO interestDTO);

    /**
     * Retrieve a specific interest record by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardInterestDTO> getInterest(Long cardId, Long interestId);

    /**
     * Update an existing interest record for a specific card.
     */
    Mono<CardInterestDTO> updateInterest(Long cardId, Long interestId, CardInterestDTO interestDTO);

    /**
     * Delete an interest record by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deleteInterest(Long cardId, Long interestId);
}
