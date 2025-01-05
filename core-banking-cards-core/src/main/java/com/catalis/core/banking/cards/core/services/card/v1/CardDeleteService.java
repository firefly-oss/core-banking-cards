package com.catalis.core.banking.cards.core.services.card.v1;

import com.catalis.core.banking.cards.models.repositories.card.v1.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardDeleteService {

    @Autowired
    private CardRepository cardRepository;

    public Mono<Void> deleteCard(Long cardId) {
        return cardRepository.deleteById(cardId);
    }
}