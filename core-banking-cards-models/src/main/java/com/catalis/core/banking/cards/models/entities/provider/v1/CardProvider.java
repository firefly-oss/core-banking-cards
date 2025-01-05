package com.catalis.core.banking.cards.models.entities.provider.v1;

import com.catalis.core.banking.cards.interfaces.enums.provider.v1.ProviderStatusEnum;
import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_provider")
public class CardProvider extends BaseEntity {
    @Id
    @Column("card_provider_id")
    private Long cardProviderId;

    @Column("card_id")
    private Long cardId;

    @Column("provider_name")
    private String providerName;

    @Column("external_reference")
    private String externalReference;

    @Column("status")
    private ProviderStatusEnum status;
}