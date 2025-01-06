package com.catalis.core.banking.cards.web.controllers.virtual.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.web.error.models.ErrorResponse;
import com.catalis.core.banking.cards.core.services.virtual.v1.VirtualCardCreateService;
import com.catalis.core.banking.cards.core.services.virtual.v1.VirtualCardDeleteService;
import com.catalis.core.banking.cards.core.services.virtual.v1.VirtualCardGetService;
import com.catalis.core.banking.cards.core.services.virtual.v1.VirtualCardUpdateService;
import com.catalis.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
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

@Tag(name = "Virtual Cards", description = "Manage Virtual Cards for a specific Card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/virtual-cards")
public class VirtualCardController {

    @Autowired
    private VirtualCardCreateService createService;

    @Autowired
    private VirtualCardGetService getService;

    @Autowired
    private VirtualCardUpdateService updateService;

    @Autowired
    private VirtualCardDeleteService deleteService;


    @Operation(
            summary = "Create a Virtual Card",
            description = "Creates a new Virtual Card associated with a specific Card ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "VirtualCard successfully created",
                    content = @Content(schema = @Schema(implementation = VirtualCardDTO.class))
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
    public Mono<ResponseEntity<VirtualCardDTO>> createVirtualCard(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "virtualCardDTO", description = "Details for creating the VirtualCard", required = true)
            @RequestBody VirtualCardDTO virtualCardDTO
    ) {
        virtualCardDTO.setCardId(cardId);
        return createService.createVirtualCard(virtualCardDTO)
                .map(createdCard -> ResponseEntity.status(HttpStatus.CREATED).body(createdCard));
    }


    @Operation(
            summary = "Get Virtual Cards by Card ID (Paginated)",
            description = "Retrieve a paginated list of Virtual Cards associated with a specific Card ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paginated list of virtual cards returned",
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
    public Mono<ResponseEntity<PaginationResponse<VirtualCardDTO>>> getVirtualCardsByCardId(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "paginationRequest", description = "Pagination parameters (pageNumber, pageSize, sortBy, sortDirection)")
            @ParameterObject @ModelAttribute PaginationRequest paginationRequest
    ) {
        return getService.getVirtualCardsByCardId(cardId, paginationRequest)
                .map(ResponseEntity::ok);
    }


    @Operation(
            summary = "Get a Virtual Card by ID",
            description = "Retrieve the details of a specific Virtual Card using its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "VirtualCard found and returned",
                    content = @Content(schema = @Schema(implementation = VirtualCardDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "VirtualCard not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{virtualCardId}")
    public Mono<ResponseEntity<VirtualCardDTO>> getVirtualCardById(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "virtualCardId", description = "Unique identifier of the Virtual Card", required = true)
            @PathVariable(name = "virtualCardId") Long virtualCardId
    ) {
        return getService.getVirtualCardById(virtualCardId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update a Virtual Card",
            description = "Update the details of an existing Virtual Card."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "VirtualCard successfully updated",
                    content = @Content(schema = @Schema(implementation = VirtualCardDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "VirtualCard not found",
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
    @PutMapping("/{virtualCardId}")
    public Mono<ResponseEntity<VirtualCardDTO>> updateVirtualCard(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "virtualCardId", description = "Unique identifier of the Virtual Card to update", required = true)
            @PathVariable(name = "virtualCardId") Long virtualCardId,
            @Parameter(name = "dto", description = "Updated details of the Virtual Card", required = true)
            @RequestBody VirtualCardDTO dto
    ) {
        dto.setCardId(cardId);
        return updateService.updateVirtualCard(virtualCardId, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete a Virtual Card",
            description = "Delete a specific Virtual Card by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "VirtualCard successfully deleted"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "VirtualCard not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{virtualCardId}")
    public Mono<ResponseEntity<Void>> deleteVirtualCard(
            @Parameter(name = "cardId", description = "Unique identifier of the parent Card", required = true)
            @PathVariable(name = "cardId") Long cardId,
            @Parameter(name = "virtualCardId", description = "Unique identifier of the Virtual Card to delete", required = true)
            @PathVariable(name = "virtualCardId") Long virtualCardId
    ) {
        return deleteService.deleteVirtualCard(virtualCardId)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
