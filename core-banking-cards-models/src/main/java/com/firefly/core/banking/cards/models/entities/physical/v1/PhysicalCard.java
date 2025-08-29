package com.firefly.core.banking.cards.models.entities.physical.v1;

import com.firefly.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Physical Card entity.
 * Represents the physical aspects of a payment card.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("physical_card")
public class PhysicalCard extends BaseEntity {

    @Id
    @Column("physical_card_id")
    private Long physicalCardId;

    @Column("card_id")
    private Long cardId;

    @Column("embossed_name")
    private String embossedName;

    @Column("plastic_id")
    private String plasticId;

    @Column("design_id")
    private Long designId;

    @Column("is_contactless")
    private Boolean isContactless;

    @Column("is_chip")
    private Boolean isChip;

    @Column("is_magstripe")
    private Boolean isMagstripe;

    @Column("manufacturing_status")
    private String manufacturingStatus;

    @Column("manufacturing_date")
    private LocalDateTime manufacturingDate;

    @Column("shipping_address")
    private String shippingAddress;

    @Column("shipping_city")
    private String shippingCity;

    @Column("shipping_state")
    private String shippingState;

    @Column("shipping_country")
    private String shippingCountry;

    @Column("shipping_postal_code")
    private String shippingPostalCode;

    @Column("shipping_method")
    private String shippingMethod;

    @Column("shipping_tracking_number")
    private String shippingTrackingNumber;

    @Column("shipping_carrier")
    private String shippingCarrier;

    @Column("shipment_date")
    private LocalDateTime shipmentDate;

    @Column("estimated_delivery_date")
    private LocalDateTime estimatedDeliveryDate;

    @Column("actual_delivery_date")
    private LocalDateTime actualDeliveryDate;

    @Column("activation_method")
    private String activationMethod;

    @Column("activation_date")
    private LocalDateTime activationDate;

    @Column("is_activated")
    private Boolean isActivated;

    @Column("replacement_reason")
    private String replacementReason;

    @Column("previous_card_id")
    private Long previousCardId;

    @Column("notes")
    private String notes;
}
