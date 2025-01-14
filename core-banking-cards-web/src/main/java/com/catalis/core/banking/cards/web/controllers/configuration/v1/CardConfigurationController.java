package com.catalis.core.banking.cards.web.controllers;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.configuration.v1.CardConfigurationServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
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

@Tag(name = "Card Configurations", description = "APIs for managing configuration records associated with a specific card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/configurations")
public class CardConfigurationController {

    @Autowired
    private CardConfigurationServiceImpl service;

    @Operation(
            summary = "List Card Configurations",
            description = "Retrieve a paginated list of configuration records associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card configurations",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No card configurations found for the specified card",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardConfigurationDTO>>> getAllConfigurations(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listConfigurations(cardId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Configuration",
            description = "Create a new configuration record and associate it with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card configuration created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardConfigurationDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid configuration data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardConfigurationDTO>> createConfiguration(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Data for the new card configuration record", required = true,
                    schema = @Schema(implementation = CardConfigurationDTO.class))
            @RequestBody CardConfigurationDTO configDTO
    ) {
        return service.createConfiguration(cardId, configDTO)
                .map(createdConfig -> ResponseEntity.status(201).body(createdConfig))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Configuration by ID",
            description = "Retrieve a specific card configuration record by its unique identifier, ensuring it belongs to the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card configuration",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardConfigurationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card configuration not found",
                    content = @Content)
    })
    @GetMapping(value = "/{configId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardConfigurationDTO>> getConfiguration(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the card configuration record", required = true)
            @PathVariable Long configId
    ) {
        return service.getConfiguration(cardId, configId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Configuration",
            description = "Update an existing configuration record associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card configuration updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardConfigurationDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card configuration record not found",
                    content = @Content)
    })
    @PutMapping(value = "/{configId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardConfigurationDTO>> updateConfiguration(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the card configuration record to update", required = true)
            @PathVariable Long configId,

            @Parameter(description = "Updated configuration data", required = true,
                    schema = @Schema(implementation = CardConfigurationDTO.class))
            @RequestBody CardConfigurationDTO configDTO
    ) {
        return service.updateConfiguration(cardId, configId, configDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Configuration",
            description = "Remove an existing card configuration record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Card configuration record deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Card configuration record not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{configId}")
    public Mono<ResponseEntity<Void>> deleteConfiguration(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the card configuration record to delete", required = true)
            @PathVariable Long configId
    ) {
        return service.deleteConfiguration(cardId, configId)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}