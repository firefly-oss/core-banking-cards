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


package com.firefly.core.banking.cards.core.services.security.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.security.v1.CardSecurityMapper;
import com.firefly.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
import com.firefly.core.banking.cards.models.entities.security.v1.CardSecurity;
import com.firefly.core.banking.cards.models.repositories.security.v1.CardSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardSecurityServiceImpl implements CardSecurityService {

    @Autowired
    private CardSecurityRepository repository;

    @Autowired
    private CardSecurityMapper mapper;

    @Override
    public Mono<PaginationResponse<CardSecurityDTO>> listSecuritySettings(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardSecurityDTO> createSecuritySetting(UUID cardId, CardSecurityDTO securityDTO) {
        CardSecurity entity = mapper.toEntity(securityDTO);
        entity.setCardId(cardId);

        return repository.save(entity)
                .map(mapper::toDTO)
                .onErrorResume(throwable -> Mono.error(new RuntimeException("Error saving CardSecurity: " + throwable.getMessage())));
    }

    @Override
    public Mono<CardSecurityDTO> getSecuritySetting(UUID cardId, UUID securityId) {
        return repository.findByCardSecurityId(securityId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardSecurity not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardSecurityDTO> updateSecuritySetting(UUID cardId, UUID securityId, CardSecurityDTO securityDTO) {
        return repository.findByCardSecurityId(securityId)
                .flatMap(existingEntity -> {
                    if (!existingEntity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardSecurity not found for the specified card."));
                    }

                    CardSecurity updatedEntity = mapper.toEntity(securityDTO);
                    updatedEntity.setCardSecurityId(securityId);
                    updatedEntity.setCardId(cardId);

                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteSecuritySetting(UUID cardId, UUID securityId) {
        return repository.findByCardSecurityId(securityId)
                .flatMap(existingEntity -> {
                    if (!existingEntity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardSecurity not found for the specified card."));
                    }

                    return repository.deleteById(securityId);
                });
    }
}
