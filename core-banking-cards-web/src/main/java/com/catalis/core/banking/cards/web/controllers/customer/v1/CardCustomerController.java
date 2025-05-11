package com.catalis.core.banking.cards.web.controllers.customer.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.customer.v1.CardCustomerServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.customer.v1.CardCustomerDTO;
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

@Tag(name = "Card Customers", description = "APIs for managing card customer records")
@RestController
@RequestMapping("/api/v1/card-customers")
public class CardCustomerController {

    @Autowired
    private CardCustomerServiceImpl service;

    @Operation(
            summary = "List Card Customers",
            description = "Retrieve a paginated list of all card customer records."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved card customers",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No card customer records found",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardCustomerDTO>>> getAllCustomers(
            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listCustomers(paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Customer",
            description = "Create a new card customer record."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Card customer created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardCustomerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid card customer data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardCustomerDTO>> createCustomer(
            @Parameter(description = "Data for the new card customer", required = true,
                    schema = @Schema(implementation = CardCustomerDTO.class))
            @RequestBody CardCustomerDTO customerDTO
    ) {
        return service.createCustomer(customerDTO)
                .map(createdCustomer -> ResponseEntity.status(201).body(createdCustomer))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Customer by ID",
            description = "Retrieve a specific card customer record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the card customer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardCustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card customer not found",
                    content = @Content)
    })
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardCustomerDTO>> getCustomer(
            @Parameter(description = "Unique identifier of the card customer", required = true)
            @PathVariable Long customerId
    ) {
        return service.getCustomer(customerId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Customer",
            description = "Update an existing card customer record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Card customer updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardCustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Card customer not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid card customer data provided",
                    content = @Content)
    })
    @PutMapping(value = "/{customerId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardCustomerDTO>> updateCustomer(
            @Parameter(description = "Unique identifier of the card customer to update", required = true)
            @PathVariable Long customerId,

            @Parameter(description = "Updated data for the card customer", required = true,
                    schema = @Schema(implementation = CardCustomerDTO.class))
            @RequestBody CardCustomerDTO customerDTO
    ) {
        return service.updateCustomer(customerId, customerDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Customer",
            description = "Delete a card customer record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Card customer deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Card customer not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{customerId}")
    public Mono<ResponseEntity<Void>> deleteCustomer(
            @Parameter(description = "Unique identifier of the card customer to delete", required = true)
            @PathVariable Long customerId
    ) {
        return service.deleteCustomer(customerId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
