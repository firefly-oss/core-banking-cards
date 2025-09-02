package com.firefly.core.banking.cards.core.services.merchant.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.merchant.v1.CardMerchantDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
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
    Mono<CardMerchantDTO> getMerchant(UUID merchantId);

    /**
     * Update an existing card merchant by ID.
     */
    Mono<CardMerchantDTO> updateMerchant(UUID merchantId, CardMerchantDTO merchantDTO);

    /**
     * Delete a card merchant by its unique ID.
     */
    Mono<Void> deleteMerchant(UUID merchantId);
}
