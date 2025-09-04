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


package com.firefly.core.banking.cards.core.mappers.activity.v1;

import com.firefly.core.banking.cards.interfaces.dtos.activity.v1.CardActivityDTO;
import com.firefly.core.banking.cards.models.entities.activity.v1.CardActivity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardActivity entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardActivityMapper {

    /**
     * Convert a CardActivity entity to a CardActivityDTO.
     *
     * @param cardActivity the CardActivity entity to convert
     * @return the resulting CardActivityDTO
     */
    CardActivityDTO toDTO(CardActivity cardActivity);

    /**
     * Convert a CardActivityDTO to a CardActivity entity.
     *
     * @param cardActivityDTO the CardActivityDTO to convert
     * @return the resulting CardActivity entity
     */
    CardActivity toEntity(CardActivityDTO cardActivityDTO);
}
