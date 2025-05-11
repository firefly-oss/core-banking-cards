package com.catalis.core.banking.cards.models.entities.configuration.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Card Configuration entity.
 * Represents configuration settings for a payment card.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_configuration")
public class CardConfiguration extends BaseEntity {
    @Id
    @Column("card_configuration_id")
    private Long cardConfigurationId;

    @Column("card_id")
    private Long cardId;

    @Column("program_id")
    private Long programId;

    @Column("config_key")
    private String configKey;

    @Column("config_value")
    private String configValue;

    @Column("config_type")
    private String configType;

    @Column("is_active")
    private Boolean isActive;

    @Column("activation_date")
    private LocalDateTime activationDate;

    @Column("expiration_date")
    private LocalDateTime expirationDate;

    @Column("is_system_default")
    private Boolean isSystemDefault;

    @Column("is_program_default")
    private Boolean isProgramDefault;

    @Column("is_party_configurable")
    private Boolean isPartyConfigurable;

    @Column("allowed_values")
    private String allowedValues;

    @Column("min_value")
    private String minValue;

    @Column("max_value")
    private String maxValue;

    @Column("description")
    private String description;
}
