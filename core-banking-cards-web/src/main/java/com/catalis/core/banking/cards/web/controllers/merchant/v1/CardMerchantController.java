package com.catalis.core.banking.cards.web.controllers.merchant.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.merchant.v1.CardMerchantServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.merchant.v1.CardMerchantDTO;
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

@Tag(name = "Card Merchants", description = "APIs for managing card merchant records")
@RestController
@RequestMapping("/api/v1/merchants")
public class CardMerchantController {

    @Autowired
    private CardMerchantServiceImpl service;

    @Operation(
            summary = "List Card Merchants",
            description = "Retrieve a paginated list of all card merchant records.\n\n" +
                    "Card merchants represent businesses that accept card payments. This endpoint returns " +
                    "detailed information about merchants including their categories, risk ratings, and " +
                    "supported payment methods. This endpoint supports filtering and sorting capabilities."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved card merchants",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No card merchant records found",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardMerchantDTO>>> getAllMerchants(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listMerchants(paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Merchant",
            description = "Create a new card merchant record.\n\n" +
                    "This endpoint allows adding new merchants to the system. Merchants are essential for " +
                    "transaction processing and reporting. The merchant record includes details about the " +
                    "business, contact information, supported payment methods, and risk assessment data."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card merchant created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardMerchantDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid card merchant data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardMerchantDTO>> createMerchant(
            @Parameter(description = "Data for the new card merchant", required = true,
                    schema = @Schema(implementation = CardMerchantDTO.class))
            @RequestBody CardMerchantDTO merchantDTO
    ) {
        return service.createMerchant(merchantDTO)
                .map(createdMerchant -> ResponseEntity.status(201).body(createdMerchant))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Merchant by ID",
            description = "Retrieve a specific card merchant record by its unique identifier.\n\n" +
                    "This endpoint returns comprehensive information about a merchant, including its " +
                    "business details, category codes, risk assessment, and settlement information. " +
                    "This data is useful for transaction analysis and merchant management."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card merchant",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardMerchantDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card merchant not found",
                    content = @Content)
    })
    @GetMapping(value = "/{merchantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardMerchantDTO>> getMerchant(
            @Parameter(description = "Unique identifier of the card merchant", required = true)
            @PathVariable Long merchantId
    ) {
        return service.getMerchant(merchantId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Merchant",
            description = "Update an existing card merchant record by its unique identifier.\n\n" +
                    "This endpoint allows modification of merchant attributes such as contact information, " +
                    "business details, risk ratings, and settlement information. Keeping merchant information " +
                    "up-to-date is essential for accurate transaction processing and reporting."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card merchant updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardMerchantDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card merchant not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid card merchant data provided",
                    content = @Content)
    })
    @PutMapping(value = "/{merchantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardMerchantDTO>> updateMerchant(
            @Parameter(description = "Unique identifier of the card merchant to update", required = true)
            @PathVariable Long merchantId,

            @Parameter(description = "Updated data for the card merchant", required = true,
                    schema = @Schema(implementation = CardMerchantDTO.class))
            @RequestBody CardMerchantDTO merchantDTO
    ) {
        return service.updateMerchant(merchantId, merchantDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Merchant",
            description = "Delete a card merchant record by its unique identifier.\n\n" +
                    "This endpoint permanently removes a merchant from the system. This operation should be used " +
                    "with caution, especially if there are transactions associated with this merchant. Consider " +
                    "deactivating the merchant instead of deleting it if it has transaction history."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Card merchant deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Card merchant not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{merchantId}")
    public Mono<ResponseEntity<Void>> deleteMerchant(
            @Parameter(description = "Unique identifier of the card merchant to delete", required = true)
            @PathVariable Long merchantId
    ) {
        return service.deleteMerchant(merchantId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
