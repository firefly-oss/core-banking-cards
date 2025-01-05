package com.catalis.core.banking.cards.web.controllers.card.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.web.error.models.ErrorResponse;
import com.catalis.core.banking.cards.core.services.card.v1.CardCreateService;
import com.catalis.core.banking.cards.core.services.card.v1.CardDeleteService;
import com.catalis.core.banking.cards.core.services.card.v1.CardGetService;
import com.catalis.core.banking.cards.core.services.card.v1.CardUpdateService;
import com.catalis.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
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

@Tag(name = "Cards", description = "Manage Cards")
@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    @Autowired
    private CardCreateService cardCreateService;

    @Autowired
    private CardGetService cardGetService;

    @Autowired
    private CardUpdateService cardUpdateService;

    @Autowired
    private CardDeleteService cardDeleteService;

    @Operation(
            summary = "Create a new Card",
            description = "Create a new card resource with the provided details."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Card successfully created",
                    content = @Content(schema = @Schema(implementation = CardDTO.class))
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
    public Mono<ResponseEntity<CardDTO>> createCard(
            @Parameter(
                    name = "cardDetails",
                    description = "Card details required to create a new card",
                    required = true
            )
            @RequestBody CardDTO cardDetails
    ) {
        return cardCreateService.createCard(cardDetails)
                .map(createdCard -> ResponseEntity.status(HttpStatus.CREATED).body(createdCard));
    }

    @Operation(
            summary = "Retrieve Card by ID",
            description = "Fetch a card's details by its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card found and returned",
                    content = @Content(schema = @Schema(implementation = CardDTO.class))
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
    @GetMapping("/details/{cardId}")
    public Mono<ResponseEntity<CardDTO>> getCardById(
            @Parameter(
                    name = "cardId",
                    description = "Unique identifier of the card to retrieve",
                    required = true
            )
            @PathVariable("cardId") Long cardId
    ) {
        return cardGetService.getCardById(cardId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Retrieve Cards by Account ID",
            description = "Fetch all cards associated with a specific account by its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Cards successfully retrieved",
                    content = @Content(schema = @Schema(implementation = PaginationResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No cards found for the given account ID",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid account ID or request parameters",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/by-account/{accountId}")
    public Mono<ResponseEntity<PaginationResponse<CardDTO>>> getCardByAccountId(
            @Parameter(
                    name = "accountId",
                    description = "Unique identifier of the account to fetch cards for",
                    required = true
            )
            @PathVariable("accountId") Long accountId,
            @Parameter(
                    name = "paginationRequest",
                    description = "Pagination and sorting parameters",
                    required = false
            )
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return cardGetService.getAllCardsByAccountId(accountId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update a Card",
            description = "Update an existing card's details by its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Card successfully updated",
                    content = @Content(schema = @Schema(implementation = CardDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Card not found",
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
    @PutMapping("/{cardId}")
    public Mono<ResponseEntity<CardDTO>> updateCard(
            @Parameter(
                    name = "cardId",
                    description = "Unique identifier of the card to update",
                    required = true
            )
            @PathVariable("cardId") Long cardId,
            @Parameter(
                    name = "updatedCardDetails",
                    description = "New details for updating the card",
                    required = true
            )
            @RequestBody CardDTO updatedCardDetails
    ) {
        return cardUpdateService.updateCard(cardId, updatedCardDetails)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a Card",
            description = "Delete an existing card by its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Card successfully deleted"
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
    @DeleteMapping("/{cardId}")
    public Mono<ResponseEntity<Void>> deleteCard(
            @Parameter(
                    name = "cardId",
                    description = "Unique identifier of the card to delete",
                    required = true
            )
            @PathVariable("cardId") Long cardId
    ) {
        return cardDeleteService.deleteCard(cardId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}