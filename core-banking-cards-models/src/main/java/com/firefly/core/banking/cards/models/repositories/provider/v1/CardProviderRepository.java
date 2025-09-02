package com.firefly.core.banking.cards.models.repositories.provider.v1;

import com.firefly.core.banking.cards.models.entities.provider.v1.CardProvider;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CardProviderRepository extends BaseRepository<CardProvider, UUID> {
    Flux<CardProvider> findByCardId(UUID cardId, Pageable pageable);
    Mono<Long> countByCardId(UUID cardId);
}
