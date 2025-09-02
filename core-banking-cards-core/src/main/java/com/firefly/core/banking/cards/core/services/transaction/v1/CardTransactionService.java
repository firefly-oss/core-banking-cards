package com.firefly.core.banking.cards.core.services.transaction.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardTransactionService {

    /**
     * Retrieve a paginated list of transactions for a specific card.
     */
    Mono<PaginationResponse<CardTransactionDTO>> listTransactions(UUID cardId, PaginationRequest paginationRequest);

    /**
     * Create a new transaction for a specific card.
     */
    Mono<CardTransactionDTO> createTransaction(UUID cardId, CardTransactionDTO transactionDTO);

    /**
     * Retrieve a specific transaction by its unique ID, ensuring it belongs to the given card.
     */
    Mono<CardTransactionDTO> getTransaction(UUID cardId, UUID transactionId);

    /**
     * Filters card transactions based on the given filter request and returns a paginated response.
     */
    Mono<PaginationResponse<CardTransactionDTO>> findFiltered(FilterRequest<CardTransactionDTO> request);

    /**
     * Update an existing transaction record for a specific card.
     */
    Mono<CardTransactionDTO> updateTransaction(UUID cardId, UUID transactionId, CardTransactionDTO transactionDTO);

    /**
     * Delete a transaction by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<Void> deleteTransaction(UUID cardId, UUID transactionId);
}