package com.catalis.core.banking.cards.web.controllers.card.v1;

import com.catalis.core.banking.cards.core.services.card.v1.CardServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Cards", description = "APIs for managing card records within the banking system")
@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    @Autowired
    private CardServiceImpl service;

    @Operation(
            summary = "Create Card",
            description = "Create a new card record in the banking system.\n\n" +
                    "This endpoint allows the creation of new payment cards. Cards are the central entity in the system " +
                    "and are associated with specific card programs, issuers, networks, and account holders. " +
                    "The created card can be either physical, virtual, or both, depending on the configuration."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid card data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardDTO>> createCard(
            @Parameter(description = "Data for the new card", required = true,
                    schema = @Schema(implementation = CardDTO.class))
            @RequestBody CardDTO cardDTO
    ) {
        return service.createCard(cardDTO)
                .map(createdCard -> ResponseEntity.status(201).body(createdCard))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card by ID",
            description = "Retrieve an existing card record by its unique identifier.\n\n" +
                    "This endpoint returns comprehensive information about a card, including its status, " +
                    "associated accounts, limits, and key attributes. Sensitive information like the full card number, " +
                    "CVV, and PIN are masked or excluded from the response for security reasons."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card not found",
                    content = @Content)
    })
    @GetMapping(value = "/{cardId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardDTO>> getCard(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId
    ) {
        return service.getCard(cardId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card",
            description = "Update an existing card record by its unique identifier.\n\n" +
                    "This endpoint allows modification of card attributes such as status, limits, and expiration details. " +
                    "Note that certain sensitive fields and core attributes (like card number or BIN) cannot be modified " +
                    "after card creation. For such changes, a new card must be issued."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card not found",
                    content = @Content)
    })
    @PutMapping(value = "/{cardId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardDTO>> updateCard(
            @Parameter(description = "Unique identifier of the card to update", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Updated card data", required = true,
                    schema = @Schema(implementation = CardDTO.class))
            @RequestBody CardDTO cardDTO
    ) {
        return service.updateCard(cardId, cardDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card",
            description = "Remove an existing card record by its unique identifier.\n\n" +
                    "This endpoint permanently removes a card from the system. This operation should be used with extreme caution, " +
                    "especially for cards that have transaction history. In most production scenarios, cards should be deactivated " +
                    "or blocked rather than deleted to preserve transaction history and audit trails."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Card deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Card not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{cardId}")
    public Mono<ResponseEntity<Void>> deleteCard(
            @Parameter(description = "Unique identifier of the card to delete", required = true)
            @PathVariable Long cardId
    ) {
        return service.deleteCard(cardId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}