/*
 * Copyright 2025 Firefly Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.fireflysoftware.core.banking.cards.core.services.card.v1;

import org.fireflyframework.core.filters.FilterRequest;
import org.fireflyframework.core.filters.FilterUtils;
import org.fireflyframework.core.queries.PaginationResponse;
import io.fireflysoftware.core.banking.cards.core.mappers.card.v1.CardMapper;
import io.fireflysoftware.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import io.fireflysoftware.core.banking.cards.interfaces.enums.card.v1.CardStatusEnum;
import io.fireflysoftware.core.banking.cards.models.entities.card.v1.Card;
import io.fireflysoftware.core.banking.cards.models.repositories.card.v1.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository repository;

    @Autowired
    private CardMapper mapper;

    @Override
    public Mono<PaginationResponse<CardDTO>> filterCards(FilterRequest<CardDTO> filterRequest) {
        return FilterUtils
                .createFilter(
                        Card.class,
                        mapper::toDTO
                )
                .filter(filterRequest);
    }

    @Override
    public Mono<CardDTO> createCard(CardDTO cardDTO) {
        Card card = mapper.toEntity(cardDTO);
        if (card.getCardStatus() == null) {
            card.setCardStatus(CardStatusEnum.DRAFT);
        }
        return repository.save(card)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardDTO> getCard(UUID cardId) {
        return repository.findByCardId(cardId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardDTO> updateCard(UUID cardId, CardDTO cardDTO) {
        return repository.findByCardId(cardId)
                .flatMap(existingCard -> {
                    CardStatusEnum current = existingCard.getCardStatus() != null
                            ? existingCard.getCardStatus()
                            : CardStatusEnum.DRAFT;
                    Card updatedCard = mapper.toEntity(cardDTO);
                    updatedCard.setCardId(existingCard.getCardId());
                    if (updatedCard.getCardStatus() == null) {
                        updatedCard.setCardStatus(current);
                    }
                    if (!CardLifecycle.canTransition(current, updatedCard.getCardStatus())) {
                        return Mono.error(new IllegalStateException(
                                "Card " + cardId + " cannot move from " + current + " to " + updatedCard.getCardStatus()));
                    }
                    return repository.save(updatedCard);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteCard(UUID cardId) {
        return repository.findByCardId(cardId)
                .flatMap(existingCard -> {
                    CardStatusEnum status = existingCard.getCardStatus() != null
                            ? existingCard.getCardStatus()
                            : CardStatusEnum.DRAFT;
                    if (status != CardStatusEnum.DRAFT) {
                        return Mono.error(new IllegalStateException(
                                "Card " + cardId + " is " + status
                                        + " and cannot be deleted; cancel it so the card history is kept"));
                    }
                    return repository.delete(existingCard);
                });
    }
}