package com.catalis.core.banking.cards.models.entities.security.v1;

import com.catalis.core.banking.cards.interfaces.enums.security.v1.SecurityFeatureEnum;
import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_security")
public class CardSecurity extends BaseEntity {
    @Id
    @Column("card_security_id")
    private Long cardSecurityId;

    @Column("card_id")
    private Long cardId;

    @Column("security_feature")
    private SecurityFeatureEnum securityFeature;

    @Column("security_status")
    private boolean securityStatus;
}