package com.catalis.core.banking.cards.core.services.issuer.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.issuer.v1.IssuerDTO;
import reactor.core.publisher.Mono;

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
    Mono<IssuerDTO> getIssuer(Long issuerId);

    /**
     * Update an existing issuer by ID.
     */
    Mono<IssuerDTO> updateIssuer(Long issuerId, IssuerDTO issuerDTO);

    /**
     * Delete an issuer by its unique ID.
     */
    Mono<Void> deleteIssuer(Long issuerId);
}
