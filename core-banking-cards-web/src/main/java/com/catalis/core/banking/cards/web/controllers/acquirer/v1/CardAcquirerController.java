package com.catalis.core.banking.cards.web.controllers.acquirer.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.acquirer.v1.CardAcquirerServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.acquirer.v1.CardAcquirerDTO;
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

@Tag(name = "Card Acquirers", description = "APIs for managing card acquirer records")
@RestController
@RequestMapping("/api/v1/card-acquirers")
public class CardAcquirerController {

    @Autowired
    private CardAcquirerServiceImpl service;

    @Operation(
            summary = "List Card Acquirers",
            description = "Retrieve a paginated list of all card acquirer records."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved card acquirers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No card acquirer records found",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardAcquirerDTO>>> getAllAcquirers(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listAcquirers(paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Acquirer",
            description = "Create a new card acquirer record."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card acquirer created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardAcquirerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid card acquirer data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardAcquirerDTO>> createAcquirer(
            @Parameter(description = "Data for the new card acquirer", required = true,
                    schema = @Schema(implementation = CardAcquirerDTO.class))
            @RequestBody CardAcquirerDTO acquirerDTO
    ) {
        return service.createAcquirer(acquirerDTO)
                .map(createdAcquirer -> ResponseEntity.status(201).body(createdAcquirer))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Acquirer by ID",
            description = "Retrieve a specific card acquirer record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card acquirer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardAcquirerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card acquirer not found",
                    content = @Content)
    })
    @GetMapping(value = "/{acquirerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardAcquirerDTO>> getAcquirer(
            @Parameter(description = "Unique identifier of the card acquirer", required = true)
            @PathVariable Long acquirerId
    ) {
        return service.getAcquirer(acquirerId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Acquirer",
            description = "Update an existing card acquirer record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card acquirer updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardAcquirerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card acquirer not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid card acquirer data provided",
                    content = @Content)
    })
    @PutMapping(value = "/{acquirerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardAcquirerDTO>> updateAcquirer(
            @Parameter(description = "Unique identifier of the card acquirer to update", required = true)
            @PathVariable Long acquirerId,

            @Parameter(description = "Updated data for the card acquirer", required = true,
                    schema = @Schema(implementation = CardAcquirerDTO.class))
            @RequestBody CardAcquirerDTO acquirerDTO
    ) {
        return service.updateAcquirer(acquirerId, acquirerDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Acquirer",
            description = "Delete a card acquirer record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Card acquirer deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Card acquirer not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{acquirerId}")
    public Mono<ResponseEntity<Void>> deleteAcquirer(
            @Parameter(description = "Unique identifier of the card acquirer to delete", required = true)
            @PathVariable Long acquirerId
    ) {
        return service.deleteAcquirer(acquirerId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
