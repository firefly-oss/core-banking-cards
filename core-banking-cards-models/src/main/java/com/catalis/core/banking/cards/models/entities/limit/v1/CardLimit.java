package com.catalis.core.banking.cards.models.entities.limit.v1;

import com.catalis.core.banking.cards.interfaces.enums.limit.v1.LimitTypeEnum;
import com.catalis.core.banking.cards.interfaces.enums.limit.v1.ResetPeriodEnum;
import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_limit")
public class CardLimit extends BaseEntity {

    @Id
    @Column("card_limit_id")
    private Long cardLimitId;

    @Column("card_id")
    private Long cardId;

    @Column("limit_type")
    private LimitTypeEnum limitType;

    @Column("limit_amount")
    private BigDecimal limitAmount;

    @Column("current_usage")
    private BigDecimal currentUsage;

    @Column("reset_period")
    private ResetPeriodEnum resetPeriod;
}
