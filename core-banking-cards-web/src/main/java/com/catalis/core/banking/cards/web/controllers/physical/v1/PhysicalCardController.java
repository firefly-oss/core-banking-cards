package com.catalis.core.banking.cards.web.controllers.physical.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.web.error.models.ErrorResponse;
import com.catalis.core.banking.cards.core.services.physical.v1.PhysicalCardCreateService;
import com.catalis.core.banking.cards.core.services.physical.v1.PhysicalCardDeleteService;
import com.catalis.core.banking.cards.core.services.physical.v1.PhysicalCardGetService;
import com.catalis.core.banking.cards.core.services.physical.v1.PhysicalCardUpdateService;
import com.catalis.core.banking.cards.interfaces.dtos.physical.v1.PhysicalCardDTO;
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

@Tag(name = "Physical Cards", description = "Manage Physical Cards for a specific Card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/physical-cards")
public class PhysicalCardController {

    @Autowired
    private PhysicalCardCreateService createService;

    @Autowired
    private PhysicalCardGetService getService;

    @Autowired
    private PhysicalCardUpdateService updateService;

    @Autowired
    private PhysicalCardDeleteService deleteService;

    @Operation(
            summary = "Create a PhysicalCard",
            description = "Creates a new PhysicalCard under a specific Card."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "PhysicalCard successfully created",
                    content = @Content(schema = @Schema(implementation = PhysicalCardDTO.class))
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
    public Mono<ResponseEntity<PhysicalCardDTO>> createPhysicalCard(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "dto", description = "Details of the PhysicalCard to create", required = true)
            @RequestBody PhysicalCardDTO dto
    ) {
        dto.setCardId(cardId);
        return createService.createPhysicalCard(dto)
                .map(createdCard -> ResponseEntity.status(HttpStatus.CREATED).body(createdCard));
    }

    @Operation(
            summary = "Get PhysicalCards by Card ID (paginated)",
            description = "Retrieve all PhysicalCards for a specific Card using pagination."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paginated list of PhysicalCards returned",
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
    public Mono<ResponseEntity<PaginationResponse<PhysicalCardDTO>>> getPhysicalCardsByCardId(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "paginationRequest", description = "Pagination parameters (pageNumber, pageSize, sortBy, sortDirection)")
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return getService.getPhysicalCardByCardId(cardId, paginationRequest)
                .map(ResponseEntity::ok);
    }

    @Operation(
            summary = "Get a PhysicalCard by ID",
            description = "Retrieve details of a specific PhysicalCard."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "PhysicalCard found and returned",
                    content = @Content(schema = @Schema(implementation = PhysicalCardDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "PhysicalCard not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{physicalCardId}")
    public Mono<ResponseEntity<PhysicalCardDTO>> getPhysicalCardById(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "physicalCardId", description = "Unique identifier of the PhysicalCard", required = true)
            @PathVariable(name = "physicalCardId") Long physicalCardId
    ) {
        return getService.getPhysicalCardById(physicalCardId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update a PhysicalCard",
            description = "Updates an existing PhysicalCard resource."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "PhysicalCard successfully updated",
                    content = @Content(schema = @Schema(implementation = PhysicalCardDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "PhysicalCard not found",
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
    @PutMapping("/{physicalCardId}")
    public Mono<ResponseEntity<PhysicalCardDTO>> updatePhysicalCard(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "physicalCardId", description = "Unique identifier of the PhysicalCard to update", required = true)
            @PathVariable(name = "physicalCardId") Long physicalCardId,
            @Parameter(name = "dto", description = "Updated details for the PhysicalCard", required = true)
            @RequestBody PhysicalCardDTO dto
    ) {
        dto.setCardId(cardId);
        return updateService.updatePhysicalCard(physicalCardId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a PhysicalCard",
            description = "Removes a PhysicalCard resource by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "PhysicalCard successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "PhysicalCard not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{physicalCardId}")
    public Mono<ResponseEntity<Void>> deletePhysicalCard(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "physicalCardId", description = "Unique identifier of the PhysicalCard to delete", required = true)
            @PathVariable(name = "physicalCardId") Long physicalCardId
    ) {
        return deleteService.deletePhysicalCard(physicalCardId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}