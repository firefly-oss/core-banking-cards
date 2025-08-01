package com.catalis.core.banking.cards.core.services.merchant.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.merchant.v1.CardMerchantDTO;
import reactor.core.publisher.Mono;

public interface CardMerchantService {

    /**
     * List all card merchants (paginated).
     */
    Mono<PaginationResponse<CardMerchantDTO>> listMerchants(PaginationRequest paginationRequest);

    /**
     * Create a new card merchant.
     */
    Mono<CardMerchantDTO> createMerchant(CardMerchantDTO merchantDTO);

    /**
     * Retrieve a specific card merchant by ID.
     */
    Mono<CardMerchantDTO> getMerchant(Long merchantId);

    /**
     * Update an existing card merchant by ID.
     */
    Mono<CardMerchantDTO> updateMerchant(Long merchantId, CardMerchantDTO merchantDTO);

    /**
     * Delete a card merchant by its unique ID.
     */
    Mono<Void> deleteMerchant(Long merchantId);
}
