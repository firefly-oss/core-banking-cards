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


package com.firefly.core.banking.cards.core.mappers.bin.v1;

import com.firefly.core.banking.cards.interfaces.dtos.bin.v1.BINDTO;
import com.firefly.core.banking.cards.models.entities.bin.v1.BIN;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the BIN entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BINMapper {

    /**
     * Convert a BIN entity to a BINDTO.
     *
     * @param bin the BIN entity to convert
     * @return the resulting BINDTO
     */
    BINDTO toDTO(BIN bin);

    /**
     * Convert a BINDTO to a BIN entity.
     *
     * @param bindto the BINDTO to convert
     * @return the resulting BIN entity
     */
    BIN toEntity(BINDTO bindto);
}