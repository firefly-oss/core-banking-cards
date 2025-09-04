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


package com.firefly.core.banking.cards.core.services.balance.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.balance.v1.CardBalanceMapper;
import com.firefly.core.banking.cards.interfaces.dtos.balance.v1.CardBalanceDTO;
import com.firefly.core.banking.cards.models.entities.balance.v1.CardBalance;
import com.firefly.core.banking.cards.models.repositories.balance.v1.CardBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardBalanceServiceImpl implements CardBalanceService {

    @Autowired
    private CardBalanceRepository repository;

    @Autowired
    private CardBalanceMapper mapper;

    @Override
    public Mono<PaginationResponse<CardBalanceDTO>> listBalances(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardBalanceDTO> createBalance(UUID cardId, CardBalanceDTO balanceDTO) {
        balanceDTO.setCardId(cardId);
        CardBalance entity = mapper.toEntity(balanceDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardBalanceDTO> getBalance(UUID cardId, UUID balanceId) {
        return repository.findByBalanceId(balanceId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardBalance not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardBalanceDTO> updateBalance(UUID cardId, UUID balanceId, CardBalanceDTO balanceDTO) {
        return repository.findByBalanceId(balanceId)
                .flatMap(existingBalance -> {
                    if (!existingBalance.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardBalance not found for the specified card."));
                    }
                    
                    CardBalance updatedBalance = mapper.toEntity(balanceDTO);
                    updatedBalance.setBalanceId(existingBalance.getBalanceId());
                    updatedBalance.setCardId(cardId);
                    
                    return repository.save(updatedBalance);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteBalance(UUID cardId, UUID balanceId) {
        return repository.findByBalanceId(balanceId)
                .flatMap(balance -> {
                    if (!balance.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardBalance not found for the specified card."));
                    }
                    return repository.delete(balance);
                });
    }
}
