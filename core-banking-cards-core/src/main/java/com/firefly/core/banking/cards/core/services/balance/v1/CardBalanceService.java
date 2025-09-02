package com.firefly.core.banking.cards.core.services.balance.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.balance.v1.CardBalanceDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardBalanceService {

    /**
     * List all balances (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardBalanceDTO>> listBalances(
            UUID cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new balance for a specific card.
     */
    Mono<CardBalanceDTO> createBalance(UUID cardId, CardBalanceDTO balanceDTO);

    /**
     * Retrieve a specific balance by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardBalanceDTO> getBalance(UUID cardId, UUID balanceId);

    /**
     * Update an existing balance for a specific card.
     */
    Mono<CardBalanceDTO> updateBalance(UUID cardId, UUID balanceId, CardBalanceDTO balanceDTO);

    /**
     * Delete a balance by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deleteBalance(UUID cardId, UUID balanceId);
}
