package com.catalis.core.banking.cards.web.controllers.security.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.web.error.models.ErrorResponse;
import com.catalis.core.banking.cards.core.services.security.v1.CardSecurityCreateService;
import com.catalis.core.banking.cards.core.services.security.v1.CardSecurityDeleteService;
import com.catalis.core.banking.cards.core.services.security.v1.CardSecurityGetService;
import com.catalis.core.banking.cards.core.services.security.v1.CardSecurityUpdateService;
import com.catalis.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
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

@Tag(name = "Card Security", description = "Manage Security Settings for a specific Card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/security")
public class CardSecurityController {

    @Autowired
    private CardSecurityCreateService createService;

    @Autowired
    private CardSecurityGetService getService;

    @Autowired
    private CardSecurityUpdateService updateService;

    @Autowired
    private CardSecurityDeleteService deleteService;


    @Operation(
            summary = "Create CardSecurity",
            description = "Creates new security settings for a Card."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "CardSecurity successfully created",
                    content = @Content(schema = @Schema(implementation = CardSecurityDTO.class))
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
    public Mono<ResponseEntity<CardSecurityDTO>> createCardSecurity(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "dto", description = "Details of the CardSecurity to create", required = true)
            @RequestBody CardSecurityDTO dto
    ) {
        dto.setCardId(cardId);
        return createService.createCardSecurity(dto)
                .map(createdSecurity -> ResponseEntity.status(HttpStatus.CREATED).body(createdSecurity));
    }


    @Operation(
            summary = "Get CardSecurity by Card ID (paginated)",
            description = "Retrieve all security settings for a Card, paginated."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paginated list of CardSecurity settings returned",
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
    public Mono<ResponseEntity<PaginationResponse<CardSecurityDTO>>> getCardSecurityByCardId(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "paginationRequest", description = "Pagination parameters (pageNumber, pageSize, sortBy, sortDirection)")
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return getService.getCardSecurityByCardId(cardId, paginationRequest)
                .map(ResponseEntity::ok);
    }


    @Operation(
            summary = "Get a single CardSecurity by ID",
            description = "Retrieve a security setting by its unique identifier."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CardSecurity found and returned",
                    content = @Content(schema = @Schema(implementation = CardSecurityDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardSecurity not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{cardSecurityId}")
    public Mono<ResponseEntity<CardSecurityDTO>> getCardSecurityById(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "cardSecurityId", description = "Unique identifier of the CardSecurity setting", required = true)
            @PathVariable(name = "cardSecurityId") Long cardSecurityId
    ) {
        return getService.getCardSecurityById(cardSecurityId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update CardSecurity",
            description = "Updates an existing CardSecurity resource."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CardSecurity successfully updated",
                    content = @Content(schema = @Schema(implementation = CardSecurityDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardSecurity not found",
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
    @PutMapping("/{cardSecurityId}")
    public Mono<ResponseEntity<CardSecurityDTO>> updateCardSecurity(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "cardSecurityId", description = "Unique identifier of the CardSecurity to update", required = true)
            @PathVariable(name = "cardSecurityId") Long cardSecurityId,
            @Parameter(name = "dto", description = "Updated details for the CardSecurity", required = true)
            @RequestBody CardSecurityDTO dto
    ) {
        dto.setCardId(cardId);
        return updateService.updateCardSecurity(cardSecurityId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete CardSecurity",
            description = "Removes a CardSecurity resource by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "CardSecurity successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardSecurity not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{cardSecurityId}")
    public Mono<ResponseEntity<Void>> deleteCardSecurity(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "cardSecurityId", description = "Unique identifier of the CardSecurity to delete", required = true)
            @PathVariable(name = "cardSecurityId") Long cardSecurityId
    ) {
        return deleteService.deleteCardSecurity(cardSecurityId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}