package com.firefly.core.banking.cards.models.repositories.transaction.v1;

import com.firefly.core.banking.cards.models.entities.transaction.v1.CardTransaction;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CardTransactionRepository extends BaseRepository<CardTransaction, UUID> {
    Mono<CardTransaction> findByCardTransactionId(UUID cardTransactionId);
    Flux<CardTransaction> findByCardId(UUID cardId, Pageable pageable);
    Mono<Long> countByCardId(UUID cardId);
}