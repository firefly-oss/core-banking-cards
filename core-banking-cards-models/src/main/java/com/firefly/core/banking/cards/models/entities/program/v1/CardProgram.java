package com.firefly.core.banking.cards.models.entities.program.v1;

import com.firefly.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Card Program entity.
 * Represents a card program which is a set of rules, configurations, and features for a group of cards.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_program")
public class CardProgram extends BaseEntity {

    @Id
    @Column("program_id")
    private Long programId;

    @Column("program_name")
    private String programName;

    @Column("program_code")
    private String programCode;

    @Column("issuer_id")
    private Long issuerId;

    @Column("bin_id")
    private Long binId;

    @Column("card_type_id")
    private Long cardTypeId;

    @Column("card_network_id")
    private Long cardNetworkId;

    @Column("default_design_id")
    private Long defaultDesignId;

    @Column("start_date")
    private LocalDateTime startDate;

    @Column("end_date")
    private LocalDateTime endDate;

    @Column("is_active")
    private Boolean isActive;

    @Column("max_cards_per_party")
    private Integer maxCardsPerParty;

    @Column("default_daily_limit")
    private Double defaultDailyLimit;

    @Column("default_monthly_limit")
    private Double defaultMonthlyLimit;

    @Column("default_credit_limit")
    private Double defaultCreditLimit;

    @Column("default_card_validity_years")
    private Integer defaultCardValidityYears;

    @Column("supports_physical_cards")
    private Boolean supportsPhysicalCards;

    @Column("supports_virtual_cards")
    private Boolean supportsVirtualCards;

    @Column("supports_contactless")
    private Boolean supportsContactless;

    @Column("supports_international")
    private Boolean supportsInternational;

    @Column("supports_atm_withdrawal")
    private Boolean supportsAtmWithdrawal;

    @Column("supports_online_transactions")
    private Boolean supportsOnlineTransactions;

    @Column("supports_recurring_payments")
    private Boolean supportsRecurringPayments;

    @Column("supports_apple_pay")
    private Boolean supportsApplePay;

    @Column("supports_google_pay")
    private Boolean supportsGooglePay;

    @Column("supports_samsung_pay")
    private Boolean supportsSamsungPay;

    @Column("requires_pin")
    private Boolean requiresPin;

    @Column("requires_activation")
    private Boolean requiresActivation;

    @Column("currency_code")
    private String currencyCode;

    @Column("country_code")
    private String countryCode;

    @Column("terms_and_conditions_url")
    private String termsAndConditionsUrl;

    @Column("description")
    private String description;
}