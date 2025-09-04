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


package com.firefly.core.banking.cards.core.services.bin.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.bin.v1.BINDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface BINService {

    /**
     * List all BINs (paginated).
     */
    Mono<PaginationResponse<BINDTO>> listBINs(PaginationRequest paginationRequest);

    /**
     * Create a new BIN.
     */
    Mono<BINDTO> createBIN(BINDTO binDTO);

    /**
     * Retrieve a specific BIN by ID.
     */
    Mono<BINDTO> getBIN(UUID binId);

    /**
     * Retrieve a specific BIN by number.
     */
    Mono<BINDTO> getBINByNumber(String binNumber);

    /**
     * Update an existing BIN by ID.
     */
    Mono<BINDTO> updateBIN(UUID binId, BINDTO binDTO);

    /**
     * Delete a BIN by its unique ID.
     */
    Mono<Void> deleteBIN(UUID binId);
}
