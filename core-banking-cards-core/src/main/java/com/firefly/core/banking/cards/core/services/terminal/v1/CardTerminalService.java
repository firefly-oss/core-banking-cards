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

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.banking.cards.interfaces.dtos.terminal.v1.CardTerminalDTO;
import reactor.core.publisher.Mono;

import java.util.UUID;
public interface CardTerminalService {

    /**
     * List all card terminals (paginated).
     */
    Mono<PaginationResponse<CardTerminalDTO>> listTerminals(PaginationRequest paginationRequest);

    /**
     * Create a new card terminal.
     */
    Mono<CardTerminalDTO> createTerminal(CardTerminalDTO terminalDTO);

    /**
     * Retrieve a specific card terminal by ID.
     */
    Mono<CardTerminalDTO> getTerminal(UUID terminalId);

    /**
     * Update an existing card terminal by ID.
     */
    Mono<CardTerminalDTO> updateTerminal(UUID terminalId, CardTerminalDTO terminalDTO);

    /**
     * Delete a card terminal by its unique ID.
     */
    Mono<Void> deleteTerminal(UUID terminalId);
}
