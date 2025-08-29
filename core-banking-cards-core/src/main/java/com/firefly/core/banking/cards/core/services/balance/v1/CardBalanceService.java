package com.firefly.core.banking.cards.core.services.balance.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.balance.v1.CardBalanceDTO;
import reactor.core.publisher.Mono;

public interface CardBalanceService {

    /**
     * List all balances (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardBalanceDTO>> listBalances(
            Long cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new balance for a specific card.
     */
    Mono<CardBalanceDTO> createBalance(Long cardId, CardBalanceDTO balanceDTO);

    /**
     * Retrieve a specific balance by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardBalanceDTO> getBalance(Long cardId, Long balanceId);

    /**
     * Update an existing balance for a specific card.
     */
    Mono<CardBalanceDTO> updateBalance(Long cardId, Long balanceId, CardBalanceDTO balanceDTO);

    /**
     * Delete a balance by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deleteBalance(Long cardId, Long balanceId);
}
