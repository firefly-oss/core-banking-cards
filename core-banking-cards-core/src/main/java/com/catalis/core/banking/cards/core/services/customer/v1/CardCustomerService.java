package com.catalis.core.banking.cards.core.services.customer.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.interfaces.dtos.customer.v1.CardCustomerDTO;
import reactor.core.publisher.Mono;

public interface CardCustomerService {

    /**
     * List all customers (paginated).
     */
    Mono<PaginationResponse<CardCustomerDTO>> listCustomers(PaginationRequest paginationRequest);

    /**
     * Create a new customer.
     */
    Mono<CardCustomerDTO> createCustomer(CardCustomerDTO customerDTO);

    /**
     * Retrieve a specific customer by ID.
     */
    Mono<CardCustomerDTO> getCustomer(Long customerId);

    /**
     * Update an existing customer by ID.
     */
    Mono<CardCustomerDTO> updateCustomer(Long customerId, CardCustomerDTO customerDTO);

    /**
     * Delete a customer by its unique ID.
     */
    Mono<Void> deleteCustomer(Long customerId);
}
