/*
 * Copyright 2025 Firefly Software Foundation
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


package io.fireflysoftware.core.banking.cards.core.services.card.v1.activation;

import io.fireflysoftware.core.banking.cards.interfaces.dtos.card.v1.CardActivationReceipt;
import io.r2dbc.spi.Row;
import io.r2dbc.postgresql.api.PostgresqlException;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

/** Each SQL function is one atomic database statement; no lock spans an issuer call. */
@Repository
public class CardActivationStore {
    private final DatabaseClient database;
    public CardActivationStore(DatabaseClient database) { this.database = database; }

    public Mono<CardActivationReceipt> reserve(UUID action, UUID card, CardActivationPolicy.AuthorizedScope scope,
                                               String digest) {
        return bindScope(database.sql("SELECT * FROM reserve_card_activation(:action,:card,:tenant,:actor,"
                        + ":party,:account,:digest,:expires)"), scope)
                .bind("action", action).bind("card", card).bind("digest", digest)
                .map((row, metadata) -> receipt(row)).one().onErrorMap(DataAccessException.class,
                        CardActivationStore::persistenceFailure);
    }

    public Mono<CardIssuerPort.Binding> binding(UUID card) {
        return database.sql("SELECT * FROM card_issuer_instrument WHERE card_id=:card")
                .bind("card", card).map((row, metadata) -> new CardIssuerPort.Binding(
                        row.get("card_id", UUID.class), row.get("tenant_id", UUID.class),
                        row.get("party_id", UUID.class), row.get("account_id", UUID.class),
                        row.get("adapter_id", String.class), row.get("instrument_reference", String.class),
                        row.get("binding_version", Long.class))).one()
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "Activation is not permitted")));
    }

    public Mono<CardActivationReceipt> finish(UUID action, CardActivationPolicy.AuthorizedScope scope,
                                              String outcome, String issuerReceipt) {
        var query = bindScope(database.sql("SELECT * FROM finish_card_activation(:action,:tenant,:actor,"
                + ":party,:account,:expires,:outcome,:receipt)"), scope)
                .bind("action", action).bind("outcome", outcome);
        query = issuerReceipt == null ? query.bindNull("receipt", String.class) : query.bind("receipt", issuerReceipt);
        return query.map((row, metadata) -> receipt(row)).one().onErrorMap(DataAccessException.class,
                CardActivationStore::persistenceFailure);
    }

    private static DatabaseClient.GenericExecuteSpec bindScope(DatabaseClient.GenericExecuteSpec query,
                                                                CardActivationPolicy.AuthorizedScope scope) {
        return query.bind("tenant", scope.tenantId()).bind("actor", scope.actorId())
                .bind("party", scope.partyId()).bind("account", scope.accountId()).bind("expires", scope.expiresAt());
    }
    private static ResponseStatusException persistenceFailure(DataAccessException error) {
        Throwable cause = error;
        while (cause != null) {
            if (cause instanceof io.r2dbc.spi.R2dbcException databaseError
                    && "P0001".equals(databaseError.getSqlState())) {
                return new ResponseStatusException(HttpStatus.CONFLICT, "Activation is not permitted");
            }
            if (cause instanceof io.r2dbc.spi.R2dbcException databaseError
                    && "23505".equals(databaseError.getSqlState())
                    && cause instanceof PostgresqlException postgresError
                    && postgresError.getErrorDetails().getTableName().filter("card_activation_action"::equals).isPresent()
                    && postgresError.getErrorDetails().getConstraintName().filter("card_activation_action_pkey"::equals).isPresent()) {
                return new ResponseStatusException(HttpStatus.CONFLICT, "Activation is not permitted");
            }
            cause = cause.getCause();
        }
        return new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Activation persistence is unavailable");
    }

    private static CardActivationReceipt receipt(Row row) {
        return new CardActivationReceipt(row.get("action_id", UUID.class), row.get("card_id", UUID.class),
                CardActivationReceipt.State.valueOf(row.get("state", String.class)),
                row.get("activation_time", OffsetDateTime.class));
    }
}
