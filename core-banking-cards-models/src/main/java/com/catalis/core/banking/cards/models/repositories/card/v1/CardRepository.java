package com.catalis.core.banking.cards.models.repositories.card.v1;

import com.catalis.core.banking.cards.interfaces.enums.card.v1.CardStatusEnum;
import com.catalis.core.banking.cards.models.entities.card.v1.Card;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CardRepository extends BaseRepository<Card, Long> {
    Mono<Card> findByCardId(Long cardId);
}