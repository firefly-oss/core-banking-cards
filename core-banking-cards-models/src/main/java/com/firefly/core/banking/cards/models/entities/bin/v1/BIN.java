package com.firefly.core.banking.cards.models.entities.bin.v1;

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
    private UUID binId;

    @Column("bin_number")
    private String binNumber;

    @Column("bin_length")
    private Integer binLength;

    @Column("issuer_id")
    private UUID issuerId;

    @Column("card_network_id")
    private UUID cardNetworkId;

    @Column("card_type_id")
    private UUID cardTypeId;

    @Column("country_code")
    private String countryCode;

    @Column("currency_code")
    private String currencyCode;

    @Column("is_active")
    private Boolean isActive;

    @Column("description")
    private String description;
}