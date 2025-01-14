package com.catalis.core.banking.cards.core.services.configuration.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
import reactor.core.publisher.Mono;

public interface CardConfigurationService {

    /**
     * Retrieve a paginated list of card configurations for a specified card.
     */
    Mono<PaginationResponse<CardConfigurationDTO>> listConfigurations(Long cardId, PaginationRequest paginationRequest);

    /**
     * Create a new configuration for a specified card.
     */
    Mono<CardConfigurationDTO> createConfiguration(Long cardId, CardConfigurationDTO configDTO);

    /**
     * Retrieve a specific card configuration by its unique ID.
     */
    Mono<CardConfigurationDTO> getConfiguration(Long cardId, Long configId);

    /**
     * Update an existing card configuration by configId, ensuring it belongs to the specified card.
     */
    Mono<CardConfigurationDTO> updateConfiguration(Long cardId, Long configId, CardConfigurationDTO configDTO);

    /**
     * Delete a card configuration by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<Void> deleteConfiguration(Long cardId, Long configId);
}
