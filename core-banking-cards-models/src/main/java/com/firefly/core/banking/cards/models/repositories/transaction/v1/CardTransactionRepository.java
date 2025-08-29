package com.firefly.core.banking.cards.models.repositories.transaction.v1;

import com.firefly.core.banking.cards.models.entities.transaction.v1.CardTransaction;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CardTransactionRepository extends BaseRepository<CardTransaction, Long> {
    Mono<CardTransaction> findByCardTransactionId(Long cardTransactionId);
    Flux<CardTransaction> findByCardId(Long cardId, Pageable pageable);
    Mono<Long> countByCardId(Long cardId);
}