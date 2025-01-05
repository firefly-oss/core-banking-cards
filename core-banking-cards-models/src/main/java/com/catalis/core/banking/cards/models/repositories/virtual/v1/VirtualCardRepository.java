package com.catalis.core.banking.cards.models.repositories.virtual.v1;

import com.catalis.core.banking.cards.models.entities.virtual.v1.VirtualCard;
import com.catalis.core.banking.cards.models.repositories.BaseRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface VirtualCardRepository extends BaseRepository<VirtualCard, Long> {
    Mono<VirtualCard> findByVirtualCardId(Long virtualCardId);

    Flux<VirtualCard> findByCardId(Long cardId, Pageable pageable);
    Mono<Long> countByCardId(Long cardId);

    Flux<VirtualCard> findByDeviceId(String deviceId, Pageable pageable);
    Mono<Long> countByDeviceId(String deviceId);
}