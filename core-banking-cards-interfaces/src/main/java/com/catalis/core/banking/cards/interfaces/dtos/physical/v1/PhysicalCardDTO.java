package com.catalis.core.banking.cards.interfaces.dtos.physical.v1;

import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Physical Card.
 * Contains the essential information about a Physical Card that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PhysicalCardDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long physicalCardId;

    @FilterableId
    private Long cardId;

    private String embossedName;

    private String plasticId;

    @FilterableId
    private Long designId;

    private Boolean isContactless;

    private Boolean isChip;

    private Boolean isMagstripe;

    private String manufacturingStatus;

    private LocalDateTime manufacturingDate;

    private String shippingAddress;

    private String shippingCity;

    private String shippingState;

    private String shippingCountry;

    private String shippingPostalCode;

    private String shippingMethod;

    private String shippingTrackingNumber;

    private String shippingCarrier;

    private LocalDateTime shipmentDate;

    private LocalDateTime estimatedDeliveryDate;

    private LocalDateTime actualDeliveryDate;

    private String activationMethod;

    private LocalDateTime activationDate;

    private Boolean isActivated;

    private String replacementReason;

    @FilterableId
    private Long previousCardId;

    private String notes;
}
