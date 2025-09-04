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


package com.firefly.core.banking.cards.core.services.acquirer.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.acquirer.v1.CardAcquirerMapper;
import com.firefly.core.banking.cards.interfaces.dtos.acquirer.v1.CardAcquirerDTO;
import com.firefly.core.banking.cards.models.entities.acquirer.v1.CardAcquirer;
import com.firefly.core.banking.cards.models.repositories.acquirer.v1.CardAcquirerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardAcquirerServiceImpl implements CardAcquirerService {

    @Autowired
    private CardAcquirerRepository repository;

    @Autowired
    private CardAcquirerMapper mapper;

    @Override
    public Mono<PaginationResponse<CardAcquirerDTO>> listAcquirers(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardAcquirerDTO> createAcquirer(CardAcquirerDTO acquirerDTO) {
        CardAcquirer entity = mapper.toEntity(acquirerDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardAcquirerDTO> getAcquirer(UUID acquirerId) {
        return repository.findByAcquirerId(acquirerId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardAcquirerDTO> updateAcquirer(UUID acquirerId, CardAcquirerDTO acquirerDTO) {
        return repository.findByAcquirerId(acquirerId)
                .flatMap(existingAcquirer -> {
                    CardAcquirer updatedAcquirer = mapper.toEntity(acquirerDTO);
                    updatedAcquirer.setAcquirerId(existingAcquirer.getAcquirerId());
                    return repository.save(updatedAcquirer);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteAcquirer(UUID acquirerId) {
        return repository.findByAcquirerId(acquirerId)
                .flatMap(repository::delete);
    }
}
