package com.firefly.core.banking.cards.models.repositories.limit.v1;

import com.firefly.core.banking.cards.models.entities.limit.v1.CardLimit;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CardLimitRepository extends BaseRepository<CardLimit, Long> {
    Mono<CardLimit> findByCardLimitId(Long cardLimitId);

    Flux<CardLimit> findByCardId(Long cardId, Pageable pageable);
    Mono<Long> countByCardId(Long cardId);
}