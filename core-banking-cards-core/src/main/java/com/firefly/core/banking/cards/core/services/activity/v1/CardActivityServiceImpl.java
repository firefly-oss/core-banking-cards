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


package com.firefly.core.banking.cards.core.services.activity.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.activity.v1.CardActivityMapper;
import com.firefly.core.banking.cards.interfaces.dtos.activity.v1.CardActivityDTO;
import com.firefly.core.banking.cards.models.entities.activity.v1.CardActivity;
import com.firefly.core.banking.cards.models.repositories.activity.v1.CardActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardActivityServiceImpl implements CardActivityService {

    @Autowired
    private CardActivityRepository repository;

    @Autowired
    private CardActivityMapper mapper;

    @Override
    public Mono<PaginationResponse<CardActivityDTO>> listActivities(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardActivityDTO> createActivity(UUID cardId, CardActivityDTO activityDTO) {
        activityDTO.setCardId(cardId);
        CardActivity entity = mapper.toEntity(activityDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardActivityDTO> getActivity(UUID cardId, UUID activityId) {
        return repository.findByActivityId(activityId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardActivity not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardActivityDTO> updateActivity(UUID cardId, UUID activityId, CardActivityDTO activityDTO) {
        return repository.findByActivityId(activityId)
                .flatMap(existingActivity -> {
                    if (!existingActivity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardActivity not found for the specified card."));
                    }
                    
                    CardActivity updatedActivity = mapper.toEntity(activityDTO);
                    updatedActivity.setActivityId(existingActivity.getActivityId());
                    updatedActivity.setCardId(cardId);
                    
                    return repository.save(updatedActivity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteActivity(UUID cardId, UUID activityId) {
        return repository.findByActivityId(activityId)
                .flatMap(activity -> {
                    if (!activity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardActivity not found for the specified card."));
                    }
                    return repository.delete(activity);
                });
    }
}
