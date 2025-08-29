package com.firefly.core.banking.cards.core.services.security.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
import reactor.core.publisher.Mono;

public interface CardSecurityService {

    /**
     * List all security settings (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardSecurityDTO>> listSecuritySettings(
            Long cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new security setting for a specific card.
     */
    Mono<CardSecurityDTO> createSecuritySetting(Long cardId, CardSecurityDTO securityDTO);

    /**
     * Retrieve a specific security setting by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardSecurityDTO> getSecuritySetting(Long cardId, Long securityId);

    /**
     * Update an existing security setting for a specific card.
     */
    Mono<CardSecurityDTO> updateSecuritySetting(Long cardId, Long securityId, CardSecurityDTO securityDTO);

    /**
     * Delete a security setting by its unique ID, ensuring it belongs to the specified card.
     */
    Mono<Void> deleteSecuritySetting(Long cardId, Long securityId);
}
