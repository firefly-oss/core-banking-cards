package com.firefly.core.banking.cards.models.repositories.limit.v1;

import com.firefly.core.banking.cards.models.entities.limit.v1.CardLimit;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CardLimitRepository extends BaseRepository<CardLimit, UUID> {
    Mono<CardLimit> findByCardLimitId(UUID cardLimitId);

    Flux<CardLimit> findByCardId(UUID cardId, Pageable pageable);
    Mono<Long> countByCardId(UUID cardId);
}