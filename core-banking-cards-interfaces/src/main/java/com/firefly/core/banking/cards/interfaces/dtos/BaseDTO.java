package com.firefly.core.banking.cards.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public abstract class BaseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @PastOrPresent(message = "Creation date cannot be in the future")
    private LocalDateTime dateCreated;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @PastOrPresent(message = "Update date cannot be in the future")
    private LocalDateTime dateUpdated;
}