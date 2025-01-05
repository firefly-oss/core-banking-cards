package com.catalis.core.banking.cards.web.controllers.configuration.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.web.error.models.ErrorResponse;
import com.catalis.core.banking.cards.core.services.configuration.v1.CardConfigurationCreateService;
import com.catalis.core.banking.cards.core.services.configuration.v1.CardConfigurationDeleteService;
import com.catalis.core.banking.cards.core.services.configuration.v1.CardConfigurationGetService;
import com.catalis.core.banking.cards.core.services.configuration.v1.CardConfigurationUpdateService;
import com.catalis.core.banking.cards.interfaces.dtos.configuration.v1.CardConfigurationDTO;
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

@Tag(name = "Card Configurations", description = "Manage configuration settings for a specific Card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/configurations")
public class CardConfigurationController {

    @Autowired
    private CardConfigurationCreateService createService;

    @Autowired
    private CardConfigurationGetService getService;

    @Autowired
    private CardConfigurationUpdateService updateService;

    @Autowired
    private CardConfigurationDeleteService deleteService;


    @Operation(
            summary = "Create CardConfiguration",
            description = "Creates new configuration settings for a Card."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "CardConfiguration successfully created",
                    content = @Content(schema = @Schema(implementation = CardConfigurationDTO.class))
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
    public Mono<ResponseEntity<CardConfigurationDTO>> createCardConfiguration(
            @Parameter(
                    name = "cardId",
                    description = "Unique identifier of the parent Card",
                    required = true
            )
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(
                    name = "dto",
                    description = "Details of the CardConfiguration to create",
                    required = true
            )
            @RequestBody CardConfigurationDTO dto
    ) {
        dto.setCardId(cardId);
        return createService.createCardConfiguration(dto)
                .map(createdConfig -> ResponseEntity.status(HttpStatus.CREATED).body(createdConfig));
    }


    @Operation(
            summary = "Get CardConfigurations by Card ID (paginated)",
            description = "Retrieve all configuration settings for a Card, using pagination."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paginated list of CardConfigurations returned",
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
    public Mono<ResponseEntity<PaginationResponse<CardConfigurationDTO>>> getCardConfigurationsByCardId(
            @Parameter(
                    name = "cardId",
                    description = "Unique identifier of the parent Card",
                    required = true
            )
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(
                    name = "paginationRequest",
                    description = "Pagination parameters (pageNumber, pageSize, sortBy, sortDirection).",
                    required = false
            )
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return getService.getCardConfigurationsByCardId(cardId, paginationRequest)
                .map(ResponseEntity::ok);
    }


    @Operation(
            summary = "Get a CardConfiguration by ID",
            description = "Retrieve a specific configuration setting."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CardConfiguration found and returned",
                    content = @Content(schema = @Schema(implementation = CardConfigurationDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardConfiguration not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{cardConfigurationId}")
    public Mono<ResponseEntity<CardConfigurationDTO>> getCardConfigurationById(
            @Parameter(
                    name = "cardId",
                    description = "Unique identifier of the parent Card",
                    required = true
            )
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(
                    name = "cardConfigurationId",
                    description = "Unique identifier of the CardConfiguration to retrieve",
                    required = true
            )
            @PathVariable(name = "cardConfigurationId") Long cardConfigurationId
    ) {
        return getService.getCardConfigurationById(cardConfigurationId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Update CardConfiguration",
            description = "Updates an existing CardConfiguration resource."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "CardConfiguration successfully updated",
                    content = @Content(schema = @Schema(implementation = CardConfigurationDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardConfiguration not found",
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
    @PutMapping("/{cardConfigurationId}")
    public Mono<ResponseEntity<CardConfigurationDTO>> updateCardConfiguration(
            @Parameter(
                    name = "cardId",
                    description = "Unique identifier of the parent Card",
                    required = true
            )
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(
                    name = "cardConfigurationId",
                    description = "Unique identifier of the CardConfiguration to update",
                    required = true
            )
            @PathVariable(name = "cardConfigurationId") Long cardConfigurationId,
            @Parameter(
                    name = "dto",
                    description = "Updated details for the CardConfiguration",
                    required = true
            )
            @RequestBody CardConfigurationDTO dto
    ) {
        dto.setCardId(cardId);
        return updateService.updateCardConfiguration(cardConfigurationId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Delete CardConfiguration",
            description = "Removes a CardConfiguration resource by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "CardConfiguration successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "CardConfiguration not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{cardConfigurationId}")
    public Mono<ResponseEntity<Void>> deleteCardConfiguration(
            @Parameter(
                    name = "cardId",
                    description = "Unique identifier of the parent Card",
                    required = true
            )
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(
                    name = "cardConfigurationId",
                    description = "Unique identifier of the CardConfiguration to delete",
                    required = true
            )
            @PathVariable(name = "cardConfigurationId") Long cardConfigurationId
    ) {
        return deleteService.deleteCardConfiguration(cardConfigurationId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
