package com.catalis.core.banking.cards.models.entities.configuration.v1;

import com.catalis.core.banking.cards.interfaces.enums.configuration.v1.ConfigTypeEnum;
import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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

    @Column("config_type")
    private ConfigTypeEnum configType;

    @Column("config_value")
    private boolean configValue;
}
