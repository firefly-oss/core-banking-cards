package com.catalis.core.banking.cards.web.controllers.enrollment.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.banking.cards.core.services.enrollment.v1.CardEnrollmentServiceImpl;
import com.catalis.core.banking.cards.interfaces.dtos.enrollment.v1.CardEnrollmentDTO;
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

@Tag(name = "Card Enrollments", description = "APIs for managing enrollment records associated with a specific card")
@RestController
@RequestMapping("/api/v1/cards/{cardId}/enrollments")
public class CardEnrollmentController {

    @Autowired
    private CardEnrollmentServiceImpl service;

    @Operation(
            summary = "List Card Enrollments",
            description = "Retrieve a paginated list of enrollment records associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved card enrollments",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PaginationResponse.class))),
            @ApiResponse(responseCode = "404", description = "No enrollment records found for the specified card",
                    content = @Content)
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<PaginationResponse<CardEnrollmentDTO>>> getAllEnrollments(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @ParameterObject
            @ModelAttribute PaginationRequest paginationRequest
    ) {
        return service.listEnrollments(cardId, paginationRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Card Enrollment",
            description = "Create a new enrollment record for the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enrollment record created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardEnrollmentDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid enrollment data provided",
                    content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardEnrollmentDTO>> createEnrollment(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Data for the new card enrollment record", required = true,
                    schema = @Schema(implementation = CardEnrollmentDTO.class))
            @RequestBody CardEnrollmentDTO enrollmentDTO
    ) {
        return service.createEnrollment(cardId, enrollmentDTO)
                .map(createdEnrollment -> ResponseEntity.status(201).body(createdEnrollment))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get Card Enrollment",
            description = "Retrieve a specific enrollment record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the enrollment record",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardEnrollmentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Enrollment record not found",
                    content = @Content)
    })
    @GetMapping(value = "/{enrollmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardEnrollmentDTO>> getEnrollment(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the enrollment record", required = true)
            @PathVariable Long enrollmentId
    ) {
        return service.getEnrollment(cardId, enrollmentId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Update Card Enrollment",
            description = "Update an existing enrollment record associated with the specified card."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Enrollment record updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CardEnrollmentDTO.class))),
            @ApiResponse(responseCode = "404", description = "Enrollment record not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid enrollment data provided",
                    content = @Content)
    })
    @PutMapping(value = "/{enrollmentId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<CardEnrollmentDTO>> updateEnrollment(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the enrollment record to update", required = true)
            @PathVariable Long enrollmentId,

            @Parameter(description = "Updated data for the enrollment record", required = true,
                    schema = @Schema(implementation = CardEnrollmentDTO.class))
            @RequestBody CardEnrollmentDTO enrollmentDTO
    ) {
        return service.updateEnrollment(cardId, enrollmentId, enrollmentDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Delete Card Enrollment",
            description = "Delete an enrollment record by its unique identifier."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Enrollment record deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Enrollment record not found",
                    content = @Content)
    })
    @DeleteMapping(value = "/{enrollmentId}")
    public Mono<ResponseEntity<Void>> deleteEnrollment(
            @Parameter(description = "Unique identifier of the card", required = true)
            @PathVariable Long cardId,

            @Parameter(description = "Unique identifier of the enrollment record to delete", required = true)
            @PathVariable Long enrollmentId
    ) {
        return service.deleteEnrollment(cardId, enrollmentId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
