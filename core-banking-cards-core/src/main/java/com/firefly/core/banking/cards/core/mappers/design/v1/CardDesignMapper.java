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


package com.firefly.core.banking.cards.core.mappers.design.v1;

import com.firefly.core.banking.cards.interfaces.dtos.design.v1.CardDesignDTO;
import com.firefly.core.banking.cards.models.entities.design.v1.CardDesign;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardDesign entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardDesignMapper {

    /**
     * Convert a CardDesign entity to a CardDesignDTO.
     *
     * @param cardDesign the CardDesign entity to convert
     * @return the resulting CardDesignDTO
     */
    CardDesignDTO toDTO(CardDesign cardDesign);

    /**
     * Convert a CardDesignDTO to a CardDesign entity.
     *
     * @param cardDesignDTO the CardDesignDTO to convert
     * @return the resulting CardDesign entity
     */
    CardDesign toEntity(CardDesignDTO cardDesignDTO);
}
