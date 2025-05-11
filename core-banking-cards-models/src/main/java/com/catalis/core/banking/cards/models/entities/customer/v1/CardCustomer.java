package com.catalis.core.banking.cards.models.entities.customer.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Card Customer entity.
 * Represents the relationship between a customer and their cards, including customer preferences,
 * risk profiles, and card-specific customer information.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_customer")
public class CardCustomer extends BaseEntity {

    @Id
    @Column("card_customer_id")
    private Long cardCustomerId;

    @Column("customer_id")
    private Long customerId;

    @Column("customer_reference")
    private String customerReference;

    @Column("customer_type")
    private String customerType;

    @Column("customer_status")
    private String customerStatus;

    @Column("first_name")
    private String firstName;

    @Column("middle_name")
    private String middleName;

    @Column("last_name")
    private String lastName;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column("nationality")
    private String nationality;

    @Column("id_type")
    private String idType;

    @Column("id_number")
    private String idNumber;

    @Column("id_expiry_date")
    private LocalDateTime idExpiryDate;

    @Column("address_line1")
    private String addressLine1;

    @Column("address_line2")
    private String addressLine2;

    @Column("city")
    private String city;

    @Column("state")
    private String state;

    @Column("postal_code")
    private String postalCode;

    @Column("country")
    private String country;

    @Column("preferred_language")
    private String preferredLanguage;

    @Column("preferred_communication_channel")
    private String preferredCommunicationChannel;

    @Column("is_marketing_consent")
    private Boolean isMarketingConsent;

    @Column("marketing_consent_timestamp")
    private LocalDateTime marketingConsentTimestamp;

    @Column("is_terms_accepted")
    private Boolean isTermsAccepted;

    @Column("terms_accepted_timestamp")
    private LocalDateTime termsAcceptedTimestamp;

    @Column("terms_version")
    private String termsVersion;

    @Column("customer_since")
    private LocalDateTime customerSince;

    @Column("last_activity_date")
    private LocalDateTime lastActivityDate;

    @Column("total_cards_count")
    private Integer totalCardsCount;

    @Column("active_cards_count")
    private Integer activeCardsCount;

    @Column("credit_score")
    private Integer creditScore;

    @Column("credit_score_provider")
    private String creditScoreProvider;

    @Column("credit_score_date")
    private LocalDateTime creditScoreDate;

    @Column("risk_rating")
    private String riskRating;

    @Column("risk_score")
    private Integer riskScore;

    @Column("risk_assessment_date")
    private LocalDateTime riskAssessmentDate;

    @Column("kyc_status")
    private String kycStatus;

    @Column("kyc_verification_date")
    private LocalDateTime kycVerificationDate;

    @Column("kyc_reference")
    private String kycReference;

    @Column("aml_status")
    private String amlStatus;

    @Column("aml_check_date")
    private LocalDateTime amlCheckDate;

    @Column("aml_reference")
    private String amlReference;

    @Column("total_credit_limit")
    private BigDecimal totalCreditLimit;

    @Column("available_credit_limit")
    private BigDecimal availableCreditLimit;

    @Column("currency_code")
    private String currencyCode;

    @Column("default_payment_method")
    private String defaultPaymentMethod;

    @Column("default_payment_account_id")
    private String defaultPaymentAccountId;

    @Column("default_payment_account_type")
    private String defaultPaymentAccountType;

    @Column("is_auto_payment_enabled")
    private Boolean isAutoPaymentEnabled;

    @Column("auto_payment_type")
    private String autoPaymentType;

    @Column("auto_payment_amount")
    private String autoPaymentAmount;

    @Column("auto_payment_day")
    private Integer autoPaymentDay;

    @Column("is_paperless_statements")
    private Boolean isPaperlessStatements;

    @Column("is_transaction_alerts")
    private Boolean isTransactionAlerts;

    @Column("is_security_alerts")
    private Boolean isSecurityAlerts;

    @Column("is_payment_reminders")
    private Boolean isPaymentReminders;

    @Column("is_reward_notifications")
    private Boolean isRewardNotifications;

    @Column("is_promotion_notifications")
    private Boolean isPromotionNotifications;

    @Column("is_vip")
    private Boolean isVip;

    @Column("vip_tier")
    private String vipTier;

    @Column("vip_since")
    private LocalDateTime vipSince;

    @Column("loyalty_program_id")
    private Long loyaltyProgramId;

    @Column("loyalty_program_status")
    private String loyaltyProgramStatus;

    @Column("loyalty_points_balance")
    private BigDecimal loyaltyPointsBalance;

    @Column("notes")
    private String notes;
}