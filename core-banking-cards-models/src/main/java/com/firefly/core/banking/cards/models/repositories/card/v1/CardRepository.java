package com.firefly.core.banking.cards.models.repositories.card.v1;

import com.firefly.core.banking.cards.models.entities.card.v1.Card;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CardRepository extends BaseRepository<Card, UUID> {
    Mono<Card> findByCardId(UUID cardId);
}