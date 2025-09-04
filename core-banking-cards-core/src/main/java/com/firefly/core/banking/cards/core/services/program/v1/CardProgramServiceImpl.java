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


package com.firefly.core.banking.cards.core.services.program.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.program.v1.CardProgramMapper;
import com.firefly.core.banking.cards.interfaces.dtos.program.v1.CardProgramDTO;
import com.firefly.core.banking.cards.models.entities.program.v1.CardProgram;
import com.firefly.core.banking.cards.models.repositories.program.v1.CardProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardProgramServiceImpl implements CardProgramService {

    @Autowired
    private CardProgramRepository repository;

    @Autowired
    private CardProgramMapper mapper;

    @Override
    public Mono<PaginationResponse<CardProgramDTO>> listPrograms(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardProgramDTO> createProgram(CardProgramDTO programDTO) {
        CardProgram entity = mapper.toEntity(programDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardProgramDTO> getProgram(UUID programId) {
        return repository.findByProgramId(programId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardProgramDTO> updateProgram(UUID programId, CardProgramDTO programDTO) {
        return repository.findByProgramId(programId)
                .flatMap(existingProgram -> {
                    CardProgram updatedProgram = mapper.toEntity(programDTO);
                    updatedProgram.setProgramId(existingProgram.getProgramId());
                    return repository.save(updatedProgram);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteProgram(UUID programId) {
        return repository.findByProgramId(programId)
                .flatMap(repository::delete);
    }
}
