package com.firefly.core.banking.cards.core.services.security.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardSecurityService {

    /**
     * List all security settings (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardSecurityDTO>> listSecuritySettings(
            UUID cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new security setting for a specific card.
     */
    Mono<CardSecurityDTO> createSecuritySetting(UUID cardId, CardSecurityDTO securityDTO);

    /**
     * Retrieve a specific security setting by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardSecurityDTO> getSecuritySetting(UUID cardId, UUID securityId);

    /**
     * Update an existing security setting for a specific card.
     */
    Mono<CardSecurityDTO> updateSecuritySetting(UUID cardId, UUID securityId, CardSecurityDTO securityDTO);

    /**
     * Delete a security setting by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<Void> deleteSecuritySetting(UUID cardId, UUID securityId);
}
