package com.firefly.core.banking.cards.core.services.bin.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.bin.v1.BINDTO;
import reactor.core.publisher.Mono;

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
    Mono<BINDTO> getBIN(Long binId);

    /**
     * Retrieve a specific BIN by number.
     */
    Mono<BINDTO> getBINByNumber(String binNumber);

    /**
     * Update an existing BIN by ID.
     */
    Mono<BINDTO> updateBIN(Long binId, BINDTO binDTO);

    /**
     * Delete a BIN by its unique ID.
     */
    Mono<Void> deleteBIN(Long binId);
}
