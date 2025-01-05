package com.catalis.core.banking.cards.models.entities.physical.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

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

    @Column("card_number")
    private String cardNumber;

    @Column("card_cvv")
    private String cardCvv;

    @Column("card_holder_name")
    private String cardHolderName;

    @Column("card_network")
    private String cardNetwork;

    @Column("card_design")
    private String cardDesign;

    @Column("shipment_date")
    private LocalDateTime shipmentDate;

    @Column("delivery_date")
    private LocalDateTime deliveryDate;
}