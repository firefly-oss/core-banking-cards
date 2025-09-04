/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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
