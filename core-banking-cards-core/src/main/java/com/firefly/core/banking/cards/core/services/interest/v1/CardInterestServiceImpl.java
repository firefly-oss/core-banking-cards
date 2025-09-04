/*
 * Copyright 2025 Firefly Software Solutions Inc
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


package com.firefly.core.banking.cards.core.services.interest.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.interest.v1.CardInterestMapper;
import com.firefly.core.banking.cards.interfaces.dtos.interest.v1.CardInterestDTO;
import com.firefly.core.banking.cards.models.entities.interest.v1.CardInterest;
import com.firefly.core.banking.cards.models.repositories.interest.v1.CardInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardInterestServiceImpl implements CardInterestService {

    @Autowired
    private CardInterestRepository repository;

    @Autowired
    private CardInterestMapper mapper;

    @Override
    public Mono<PaginationResponse<CardInterestDTO>> listInterests(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardInterestDTO> createInterest(UUID cardId, CardInterestDTO interestDTO) {
        interestDTO.setCardId(cardId);
        CardInterest entity = mapper.toEntity(interestDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardInterestDTO> getInterest(UUID cardId, UUID interestId) {
        return repository.findByInterestId(interestId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardInterest not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardInterestDTO> updateInterest(UUID cardId, UUID interestId, CardInterestDTO interestDTO) {
        return repository.findByInterestId(interestId)
                .flatMap(existingInterest -> {
                    if (!existingInterest.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardInterest not found for the specified card."));
                    }
                    
                    CardInterest updatedInterest = mapper.toEntity(interestDTO);
                    updatedInterest.setInterestId(existingInterest.getInterestId());
                    updatedInterest.setCardId(cardId);
                    
                    return repository.save(updatedInterest);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteInterest(UUID cardId, UUID interestId) {
        return repository.findByInterestId(interestId)
                .flatMap(interest -> {
                    if (!interest.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardInterest not found for the specified card."));
                    }
                    return repository.delete(interest);
                });
    }
}
