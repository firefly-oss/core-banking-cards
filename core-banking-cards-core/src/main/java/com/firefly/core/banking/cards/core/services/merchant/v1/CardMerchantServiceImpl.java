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


package com.firefly.core.banking.cards.core.services.merchant.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.merchant.v1.CardMerchantMapper;
import com.firefly.core.banking.cards.interfaces.dtos.merchant.v1.CardMerchantDTO;
import com.firefly.core.banking.cards.models.entities.merchant.v1.CardMerchant;
import com.firefly.core.banking.cards.models.repositories.merchant.v1.CardMerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardMerchantServiceImpl implements CardMerchantService {

    @Autowired
    private CardMerchantRepository repository;

    @Autowired
    private CardMerchantMapper mapper;

    @Override
    public Mono<PaginationResponse<CardMerchantDTO>> listMerchants(PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                repository::findAllBy,
                repository::count
        );
    }

    @Override
    public Mono<CardMerchantDTO> createMerchant(CardMerchantDTO merchantDTO) {
        CardMerchant entity = mapper.toEntity(merchantDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardMerchantDTO> getMerchant(UUID merchantId) {
        return repository.findByMerchantId(merchantId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardMerchantDTO> updateMerchant(UUID merchantId, CardMerchantDTO merchantDTO) {
        return repository.findByMerchantId(merchantId)
                .flatMap(existingMerchant -> {
                    CardMerchant updatedMerchant = mapper.toEntity(merchantDTO);
                    updatedMerchant.setMerchantId(existingMerchant.getMerchantId());
                    return repository.save(updatedMerchant);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteMerchant(UUID merchantId) {
        return repository.findByMerchantId(merchantId)
                .flatMap(repository::delete);
    }
}
