package com.firefly.core.banking.cards.core.services.activity.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.activity.v1.CardActivityDTO;
import reactor.core.publisher.Mono;

public interface CardActivityService {

    /**
     * List all activities (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardActivityDTO>> listActivities(
            Long cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new activity for a specific card.
     */
    Mono<CardActivityDTO> createActivity(Long cardId, CardActivityDTO activityDTO);

    /**
     * Retrieve a specific activity by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardActivityDTO> getActivity(Long cardId, Long activityId);

    /**
     * Update an existing activity for a specific card.
     */
    Mono<CardActivityDTO> updateActivity(Long cardId, Long activityId, CardActivityDTO activityDTO);

    /**
     * Delete an activity by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deleteActivity(Long cardId, Long activityId);
}
