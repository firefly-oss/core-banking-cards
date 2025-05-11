package com.catalis.core.banking.cards.web.controllers.bin.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.bin.v1.BINServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.bin.v1.BINDTO;
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

@Tag(name = "BINs", description = "APIs for managing Bank Identification Number (BIN) records")
@RestController
@RequestMapping("/api/v1/bins")
public class BINController {

    @Autowired
    private BINServiceImpl service;

    @Operation(
            summary = "List BINs",
            description = "Retrieve a paginated list of all BIN records."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved BINs",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No BIN records found",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<BINDTO>>> getAllBINs(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listBINs(paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create BIN",
            description = "Create a new BIN record."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "BIN created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BINDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid BIN data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BINDTO>> createBIN(
            @Parameter(description = "Data for the new BIN", required = true,
                    schema = @Schema(implementation = BINDTO.class))
            @RequestBody BINDTO binDTO
    ) {
        return service.createBIN(binDTO)
                .map(createdBIN -> ResponseEntity.status(201).body(createdBIN))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get BIN by ID",
            description = "Retrieve a specific BIN record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the BIN",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BINDTO.class))),
            @ApiResponse(responseCode = "404", description = "BIN not found",
                    content = @Content)
    })
    @GetMapping(value = "/{binId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BINDTO>> getBIN(
            @Parameter(description = "Unique identifier of the BIN", required = true)
            @PathVariable Long binId
    ) {
        return service.getBIN(binId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Get BIN by Number",
            description = "Retrieve a specific BIN record by its BIN number."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the BIN",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BINDTO.class))),
            @ApiResponse(responseCode = "404", description = "BIN not found",
                    content = @Content)
    })
    @GetMapping(value = "/number/{binNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BINDTO>> getBINByNumber(
            @Parameter(description = "BIN number", required = true)
            @PathVariable String binNumber
    ) {
        return service.getBINByNumber(binNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update BIN",
            description = "Update an existing BIN record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BIN updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BINDTO.class))),
            @ApiResponse(responseCode = "404", description = "BIN not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid BIN data provided",
                    content = @Content)
    })
    @PutMapping(value = "/{binId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BINDTO>> updateBIN(
            @Parameter(description = "Unique identifier of the BIN to update", required = true)
            @PathVariable Long binId,

            @Parameter(description = "Updated data for the BIN", required = true,
                    schema = @Schema(implementation = BINDTO.class))
            @RequestBody BINDTO binDTO
    ) {
        return service.updateBIN(binId, binDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete BIN",
            description = "Delete a BIN record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "BIN deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "BIN not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{binId}")
    public Mono<ResponseEntity<Void>> deleteBIN(
            @Parameter(description = "Unique identifier of the BIN to delete", required = true)
            @PathVariable Long binId
    ) {
        return service.deleteBIN(binId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
