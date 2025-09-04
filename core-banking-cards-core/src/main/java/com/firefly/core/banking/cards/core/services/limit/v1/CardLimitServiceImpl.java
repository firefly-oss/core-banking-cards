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


package com.firefly.core.banking.cards.core.services.limit.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.limit.v1.CardLimitMapper;
import com.firefly.core.banking.cards.interfaces.dtos.limit.v1.CardLimitDTO;
import com.firefly.core.banking.cards.models.entities.limit.v1.CardLimit;
import com.firefly.core.banking.cards.models.repositories.limit.v1.CardLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardLimitServiceImpl implements CardLimitService {

    @Autowired
    private CardLimitRepository repository;

    @Autowired
    private CardLimitMapper mapper;

    @Override
    public Mono<PaginationResponse<CardLimitDTO>> listLimits(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardLimitDTO> createLimit(UUID cardId, CardLimitDTO limitDTO) {
        limitDTO.setCardId(cardId);
        CardLimit entity = mapper.toEntity(limitDTO);
        return Mono.just(entity)
                .flatMap(repository::save)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardLimitDTO> getLimit(UUID cardId, UUID limitId) {
        return repository.findByCardLimitId(limitId)
                .filter(cardLimit -> cardLimit.getCardId().equals(cardId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardLimitDTO> updateLimit(UUID cardId, UUID limitId, CardLimitDTO limitDTO) {
        return repository.findByCardLimitId(limitId)
                .filter(cardLimit -> cardLimit.getCardId().equals(cardId))
                .flatMap(existingEntity -> {
                    existingEntity.setLimitType(limitDTO.getLimitType().name());
                    existingEntity.setLimitAmount(limitDTO.getLimitAmount());
                    existingEntity.setCurrentUsage(limitDTO.getCurrentUsage());
                    existingEntity.setResetPeriod(limitDTO.getResetPeriod().name());
                    return repository.save(existingEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteLimit(UUID cardId, UUID limitId) {
        return repository.findByCardLimitId(limitId)
                .filter(cardLimit -> cardLimit.getCardId().equals(cardId))
                .flatMap(repository::delete)
                .then();
    }
}