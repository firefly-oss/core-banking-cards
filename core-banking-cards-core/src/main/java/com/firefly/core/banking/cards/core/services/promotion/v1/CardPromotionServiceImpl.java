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


package com.firefly.core.banking.cards.core.services.promotion.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.promotion.v1.CardPromotionMapper;
import com.firefly.core.banking.cards.interfaces.dtos.promotion.v1.CardPromotionDTO;
import com.firefly.core.banking.cards.models.entities.promotion.v1.CardPromotion;
import com.firefly.core.banking.cards.models.repositories.promotion.v1.CardPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional
public class CardPromotionServiceImpl implements CardPromotionService {

    @Autowired
    private CardPromotionRepository repository;

    @Autowired
    private CardPromotionMapper mapper;

    @Override
    public Mono<PaginationResponse<CardPromotionDTO>> listPromotions(UUID cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardPromotionDTO> createPromotion(UUID cardId, CardPromotionDTO promotionDTO) {
        promotionDTO.setCardId(cardId);
        CardPromotion entity = mapper.toEntity(promotionDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardPromotionDTO> getPromotion(UUID cardId, UUID promotionId) {
        return repository.findByPromotionId(promotionId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardPromotion not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardPromotionDTO> updatePromotion(UUID cardId, UUID promotionId, CardPromotionDTO promotionDTO) {
        return repository.findByPromotionId(promotionId)
                .flatMap(existingPromotion -> {
                    if (!existingPromotion.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardPromotion not found for the specified card."));
                    }
                    
                    CardPromotion updatedPromotion = mapper.toEntity(promotionDTO);
                    updatedPromotion.setPromotionId(existingPromotion.getPromotionId());
                    updatedPromotion.setCardId(cardId);
                    
                    return repository.save(updatedPromotion);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deletePromotion(UUID cardId, UUID promotionId) {
        return repository.findByPromotionId(promotionId)
                .flatMap(promotion -> {
                    if (!promotion.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardPromotion not found for the specified card."));
                    }
                    return repository.delete(promotion);
                });
    }
}
