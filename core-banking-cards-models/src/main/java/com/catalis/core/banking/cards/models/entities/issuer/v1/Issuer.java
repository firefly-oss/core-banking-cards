package com.catalis.core.banking.cards.models.entities.issuer.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Issuer entity.
 * Represents a financial institution that issues payment cards to consumers.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("issuer")
public class Issuer extends BaseEntity {

    @Id
    @Column("issuer_id")
    private Long issuerId;

    @Column("issuer_name")
    private String issuerName;

    @Column("issuer_code")
    private String issuerCode;

    @Column("country_code")
    private String countryCode;

    @Column("contact_email")
    private String contactEmail;

    @Column("contact_phone")
    private String contactPhone;

    @Column("is_active")
    private Boolean isActive;

    @Column("support_url")
    private String supportUrl;

    @Column("logo_url")
    private String logoUrl;

    @Column("description")
    private String description;
}