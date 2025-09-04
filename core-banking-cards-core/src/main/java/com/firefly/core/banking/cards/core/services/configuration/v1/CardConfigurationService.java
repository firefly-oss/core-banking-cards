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


package com.firefly.core.banking.cards.core.services.configuration.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardConfigurationService {

    /**
     * Retrieve a paginated list of card configurations for a specified card.
     */
    Mono<PaginationResponse<CardConfigurationDTO>> listConfigurations(UUID cardId, PaginationRequest paginationRequest);

    /**
     * Create a new configuration for a specified card.
     */
    Mono<CardConfigurationDTO> createConfiguration(UUID cardId, CardConfigurationDTO configDTO);

    /**
     * Retrieve a specific card configuration by its unique ID.
     */
    Mono<CardConfigurationDTO> getConfiguration(UUID cardId, UUID configId);

    /**
     * Update an existing card configuration by configId, ensuring it belongs to the specified card.
     */
    Mono<CardConfigurationDTO> updateConfiguration(UUID cardId, UUID configId, CardConfigurationDTO configDTO);

    /**
     * Delete a card configuration by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<Void> deleteConfiguration(UUID cardId, UUID configId);
}
