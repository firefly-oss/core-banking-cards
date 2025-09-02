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
