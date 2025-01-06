package com.catalis.core.banking.cards.web.controllers.limit.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.web.error.models.ErrorResponse;
import com.catalis.core.banking.cards.core.services.limit.v1.CardLimitCreateService;
import com.catalis.core.banking.cards.core.services.limit.v1.CardLimitDeleteService;
import com.catalis.core.banking.cards.core.services.limit.v1.CardLimitGetService;
import com.catalis.core.banking.cards.core.services.limit.v1.CardLimitUpdateService;
import com.catalis.core.banking.cards.interfaces.dtos.limit.v1.CardLimitDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Card Limits", description = "Manage spending or withdrawal limits for a specific Card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/limits")
public class CardLimitController {

    @Autowired
    private CardLimitCreateService createService;

    @Autowired
    private CardLimitGetService getService;

    @Autowired
    private CardLimitUpdateService updateService;

    @Autowired
    private CardLimitDeleteService deleteService;


    @Operation(
            summary = "Create CardLimit",
            description = "Creates a new limit setting for a Card."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "CardLimit successfully created",
                    content = @Content(schema = @Schema(implementation = CardLimitDTO.class))
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
    public Mono<ResponseEntity<CardLimitDTO>> createCardLimit(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "dto", description = "Details of the CardLimit to create", required = true)
            @RequestBody CardLimitDTO dto
    ) {
        dto.setCardId(cardId);
        return createService.createCardLimit(dto)
                .map(createdLimit -> ResponseEntity.status(HttpStatus.CREATED).body(createdLimit));
    }


    @Operation(
            summary = "Get CardLimits by Card ID (paginated)",
            description = "Retrieve all limit settings for a Card with pagination."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paginated list of CardLimits returned",
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
    @GetMapping("/")
    public Mono<ResponseEntity<PaginationResponse<CardLimitDTO>>> getCardLimitsByCardId(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "paginationRequest", description = "Pagination parameters (pageNumber, pageSize, sortBy, sortDirection)")
            @ParameterObject @ModelAttribute PaginationRequest paginationRequest
    ) {
        return getService.getCardLimitByCardId(cardId, paginationRequest)
                .map(ResponseEntity::ok);
    }


    @Operation(
            summary = "Get CardLimit by ID",
            description = "Retrieve a limit setting by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CardLimit found and returned",
                    content = @Content(schema = @Schema(implementation = CardLimitDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardLimit not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{cardLimitId}")
    public Mono<ResponseEntity<CardLimitDTO>> getCardLimitById(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "cardLimitId", description = "Unique identifier of the CardLimit to retrieve", required = true)
            @PathVariable(name = "cardLimitId") Long cardLimitId
    ) {
        return getService.getCardLimitById(cardLimitId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Update CardLimit",
            description = "Updates an existing limit setting."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CardLimit successfully updated",
                    content = @Content(schema = @Schema(implementation = CardLimitDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardLimit not found",
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
    @PutMapping("/{cardLimitId}")
    public Mono<ResponseEntity<CardLimitDTO>> updateCardLimit(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "cardLimitId", description = "Unique identifier of the CardLimit to update", required = true)
            @PathVariable(name = "cardLimitId") Long cardLimitId,
            @Parameter(name = "dto", description = "Updated details for the CardLimit", required = true)
            @RequestBody CardLimitDTO dto
    ) {
        dto.setCardId(cardId);
        return updateService.updateCardLimit(cardLimitId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Delete CardLimit",
            description = "Removes a CardLimit resource by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "CardLimit successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardLimit not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{cardLimitId}")
    public Mono<ResponseEntity<Void>> deleteCardLimit(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "cardLimitId", description = "Unique identifier of the CardLimit to delete", required = true)
            @PathVariable(name = "cardLimitId") Long cardLimitId
    ) {
        return deleteService.deleteCardLimit(cardLimitId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}