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
            description = "Retrieve a paginated list of all Bank Identification Number (BIN) records.\n\n" +
                    "BINs are the first 6 to 8 digits of a payment card number that identify the card issuer, " +
                    "card type, and other key attributes. This endpoint supports filtering and sorting capabilities."
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
            description = "Create a new Bank Identification Number (BIN) record.\n\n" +
                    "This endpoint allows financial institutions to register new BINs in the system. " +
                    "The BIN is associated with a specific issuer, card network, and card type, and is used to identify " +
                    "the origin of payment cards."
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
            description = "Retrieve a specific Bank Identification Number (BIN) record by its unique identifier.\n\n" +
                    "This endpoint returns detailed information about a BIN, including its associated issuer, " +
                    "card network, and card type. This information is essential for card issuance and transaction processing."
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
            description = "Retrieve a specific Bank Identification Number (BIN) record by its BIN number.\n\n" +
                    "This endpoint allows looking up BIN information using the actual BIN number (first 6-8 digits of a card), " +
                    "which is useful during transaction processing and card validation workflows."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the BIN",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BINDTO.class))),
            @ApiResponse(responseCode = "404", description = "BIN not found",
                    content = @Content)
    })
    @GetMapping(params = "number", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<BINDTO>> getBINByNumber(
            @Parameter(description = "BIN number", required = true)
            @RequestParam("number") String binNumber
    ) {
        return service.getBINByNumber(binNumber)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update BIN",
            description = "Update an existing Bank Identification Number (BIN) record by its unique identifier.\n\n" +
                    "This endpoint allows modification of BIN attributes such as associated card type, network, " +
                    "country code, currency code, and activation status. Note that the BIN number itself should " +
                    "typically not be changed as it is a standardized identifier."
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
            description = "Delete a Bank Identification Number (BIN) record by its unique identifier.\n\n" +
                    "This endpoint permanently removes a BIN from the system. This operation should be used with caution, " +
                    "especially if there are cards already issued with this BIN. Consider deactivating the BIN instead " +
                    "of deleting it if it has been used in production."
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
