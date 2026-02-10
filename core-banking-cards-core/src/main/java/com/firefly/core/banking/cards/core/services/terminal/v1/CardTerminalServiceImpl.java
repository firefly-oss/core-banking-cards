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


package com.firefly.core.banking.cards.core.services.terminal.v1;

import org.fireflyframework.core.queries.PaginationRequest;
import org.fireflyframework.core.queries.PaginationResponse;
import org.fireflyframework.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.terminal.v1.CardTerminalMapper;
import com.firefly.core.banking.cards.interfaces.dtos.terminal.v1.CardTerminalDTO;
import com.firefly.core.banking.cards.models.entities.terminal.v1.CardTerminal;
import com.firefly.core.banking.cards.models.repositories.terminal.v1.CardTerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardTerminalServiceImpl implements CardTerminalService {

    @Autowired
    private CardTerminalRepository repository;

    @Autowired
    private CardTerminalMapper mapper;

    @Override
    public Mono<PaginationResponse<CardTerminalDTO>> listTerminals(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardTerminalDTO> createTerminal(CardTerminalDTO terminalDTO) {
        CardTerminal entity = mapper.toEntity(terminalDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardTerminalDTO> getTerminal(UUID terminalId) {
        return repository.findByTerminalId(terminalId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardTerminalDTO> updateTerminal(UUID terminalId, CardTerminalDTO terminalDTO) {
        return repository.findByTerminalId(terminalId)
                .flatMap(existingTerminal -> {
                    CardTerminal updatedTerminal = mapper.toEntity(terminalDTO);
                    updatedTerminal.setTerminalId(existingTerminal.getTerminalId());
                    return repository.save(updatedTerminal);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteTerminal(UUID terminalId) {
        return repository.findByTerminalId(terminalId)
                .flatMap(repository::delete);
    }
}
