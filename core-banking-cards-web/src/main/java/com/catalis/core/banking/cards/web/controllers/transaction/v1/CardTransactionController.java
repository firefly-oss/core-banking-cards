package com.catalis.core.banking.cards.web.controllers.transaction.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.web.error.models.ErrorResponse;
import com.catalis.core.banking.cards.core.services.transaction.v1.CardTransactionCreateService;
import com.catalis.core.banking.cards.core.services.transaction.v1.CardTransactionDeleteService;
import com.catalis.core.banking.cards.core.services.transaction.v1.CardTransactionGetService;
import com.catalis.core.banking.cards.core.services.transaction.v1.CardTransactionUpdateService;
import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionFilterDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Card Transactions", description = "Manage Transactions for a specific Card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/transactions")
public class CardTransactionController {

    @Autowired
    private CardTransactionCreateService createService;

    @Autowired
    private CardTransactionGetService getService;

    @Autowired
    private CardTransactionUpdateService updateService;

    @Autowired
    private CardTransactionDeleteService deleteService;


    @Operation(
            summary = "Create a Transaction",
            description = "Creates a new Transaction under a specific Card."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Transaction successfully created",
                    content = @Content(schema = @Schema(implementation = CardTransactionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    public Mono<ResponseEntity<CardTransactionDTO>> createTransaction(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "dto", description = "Details of the transaction to create", required = true)
            @RequestBody CardTransactionDTO dto
    ) {
        dto.setCardId(cardId);
        return createService.createTransaction(dto)
                .map(createdTx -> ResponseEntity.status(HttpStatus.CREATED).body(createdTx));
    }


    @Operation(
            summary = "Get all Transactions (paginated)",
            description = "Retrieve all Transactions for a specific Card."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paginated list of transactions returned",
                    content = @Content(schema = @Schema(implementation = PaginationResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid pagination parameters",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping
    public Mono<ResponseEntity<PaginationResponse<CardTransactionDTO>>> getAllTransactions(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "paginationRequest", description = "Pagination parameters (pageNumber, pageSize, sortBy, sortDirection)")
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return getService.getAllTransactionsByCardId(cardId, paginationRequest)
                .map(ResponseEntity::ok);
    }


    @Operation(
            summary = "Get a Transaction by ID",
            description = "Retrieve a specific Transaction."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Transaction found and returned",
                    content = @Content(schema = @Schema(implementation = CardTransactionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Transaction not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{transactionId}")
    public Mono<ResponseEntity<CardTransactionDTO>> getTransactionById(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "transactionId", description = "Unique identifier of the transaction", required = true)
            @PathVariable(name = "transactionId") Long transactionId
    ) {
        return getService.getTransactionById(transactionId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Update a Transaction",
            description = "Updates an existing Transaction resource."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Transaction successfully updated",
                    content = @Content(schema = @Schema(implementation = CardTransactionDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Transaction not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/{transactionId}")
    public Mono<ResponseEntity<CardTransactionDTO>> updateTransaction(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "transactionId", description = "Unique identifier of the transaction to update", required = true)
            @PathVariable(name = "transactionId") Long transactionId,
            @Parameter(name = "dto", description = "Updated details for the transaction", required = true)
            @RequestBody CardTransactionDTO dto
    ) {
        dto.setCardId(cardId);
        return updateService.updateTransaction(transactionId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Delete a Transaction",
            description = "Removes a Transaction resource by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Transaction successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Transaction not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{transactionId}")
    public Mono<ResponseEntity<Void>> deleteTransaction(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "transactionId", description = "Unique identifier of the transaction to delete", required = true)
            @PathVariable(name = "transactionId") Long transactionId
    ) {
        return deleteService.deleteTransaction(transactionId)
                .thenReturn(ResponseEntity.noContent().build());
    }


    @Operation(
            summary = "Filter Transactions with criteria",
            description = "Filters and paginates transactions by optional fields (date range, amounts, etc.)."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Filtered paginated list of transactions returned",
                    content = @Content(schema = @Schema(implementation = PaginationResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid filter or pagination parameters",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping("/filter")
    public Mono<ResponseEntity<PaginationResponse<CardTransactionDTO>>> filterTransactions(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "filterDTO", description = "Transaction filter criteria", required = true)
            @RequestBody CardTransactionFilterDTO filterDTO,
            @Parameter(name = "paginationRequest", description = "Pagination parameters (pageNumber, pageSize, sortBy, sortDirection)")
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        filterDTO.setCardId(cardId);
        return getService.filterTransactions(filterDTO, paginationRequest)
                .map(ResponseEntity::ok);
    }
}