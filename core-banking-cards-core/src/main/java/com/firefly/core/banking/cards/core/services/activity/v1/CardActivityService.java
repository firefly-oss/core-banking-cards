package com.firefly.core.banking.cards.core.services.activity.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.activity.v1.CardActivityDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardActivityService {

    /**
     * List all activities (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardActivityDTO>> listActivities(
            UUID cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new activity for a specific card.
     */
    Mono<CardActivityDTO> createActivity(UUID cardId, CardActivityDTO activityDTO);

    /**
     * Retrieve a specific activity by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardActivityDTO> getActivity(UUID cardId, UUID activityId);

    /**
     * Update an existing activity for a specific card.
     */
    Mono<CardActivityDTO> updateActivity(UUID cardId, UUID activityId, CardActivityDTO activityDTO);

    /**
     * Delete an activity by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deleteActivity(UUID cardId, UUID activityId);
}
