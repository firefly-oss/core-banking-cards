package com.firefly.core.banking.cards.models.repositories.security.v1;

import com.firefly.core.banking.cards.models.entities.security.v1.CardSecurity;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CardSecurityRepository extends BaseRepository<CardSecurity, Long> {
    Mono<CardSecurity> findByCardSecurityId(Long cardSecurityId);

    Flux<CardSecurity> findByCardId(Long cardId, Pageable pageable);
    Mono<Long> countByCardId(Long cardId);
}