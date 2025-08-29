package com.firefly.core.banking.cards.core.services.physical.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.physical.v1.PhysicalCardDTO;
import reactor.core.publisher.Mono;

public interface PhysicalCardService {

    /**
     * Retrieve a paginated list of physical cards for a specified card.
     */
    Mono<PaginationResponse<PhysicalCardDTO>> listPhysicalCards(
            Long cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new physical card record for a specified card.
     */
    Mono<PhysicalCardDTO> createPhysicalCard(Long cardId, PhysicalCardDTO physicalCardDTO);

    /**
     * Retrieve a specific physical card by its ID, ensuring it belongs to the specified card.
     */
    Mono<PhysicalCardDTO> getPhysicalCard(Long cardId, Long physicalCardId);

    /**
     * Update an existing physical card for the specified card.
     */
    Mono<PhysicalCardDTO> updatePhysicalCard(Long cardId, Long physicalCardId, PhysicalCardDTO physicalCardDTO);

    /**
     * Delete a physical card by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<Void> deletePhysicalCard(Long cardId, Long physicalCardId);
}
