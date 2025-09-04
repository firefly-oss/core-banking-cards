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


package com.firefly.core.banking.cards.core.services.virtual.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.virtual.v1.VirtualCardMapper;
import com.firefly.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
import com.firefly.core.banking.cards.models.entities.virtual.v1.VirtualCard;
import com.firefly.core.banking.cards.models.repositories.virtual.v1.VirtualCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class VirtualCardServiceImpl implements VirtualCardService {

    @Autowired
    private VirtualCardRepository repository;

    @Autowired
    private VirtualCardMapper mapper;

    @Override
    public Mono<PaginationResponse<VirtualCardDTO>> listVirtualCards(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<VirtualCardDTO> createVirtualCard(UUID cardId, VirtualCardDTO virtualCardDTO) {
        virtualCardDTO.setCardId(cardId);
        VirtualCard virtualCard = mapper.toEntity(virtualCardDTO);
        return repository.save(virtualCard)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<VirtualCardDTO> getVirtualCard(UUID cardId, UUID virtualCardId) {
        return repository.findByVirtualCardId(virtualCardId)
                .filter(virtualCard -> virtualCard.getCardId().equals(cardId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<VirtualCardDTO> updateVirtualCard(UUID cardId, UUID virtualCardId, VirtualCardDTO virtualCardDTO) {
        return repository.findByVirtualCardId(virtualCardId)
                .filter(existingCard -> existingCard.getCardId().equals(cardId))
                .flatMap(existingCard -> {
                    virtualCardDTO.setVirtualCardId(existingCard.getVirtualCardId());
                    virtualCardDTO.setCardId(cardId);
                    VirtualCard updatedEntity = mapper.toEntity(virtualCardDTO);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteVirtualCard(UUID cardId, UUID virtualCardId) {
        return repository.findByVirtualCardId(virtualCardId)
                .filter(virtualCard -> virtualCard.getCardId().equals(cardId))
                .flatMap(repository::delete);
    }
}
