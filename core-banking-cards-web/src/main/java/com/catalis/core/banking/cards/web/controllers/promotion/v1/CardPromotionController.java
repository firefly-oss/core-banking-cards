package com.catalis.core.banking.cards.web.controllers.promotion.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.promotion.v1.CardPromotionServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.promotion.v1.CardPromotionDTO;
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

@Tag(name = "Card Promotions", description = "APIs for managing promotion records associated with a specific card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/promotions")
public class CardPromotionController {

    @Autowired
    private CardPromotionServiceImpl service;

    @Operation(
            summary = "List Card Promotions",
            description = "Retrieve a paginated list of promotion records associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved card promotions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No promotion records found for the specified card",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardPromotionDTO>>> getAllPromotions(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listPromotions(cardId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Promotion",
            description = "Create a new promotion record for the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Promotion record created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardPromotionDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid promotion data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardPromotionDTO>> createPromotion(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Data for the new card promotion record", required = true,
                    schema = @Schema(implementation = CardPromotionDTO.class))
            @RequestBody CardPromotionDTO promotionDTO
    ) {
        return service.createPromotion(cardId, promotionDTO)
                .map(createdPromotion -> ResponseEntity.status(201).body(createdPromotion))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Promotion",
            description = "Retrieve a specific promotion record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the promotion record",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardPromotionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Promotion record not found",
                    content = @Content)
    })
    @GetMapping(value = "/{promotionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardPromotionDTO>> getPromotion(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the promotion record", required = true)
            @PathVariable Long promotionId
    ) {
        return service.getPromotion(cardId, promotionId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Promotion",
            description = "Update an existing promotion record associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Promotion record updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardPromotionDTO.class))),
            @ApiResponse(responseCode = "404", description = "Promotion record not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid promotion data provided",
                    content = @Content)
    })
    @PutMapping(value = "/{promotionId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardPromotionDTO>> updatePromotion(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the promotion record to update", required = true)
            @PathVariable Long promotionId,

            @Parameter(description = "Updated data for the promotion record", required = true,
                    schema = @Schema(implementation = CardPromotionDTO.class))
            @RequestBody CardPromotionDTO promotionDTO
    ) {
        return service.updatePromotion(cardId, promotionId, promotionDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Promotion",
            description = "Delete a promotion record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Promotion record deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Promotion record not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{promotionId}")
    public Mono<ResponseEntity<Void>> deletePromotion(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the promotion record to delete", required = true)
            @PathVariable Long promotionId
    ) {
        return service.deletePromotion(cardId, promotionId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
