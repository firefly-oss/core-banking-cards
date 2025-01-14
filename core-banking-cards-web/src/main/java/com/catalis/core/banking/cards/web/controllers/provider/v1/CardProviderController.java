package com.catalis.core.banking.cards.web.controllers.provider.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.provider.v1.CardProviderServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
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

@Tag(name = "Card Providers", description = "APIs for managing provider records associated with a specific card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/providers")
public class CardProviderController {

    @Autowired
    private CardProviderServiceImpl service;

    @Operation(
            summary = "List Card Providers",
            description = "Retrieve a paginated list of provider records associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card provider records",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No provider records found for the specified card",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardProviderDTO>>> getAllProviders(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listProviders(cardId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Provider",
            description = "Create a new provider record and associate it with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card provider created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardProviderDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid card provider data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardProviderDTO>> createProvider(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Data for the new card provider record", required = true,
                    schema = @Schema(implementation = CardProviderDTO.class))
            @RequestBody CardProviderDTO providerDTO
    ) {
        return service.createProvider(cardId, providerDTO)
                .map(createdProvider -> ResponseEntity.status(201).body(createdProvider))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Provider by ID",
            description = "Retrieve a specific card provider record by its unique identifier, ensuring it belongs to the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card provider",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardProviderDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card provider record not found",
                    content = @Content)
    })
    @GetMapping(value = "/{providerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardProviderDTO>> getProvider(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the card provider record", required = true)
            @PathVariable Long providerId
    ) {
        return service.getProvider(cardId, providerId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Provider",
            description = "Update an existing provider record associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card provider updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardProviderDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card provider record not found",
                    content = @Content)
    })
    @PutMapping(value = "/{providerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardProviderDTO>> updateProvider(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the card provider record to update", required = true)
            @PathVariable Long providerId,

            @Parameter(description = "Updated card provider data", required = true,
                    schema = @Schema(implementation = CardProviderDTO.class))
            @RequestBody CardProviderDTO providerDTO
    ) {
        return service.updateProvider(cardId, providerId, providerDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Provider",
            description = "Remove an existing provider record from a card by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Card provider record deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Card provider record not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{providerId}")
    public Mono<ResponseEntity<Void>> deleteProvider(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the card provider record to delete", required = true)
            @PathVariable Long providerId
    ) {
        return service.deleteProvider(cardId, providerId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}