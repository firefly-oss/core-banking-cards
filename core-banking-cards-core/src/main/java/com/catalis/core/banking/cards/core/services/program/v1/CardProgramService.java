package com.catalis.core.banking.cards.core.services.program.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.program.v1.CardProgramDTO;
import reactor.core.publisher.Mono;

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
    Mono<CardProgramDTO> getProgram(Long programId);

    /**
     * Update an existing card program by ID.
     */
    Mono<CardProgramDTO> updateProgram(Long programId, CardProgramDTO programDTO);

    /**
     * Delete a card program by its unique ID.
     */
    Mono<Void> deleteProgram(Long programId);
}
