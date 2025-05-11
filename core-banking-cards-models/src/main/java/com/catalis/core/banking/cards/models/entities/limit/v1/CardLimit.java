package com.catalis.core.banking.cards.models.entities.limit.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Card Limit entity.
 * Represents spending limits for a payment card.
 */
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

    @Column("program_id")
    private Long programId;

    @Column("limit_name")
    private String limitName;

    @Column("limit_code")
    private String limitCode;

    @Column("limit_type")
    private String limitType;

    @Column("channel_type")
    private String channelType;

    @Column("transaction_type")
    private String transactionType;

    @Column("merchant_category_code")
    private String merchantCategoryCode;

    @Column("country_code")
    private String countryCode;

    @Column("currency_code")
    private String currencyCode;

    @Column("limit_amount")
    private BigDecimal limitAmount;

    @Column("current_usage")
    private BigDecimal currentUsage;

    @Column("available_amount")
    private BigDecimal availableAmount;

    @Column("reset_period")
    private String resetPeriod;

    @Column("reset_day")
    private Integer resetDay;

    @Column("reset_hour")
    private Integer resetHour;

    @Column("last_reset_date")
    private LocalDateTime lastResetDate;

    @Column("next_reset_date")
    private LocalDateTime nextResetDate;

    @Column("effective_from")
    private LocalDateTime effectiveFrom;

    @Column("effective_to")
    private LocalDateTime effectiveTo;

    @Column("is_active")
    private Boolean isActive;

    @Column("is_system_default")
    private Boolean isSystemDefault;

    @Column("is_program_default")
    private Boolean isProgramDefault;

    @Column("is_customer_configurable")
    private Boolean isCustomerConfigurable;

    @Column("min_limit")
    private BigDecimal minLimit;

    @Column("max_limit")
    private BigDecimal maxLimit;

    @Column("description")
    private String description;
}
