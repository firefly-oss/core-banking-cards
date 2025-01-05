package com.catalis.core.banking.cards.models.entities.card.v1;

import com.catalis.core.banking.cards.interfaces.enums.card.v1.CardStatusEnum;
import com.catalis.core.banking.cards.interfaces.enums.card.v1.CardTypeEnum;
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
@Table("card")
public class Card extends BaseEntity {

    @Id
    @Column("card_id")
    private Long cardId;

    @Column("contract_id")
    private Long contractId;

    @Column("account_id")
    private Long accountId;

    @Column("card_type")
    private CardTypeEnum cardType;

    @Column("card_status")
    private CardStatusEnum cardStatus;

    @Column("issuing_bank")
    private String issuingBank;

    @Column("issuance_date")
    private LocalDateTime issuanceDate;

    @Column("expiration_date")
    private LocalDateTime expirationDate;

    @Column("physical_flag")
    private boolean physicalFlag;
}
