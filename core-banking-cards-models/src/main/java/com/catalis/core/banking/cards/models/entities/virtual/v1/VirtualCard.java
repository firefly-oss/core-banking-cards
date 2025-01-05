package com.catalis.core.banking.cards.models.entities.virtual.v1;

import com.catalis.core.banking.cards.interfaces.enums.virtual.v1.VirtualCardStatusEnum;
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
@Table("virtual_card")
public class VirtualCard extends BaseEntity {
    @Id
    @Column("virtual_card_id")
    private Long virtualCardId;

    @Column("card_id")
    private Long cardId;

    @Column("device_id")
    private String deviceId;

    @Column("virtual_card_number")
    private String virtualCardNumber;

    @Column("virtual_card_status")
    private VirtualCardStatusEnum virtualCardStatus;

    @Column("creation_timestamp")
    private LocalDateTime creationTimestamp;

    @Column("update_timestamp")
    private LocalDateTime updateTimestamp;
}