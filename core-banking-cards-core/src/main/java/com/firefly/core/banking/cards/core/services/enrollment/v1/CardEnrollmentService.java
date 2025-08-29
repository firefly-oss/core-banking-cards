package com.firefly.core.banking.cards.core.services.enrollment.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.enrollment.v1.CardEnrollmentDTO;
import reactor.core.publisher.Mono;

public interface CardEnrollmentService {

    /**
     * List all enrollments (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardEnrollmentDTO>> listEnrollments(
            Long cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new enrollment for a specific card.
     */
    Mono<CardEnrollmentDTO> createEnrollment(Long cardId, CardEnrollmentDTO enrollmentDTO);

    /**
     * Retrieve a specific enrollment by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardEnrollmentDTO> getEnrollment(Long cardId, Long enrollmentId);

    /**
     * Update an existing enrollment for a specific card.
     */
    Mono<CardEnrollmentDTO> updateEnrollment(Long cardId, Long enrollmentId, CardEnrollmentDTO enrollmentDTO);

    /**
     * Delete an enrollment by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deleteEnrollment(Long cardId, Long enrollmentId);
}
