package com.catalis.core.banking.cards.core.services.terminal.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.terminal.v1.CardTerminalDTO;
import reactor.core.publisher.Mono;

public interface CardTerminalService {

    /**
     * List all card terminals (paginated).
     */
    Mono<PaginationResponse<CardTerminalDTO>> listTerminals(PaginationRequest paginationRequest);

    /**
     * Create a new card terminal.
     */
    Mono<CardTerminalDTO> createTerminal(CardTerminalDTO terminalDTO);

    /**
     * Retrieve a specific card terminal by ID.
     */
    Mono<CardTerminalDTO> getTerminal(Long terminalId);

    /**
     * Update an existing card terminal by ID.
     */
    Mono<CardTerminalDTO> updateTerminal(Long terminalId, CardTerminalDTO terminalDTO);

    /**
     * Delete a card terminal by its unique ID.
     */
    Mono<Void> deleteTerminal(Long terminalId);
}
