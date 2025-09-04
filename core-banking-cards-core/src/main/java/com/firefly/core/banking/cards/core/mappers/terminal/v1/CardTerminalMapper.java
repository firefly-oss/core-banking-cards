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


package com.firefly.core.banking.cards.core.mappers.terminal.v1;

import com.firefly.core.banking.cards.interfaces.dtos.terminal.v1.CardTerminalDTO;
import com.firefly.core.banking.cards.models.entities.terminal.v1.CardTerminal;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper for the CardTerminal entity and its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardTerminalMapper {

    /**
     * Convert a CardTerminal entity to a CardTerminalDTO.
     *
     * @param cardTerminal the CardTerminal entity to convert
     * @return the resulting CardTerminalDTO
     */
    CardTerminalDTO toDTO(CardTerminal cardTerminal);

    /**
     * Convert a CardTerminalDTO to a CardTerminal entity.
     *
     * @param cardTerminalDTO the CardTerminalDTO to convert
     * @return the resulting CardTerminal entity
     */
    CardTerminal toEntity(CardTerminalDTO cardTerminalDTO);
}
