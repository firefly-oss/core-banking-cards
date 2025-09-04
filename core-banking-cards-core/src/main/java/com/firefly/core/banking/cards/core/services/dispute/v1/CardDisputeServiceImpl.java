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


package com.firefly.core.banking.cards.core.services.dispute.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.dispute.v1.CardDisputeMapper;
import com.firefly.core.banking.cards.interfaces.dtos.dispute.v1.CardDisputeDTO;
import com.firefly.core.banking.cards.models.entities.dispute.v1.CardDispute;
import com.firefly.core.banking.cards.models.repositories.dispute.v1.CardDisputeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardDisputeServiceImpl implements CardDisputeService {

    @Autowired
    private CardDisputeRepository repository;

    @Autowired
    private CardDisputeMapper mapper;

    @Override
    public Mono<PaginationResponse<CardDisputeDTO>> listDisputes(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardDisputeDTO> createDispute(UUID cardId, CardDisputeDTO disputeDTO) {
        disputeDTO.setCardId(cardId);
        CardDispute entity = mapper.toEntity(disputeDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardDisputeDTO> getDispute(UUID cardId, UUID disputeId) {
        return repository.findByDisputeId(disputeId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardDispute not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardDisputeDTO> updateDispute(UUID cardId, UUID disputeId, CardDisputeDTO disputeDTO) {
        return repository.findByDisputeId(disputeId)
                .flatMap(existingDispute -> {
                    if (!existingDispute.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardDispute not found for the specified card."));
                    }
                    
                    CardDispute updatedDispute = mapper.toEntity(disputeDTO);
                    updatedDispute.setDisputeId(existingDispute.getDisputeId());
                    updatedDispute.setCardId(cardId);
                    
                    return repository.save(updatedDispute);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteDispute(UUID cardId, UUID disputeId) {
        return repository.findByDisputeId(disputeId)
                .flatMap(dispute -> {
                    if (!dispute.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardDispute not found for the specified card."));
                    }
                    return repository.delete(dispute);
                });
    }
}
