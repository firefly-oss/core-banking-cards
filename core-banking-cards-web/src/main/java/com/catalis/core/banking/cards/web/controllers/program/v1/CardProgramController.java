package com.catalis.core.banking.cards.web.controllers.program.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.program.v1.CardProgramServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.program.v1.CardProgramDTO;
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

@Tag(name = "Card Programs", description = "APIs for managing card program records")
@RestController
@RequestMapping("/api/v1/programs")
public class CardProgramController {

    @Autowired
    private CardProgramServiceImpl service;

    @Operation(
            summary = "List Card Programs",
            description = "Retrieve a paginated list of all card program records.\n\n" +
                    "Card programs define the rules, configurations, and features for a group of cards. " +
                    "They specify parameters such as card limits, supported features, and eligibility criteria. " +
                    "This endpoint supports filtering and sorting capabilities."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved card programs",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No card program records found",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardProgramDTO>>> getAllPrograms(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listPrograms(paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Program",
            description = "Create a new card program record.\n\n" +
                    "Card programs are the foundation for issuing cards to customers. Each program defines " +
                    "the characteristics, limits, and features of cards issued under it. Programs are typically " +
                    "associated with specific issuers, BINs, card types, and networks."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card program created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardProgramDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid card program data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardProgramDTO>> createProgram(
            @Parameter(description = "Data for the new card program", required = true,
                    schema = @Schema(implementation = CardProgramDTO.class))
            @RequestBody CardProgramDTO programDTO
    ) {
        return service.createProgram(programDTO)
                .map(createdProgram -> ResponseEntity.status(201).body(createdProgram))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Program by ID",
            description = "Retrieve a specific card program record by its unique identifier.\n\n" +
                    "This endpoint returns detailed information about a card program, including its configuration, " +
                    "limits, supported features, and associations with issuers, BINs, and card networks."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card program",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardProgramDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card program not found",
                    content = @Content)
    })
    @GetMapping(value = "/{programId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardProgramDTO>> getProgram(
            @Parameter(description = "Unique identifier of the card program", required = true)
            @PathVariable Long programId
    ) {
        return service.getProgram(programId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Program",
            description = "Update an existing card program record by its unique identifier.\n\n" +
                    "This endpoint allows modification of card program attributes such as limits, supported features, " +
                    "and activation status. Changes to a card program may affect new cards issued under the program, " +
                    "but typically do not automatically apply to existing cards unless explicitly configured."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card program updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardProgramDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card program not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid card program data provided",
                    content = @Content)
    })
    @PutMapping(value = "/{programId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardProgramDTO>> updateProgram(
            @Parameter(description = "Unique identifier of the card program to update", required = true)
            @PathVariable Long programId,

            @Parameter(description = "Updated data for the card program", required = true,
                    schema = @Schema(implementation = CardProgramDTO.class))
            @RequestBody CardProgramDTO programDTO
    ) {
        return service.updateProgram(programId, programDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Program",
            description = "Delete a card program record by its unique identifier.\n\n" +
                    "This endpoint permanently removes a card program from the system. This operation should be used " +
                    "with caution, especially if there are cards already issued under this program. Consider deactivating " +
                    "the program instead of deleting it if it has been used in production."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Card program deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Card program not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{programId}")
    public Mono<ResponseEntity<Void>> deleteProgram(
            @Parameter(description = "Unique identifier of the card program to delete", required = true)
            @PathVariable Long programId
    ) {
        return service.deleteProgram(programId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
