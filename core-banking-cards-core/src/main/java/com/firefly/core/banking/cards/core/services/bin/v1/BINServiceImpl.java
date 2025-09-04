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


package com.firefly.core.banking.cards.core.services.bin.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.bin.v1.BINMapper;
import com.firefly.core.banking.cards.interfaces.dtos.bin.v1.BINDTO;
import com.firefly.core.banking.cards.models.entities.bin.v1.BIN;
import com.firefly.core.banking.cards.models.repositories.bin.v1.BINRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class BINServiceImpl implements BINService {

    @Autowired
    private BINRepository repository;

    @Autowired
    private BINMapper mapper;

    @Override
    public Mono<PaginationResponse<BINDTO>> listBINs(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<BINDTO> createBIN(BINDTO binDTO) {
        BIN entity = mapper.toEntity(binDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BINDTO> getBIN(UUID binId) {
        return repository.findByBinId(binId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BINDTO> getBINByNumber(String binNumber) {
        return repository.findByBinNumber(binNumber)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BINDTO> updateBIN(UUID binId, BINDTO binDTO) {
        return repository.findByBinId(binId)
                .flatMap(existingBIN -> {
                    BIN updatedBIN = mapper.toEntity(binDTO);
                    updatedBIN.setBinId(existingBIN.getBinId());
                    return repository.save(updatedBIN);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteBIN(UUID binId) {
        return repository.findByBinId(binId)
                .flatMap(repository::delete);
    }
}
