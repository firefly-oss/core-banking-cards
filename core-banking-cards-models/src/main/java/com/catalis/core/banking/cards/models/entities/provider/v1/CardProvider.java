package com.catalis.core.banking.cards.models.entities.provider.v1;

import com.catalis.core.banking.cards.interfaces.enums.provider.v1.ProviderStatusEnum;
import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Card Provider entity.
 * Represents a provider that processes card transactions and manages card services.
 */
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

    @Column("provider_code")
    private String providerCode;

    @Column("provider_type")
    private String providerType;

    @Column("api_base_url")
    private String apiBaseUrl;

    @Column("api_version")
    private String apiVersion;

    @Column("api_key")
    private String apiKey;

    @Column("api_secret")
    private String apiSecret;

    @Column("api_username")
    private String apiUsername;

    @Column("api_password")
    private String apiPassword;

    @Column("api_certificate")
    private String apiCertificate;

    @Column("webhook_url")
    private String webhookUrl;

    @Column("webhook_secret")
    private String webhookSecret;

    @Column("supports_physical_cards")
    private Boolean supportsPhysicalCards;

    @Column("supports_virtual_cards")
    private Boolean supportsVirtualCards;

    @Column("supports_tokenization")
    private Boolean supportsTokenization;

    @Column("supports_3d_secure")
    private Boolean supports3dSecure;

    @Column("supports_apple_pay")
    private Boolean supportsApplePay;

    @Column("supports_google_pay")
    private Boolean supportsGooglePay;

    @Column("supports_samsung_pay")
    private Boolean supportsSamsungPay;

    @Column("contact_name")
    private String contactName;

    @Column("contact_email")
    private String contactEmail;

    @Column("contact_phone")
    private String contactPhone;

    @Column("support_url")
    private String supportUrl;

    @Column("support_email")
    private String supportEmail;

    @Column("support_phone")
    private String supportPhone;

    @Column("contract_start_date")
    private LocalDateTime contractStartDate;

    @Column("contract_end_date")
    private LocalDateTime contractEndDate;

    @Column("status")
    private ProviderStatusEnum status;

    @Column("last_connection_date")
    private LocalDateTime lastConnectionDate;

    @Column("last_connection_status")
    private String lastConnectionStatus;

    @Column("description")
    private String description;
}
