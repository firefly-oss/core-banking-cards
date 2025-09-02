package com.firefly.core.banking.cards.core.services.gateway.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.gateway.v1.CardGatewayDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardGatewayService {

    /**
     * List all card gateways (paginated).
     */
    Mono<PaginationResponse<CardGatewayDTO>> listGateways(PaginationRequest paginationRequest);

    /**
     * Create a new card gateway.
     */
    Mono<CardGatewayDTO> createGateway(CardGatewayDTO gatewayDTO);

    /**
     * Retrieve a specific card gateway by ID.
     */
    Mono<CardGatewayDTO> getGateway(UUID gatewayId);

    /**
     * Update an existing card gateway by ID.
     */
    Mono<CardGatewayDTO> updateGateway(UUID gatewayId, CardGatewayDTO gatewayDTO);

    /**
     * Delete a card gateway by its unique ID.
     */
    Mono<Void> deleteGateway(UUID gatewayId);
}
