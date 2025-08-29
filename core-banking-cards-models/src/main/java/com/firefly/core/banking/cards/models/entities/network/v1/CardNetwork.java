package com.firefly.core.banking.cards.models.entities.network.v1;

import com.firefly.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Card Network entity.
 * Represents a payment card network like Visa, Mastercard, American Express, etc.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_network")
public class CardNetwork extends BaseEntity {

    @Id
    @Column("card_network_id")
    private Long cardNetworkId;

    @Column("network_name")
    private String networkName;

    @Column("network_code")
    private String networkCode;

    @Column("network_logo_url")
    private String networkLogoUrl;

    @Column("support_contact")
    private String supportContact;

    @Column("api_endpoint")
    private String apiEndpoint;

    @Column("api_key")
    private String apiKey;

    @Column("api_secret")
    private String apiSecret;

    @Column("is_active")
    private Boolean isActive;

    @Column("description")
    private String description;
}