package com.firefly.core.banking.cards.core.services.payment.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.payment.v1.CardPaymentDTO;
import reactor.core.publisher.Mono;

public interface CardPaymentService {

    /**
     * List all payments (paginated) for a specific card.
     */
    Mono<PaginationResponse<CardPaymentDTO>> listPayments(
            Long cardId,
            PaginationRequest paginationRequest
    );

    /**
     * Create a new payment for a specific card.
     */
    Mono<CardPaymentDTO> createPayment(Long cardId, CardPaymentDTO paymentDTO);

    /**
     * Retrieve a specific payment by its unique ID, ensuring it belongs to the card.
     */
    Mono<CardPaymentDTO> getPayment(Long cardId, Long paymentId);

    /**
     * Update an existing payment for a specific card.
     */
    Mono<CardPaymentDTO> updatePayment(Long cardId, Long paymentId, CardPaymentDTO paymentDTO);

    /**
     * Delete a payment by its unique ID, ensuring it belongs to the card.
     */
    Mono<Void> deletePayment(Long cardId, Long paymentId);
}
