package com.catalis.core.banking.cards.web.controllers.dispute.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.dispute.v1.CardDisputeServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.dispute.v1.CardDisputeDTO;
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

@Tag(name = "Card Disputes", description = "APIs for managing dispute records associated with a specific card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/disputes")
public class CardDisputeController {

    @Autowired
    private CardDisputeServiceImpl service;

    @Operation(
            summary = "List Card Disputes",
            description = "Retrieve a paginated list of dispute records associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved card disputes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No dispute records found for the specified card",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardDisputeDTO>>> getAllDisputes(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listDisputes(cardId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Dispute",
            description = "Create a new dispute record for the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dispute record created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDisputeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid dispute data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardDisputeDTO>> createDispute(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Data for the new card dispute record", required = true,
                    schema = @Schema(implementation = CardDisputeDTO.class))
            @RequestBody CardDisputeDTO disputeDTO
    ) {
        return service.createDispute(cardId, disputeDTO)
                .map(createdDispute -> ResponseEntity.status(201).body(createdDispute))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Dispute",
            description = "Retrieve a specific dispute record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the dispute record",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDisputeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Dispute record not found",
                    content = @Content)
    })
    @GetMapping(value = "/{disputeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardDisputeDTO>> getDispute(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the dispute record", required = true)
            @PathVariable Long disputeId
    ) {
        return service.getDispute(cardId, disputeId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Dispute",
            description = "Update an existing dispute record associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dispute record updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardDisputeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Dispute record not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid dispute data provided",
                    content = @Content)
    })
    @PutMapping(value = "/{disputeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardDisputeDTO>> updateDispute(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the dispute record to update", required = true)
            @PathVariable Long disputeId,

            @Parameter(description = "Updated data for the dispute record", required = true,
                    schema = @Schema(implementation = CardDisputeDTO.class))
            @RequestBody CardDisputeDTO disputeDTO
    ) {
        return service.updateDispute(cardId, disputeId, disputeDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Dispute",
            description = "Delete a dispute record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Dispute record deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Dispute record not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{disputeId}")
    public Mono<ResponseEntity<Void>> deleteDispute(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the dispute record to delete", required = true)
            @PathVariable Long disputeId
    ) {
        return service.deleteDispute(cardId, disputeId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
