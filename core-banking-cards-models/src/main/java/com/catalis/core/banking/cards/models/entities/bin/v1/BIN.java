package com.catalis.core.banking.cards.models.entities.bin.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Bank Identification Number (BIN) entity.
 * The BIN is the first 6 or 8 digits of a payment card number (PAN) that identifies the card issuer.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("bin")
public class BIN extends BaseEntity {

    @Id
    @Column("bin_id")
    private Long binId;

    @Column("bin_number")
    private String binNumber;

    @Column("bin_length")
    private Integer binLength;

    @Column("issuer_id")
    private Long issuerId;

    @Column("card_network_id")
    private Long cardNetworkId;

    @Column("card_type_id")
    private Long cardTypeId;

    @Column("country_code")
    private String countryCode;

    @Column("currency_code")
    private String currencyCode;

    @Column("is_active")
    private Boolean isActive;

    @Column("description")
    private String description;
}