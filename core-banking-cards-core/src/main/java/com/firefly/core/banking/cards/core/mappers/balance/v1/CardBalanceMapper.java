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


package com.firefly.core.banking.cards.core.mappers.balance.v1;

import com.firefly.core.banking.cards.interfaces.dtos.balance.v1.CardBalanceDTO;
import com.firefly.core.banking.cards.models.entities.balance.v1.CardBalance;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardBalance entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardBalanceMapper {

    /**
     * Convert a CardBalance entity to a CardBalanceDTO.
     *
     * @param cardBalance the CardBalance entity to convert
     * @return the resulting CardBalanceDTO
     */
    CardBalanceDTO toDTO(CardBalance cardBalance);

    /**
     * Convert a CardBalanceDTO to a CardBalance entity.
     *
     * @param cardBalanceDTO the CardBalanceDTO to convert
     * @return the resulting CardBalance entity
     */
    CardBalance toEntity(CardBalanceDTO cardBalanceDTO);
}