package com.firefly.core.banking.cards.core.services.payment.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.payment.v1.CardPaymentDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardPaymentService {

    /**
     * List all payments (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardPaymentDTO>> listPayments(
            UUID cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new payment for a specific card.
     */
    Mono<CardPaymentDTO> createPayment(UUID cardId, CardPaymentDTO paymentDTO);

    /**
     * Retrieve a specific payment by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardPaymentDTO> getPayment(UUID cardId, UUID paymentId);

    /**
     * Update an existing payment for a specific card.
     */
    Mono<CardPaymentDTO> updatePayment(UUID cardId, UUID paymentId, CardPaymentDTO paymentDTO);

    /**
     * Delete a payment by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deletePayment(UUID cardId, UUID paymentId);
}
