package com.firefly.core.banking.cards.models.repositories.virtual.v1;

import com.firefly.core.banking.cards.models.entities.virtual.v1.VirtualCard;
import com.firefly.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface VirtualCardRepository extends BaseRepository<VirtualCard, UUID> {
    Mono<VirtualCard> findByVirtualCardId(UUID virtualCardId);

    Flux<VirtualCard> findByCardId(UUID cardId, Pageable pageable);
    Mono<Long> countByCardId(UUID cardId);
}