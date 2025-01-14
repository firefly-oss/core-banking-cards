package com.catalis.core.banking.cards.web.controllers.transaction.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.transaction.v1.CardTransactionServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Card Transactions", description = "APIs for managing transaction records associated with a specific card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/transactions")
public class CardTransactionController {

    @Autowired
    private CardTransactionServiceImpl service;

    @Operation(
            summary = "List Card Transactions",
            description = "Retrieve a paginated list of all transaction records associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved card transactions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No transaction records found for the specified card",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardTransactionDTO>>> getAllTransactions(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listTransactions(cardId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Filter Card Transactions",
            description = "Apply custom filters to retrieve a list of transactions for the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved filtered card transactions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No filtered results found for the specified card",
                    content = @Content)
    })
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardTransactionDTO>>> filterProducts(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @ParameterObject
            @ModelAttribute FilterRequest<CardTransactionDTO> filterRequest
    ) {
        filterRequest.getFilters().setCardId(cardId);
        return service.findFiltered(filterRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Transaction",
            description = "Create a new transaction record and associate it with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card transaction created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardTransactionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid transaction data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardTransactionDTO>> createTransaction(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Data for the new card transaction record", required = true,
                    schema = @Schema(implementation = CardTransactionDTO.class))
            @RequestBody CardTransactionDTO transactionDTO
    ) {
        return service.createTransaction(cardId, transactionDTO)
                .map(createdTransaction -> ResponseEntity.status(201).body(createdTransaction))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Transaction by ID",
            description = "Retrieve a specific transaction record by its unique identifier, ensuring it belongs to the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card transaction",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardTransactionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card transaction not found",
                    content = @Content)
    })
    @GetMapping(value = "/{transactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardTransactionDTO>> getTransaction(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the transaction record", required = true)
            @PathVariable Long transactionId
    ) {
        return service.getTransaction(cardId, transactionId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Transaction",
            description = "Update an existing transaction record associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card transaction updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardTransactionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card transaction record not found",
                    content = @Content)
    })
    @PutMapping(value = "/{transactionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardTransactionDTO>> updateTransaction(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the card transaction to update", required = true)
            @PathVariable Long transactionId,

            @Parameter(description = "Updated transaction data", required = true,
                    schema = @Schema(implementation = CardTransactionDTO.class))
            @RequestBody CardTransactionDTO transactionDTO
    ) {
        return service.updateTransaction(cardId, transactionId, transactionDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Transaction",
            description = "Remove an existing transaction record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Card transaction record deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Card transaction record not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{transactionId}")
    public Mono<ResponseEntity<Void>> deleteTransaction(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the transaction record to delete", required = true)
            @PathVariable Long transactionId
    ) {
        return service.deleteTransaction(cardId, transactionId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}