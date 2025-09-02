package com.firefly.core.banking.cards.models.entities.design.v1;

import com.firefly.core.banking.cards.models.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

/**
 * Card Design entity.
 * Represents a physical design template for payment cards.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_design")
public class CardDesign extends BaseEntity {

    @Id
    @Column("design_id")
    private UUID designId;

    @Column("design_name")
    private String designName;

    @Column("design_code")
    private String designCode;

    @Column("front_image_url")
    private String frontImageUrl;

    @Column("back_image_url")
    private String backImageUrl;

    @Column("card_type_id")
    private UUID cardTypeId;

    @Column("issuer_id")
    private UUID issuerId;

    @Column("card_network_id")
    private UUID cardNetworkId;

    @Column("is_customizable")
    private Boolean isCustomizable;

    @Column("is_default")
    private Boolean isDefault;

    @Column("is_active")
    private Boolean isActive;

    @Column("description")
    private String description;
}