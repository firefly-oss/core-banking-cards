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


package com.firefly.core.banking.cards.core.services.network.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.network.v1.CardNetworkDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardNetworkService {

    /**
     * List all card networks (paginated).
     */
    Mono<PaginationResponse<CardNetworkDTO>> listNetworks(PaginationRequest paginationRequest);

    /**
     * Create a new card network.
     */
    Mono<CardNetworkDTO> createNetwork(CardNetworkDTO networkDTO);

    /**
     * Retrieve a specific card network by ID.
     */
    Mono<CardNetworkDTO> getNetwork(UUID networkId);

    /**
     * Update an existing card network by ID.
     */
    Mono<CardNetworkDTO> updateNetwork(UUID networkId, CardNetworkDTO networkDTO);

    /**
     * Delete a card network by its unique ID.
     */
    Mono<Void> deleteNetwork(UUID networkId);
}
