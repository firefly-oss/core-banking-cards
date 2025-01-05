package com.catalis.core.banking.cards.web.controllers.provider.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.web.error.models.ErrorResponse;
import com.catalis.core.banking.cards.core.services.provider.v1.CardProviderCreateService;
import com.catalis.core.banking.cards.core.services.provider.v1.CardProviderDeleteService;
import com.catalis.core.banking.cards.core.services.provider.v1.CardProviderGetService;
import com.catalis.core.banking.cards.core.services.provider.v1.CardProviderUpdateService;
import com.catalis.core.banking.cards.interfaces.dtos.provider.v1.CardProviderDTO;
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

@Tag(name = "Card Providers", description = "Manage Providers for a specific Card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/providers")
public class CardProviderController {

    @Autowired
    private CardProviderCreateService createService;

    @Autowired
    private CardProviderGetService getService;

    @Autowired
    private CardProviderUpdateService updateService;

    @Autowired
    private CardProviderDeleteService deleteService;

    @Operation(
            summary = "Create a CardProvider",
            description = "Creates a new provider for a Card."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "CardProvider successfully created",
                    content = @Content(schema = @Schema(implementation = CardProviderDTO.class))
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
    public Mono<ResponseEntity<CardProviderDTO>> createCardProvider(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "dto", description = "Details of the CardProvider to create", required = true)
            @RequestBody CardProviderDTO dto
    ) {
        dto.setCardId(cardId);
        return createService.createCardProvider(dto)
                .map(createdProvider -> ResponseEntity.status(HttpStatus.CREATED).body(createdProvider));
    }

    @Operation(
            summary = "Get CardProviders by Card ID (paginated)",
            description = "Retrieve all providers for a Card in a paginated manner."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paginated list of CardProviders returned",
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
    public Mono<ResponseEntity<PaginationResponse<CardProviderDTO>>> getCardProvidersByCardId(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "paginationRequest", description = "Pagination parameters (pageNumber, pageSize, sortBy, sortDirection)")
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return getService.getCardProvidersByCardId(cardId, paginationRequest)
                .map(ResponseEntity::ok);
    }


    @Operation(
            summary = "Update a CardProvider",
            description = "Updates an existing CardProvider resource."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CardProvider successfully updated",
                    content = @Content(schema = @Schema(implementation = CardProviderDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardProvider not found",
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
    @PutMapping("/{cardProviderId}")
    public Mono<ResponseEntity<CardProviderDTO>> updateCardProvider(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "cardProviderId", description = "Unique identifier of the CardProvider to update", required = true)
            @PathVariable(name = "cardProviderId") Long cardProviderId,
            @Parameter(name = "dto", description = "New details for the CardProvider", required = true)
            @RequestBody CardProviderDTO dto
    ) {
        dto.setCardId(cardId);
        return updateService.updateCardProvider(cardProviderId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a CardProvider",
            description = "Removes a CardProvider resource by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "CardProvider successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardProvider not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{cardProviderId}")
    public Mono<ResponseEntity<Void>> deleteCardProvider(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "cardProviderId", description = "Unique identifier of the CardProvider to delete", required = true)
            @PathVariable(name = "cardProviderId") Long cardProviderId
    ) {
        return deleteService.deleteCardProvider(cardProviderId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}