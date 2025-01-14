package com.catalis.core.banking.cards.core.services.card.v1;

import com.catalis.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import reactor.core.publisher.Mono;

public interface CardService {

    /**
     * Create a new card record.
     */
    Mono<CardDTO> createCard(CardDTO cardDTO);

    /**
     * Retrieve a specific card by ID.
     */
    Mono<CardDTO> getCard(Long cardId);

    /**
     * Update an existing card record by ID.
     */
    Mono<CardDTO> updateCard(Long cardId, CardDTO cardDTO);

    /**
     * Delete a card by its unique ID.
     */
    Mono<Void> deleteCard(Long cardId);
}