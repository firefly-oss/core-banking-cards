package com.firefly.core.banking.cards.core.services.issuer.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.issuer.v1.IssuerDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface IssuerService {

    /**
     * List all issuers (paginated).
     */
    Mono<PaginationResponse<IssuerDTO>> listIssuers(PaginationRequest paginationRequest);

    /**
     * Create a new issuer.
     */
    Mono<IssuerDTO> createIssuer(IssuerDTO issuerDTO);

    /**
     * Retrieve a specific issuer by ID.
     */
    Mono<IssuerDTO> getIssuer(UUID issuerId);

    /**
     * Update an existing issuer by ID.
     */
    Mono<IssuerDTO> updateIssuer(UUID issuerId, IssuerDTO issuerDTO);

    /**
     * Delete an issuer by its unique ID.
     */
    Mono<Void> deleteIssuer(UUID issuerId);
}
