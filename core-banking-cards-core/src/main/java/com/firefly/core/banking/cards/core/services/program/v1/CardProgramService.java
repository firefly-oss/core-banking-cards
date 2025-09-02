package com.firefly.core.banking.cards.core.services.program.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.program.v1.CardProgramDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardProgramService {

    /**
     * List all card programs (paginated).
     */
    Mono<PaginationResponse<CardProgramDTO>> listPrograms(PaginationRequest paginationRequest);

    /**
     * Create a new card program.
     */
    Mono<CardProgramDTO> createProgram(CardProgramDTO programDTO);

    /**
     * Retrieve a specific card program by ID.
     */
    Mono<CardProgramDTO> getProgram(UUID programId);

    /**
     * Update an existing card program by ID.
     */
    Mono<CardProgramDTO> updateProgram(UUID programId, CardProgramDTO programDTO);

    /**
     * Delete a card program by its unique ID.
     */
    Mono<Void> deleteProgram(UUID programId);
}
