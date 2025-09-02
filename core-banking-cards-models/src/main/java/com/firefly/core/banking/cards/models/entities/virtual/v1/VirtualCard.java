package com.firefly.core.banking.cards.models.entities.virtual.v1;

import com.firefly.core.banking.cards.interfaces.enums.virtual.v1.VirtualCardStatusEnum;
import com.firefly.core.banking.cards.models.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Virtual Card entity.
 * Represents a virtual payment card that exists only in digital form.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("virtual_card")
public class VirtualCard extends BaseEntity {
    @Id
    @Column("virtual_card_id")
    private UUID virtualCardId;

    @Column("card_id")
    private UUID cardId;

    @Column("device_id")
    private String deviceId;

    @Column("device_type")
    private String deviceType;

    @Column("device_model")
    private String deviceModel;

    @Column("device_os")
    private String deviceOs;

    @Column("device_os_version")
    private String deviceOsVersion;

    @Column("wallet_provider")
    private String walletProvider;

    @Column("wallet_account_id")
    private String walletAccountId;

    @Column("tokenization_provider")
    private String tokenizationProvider;

    @Column("token_id")
    private String tokenId;

    @Column("token_expiry_date")
    private LocalDateTime tokenExpiryDate;

    @Column("token_status")
    private String tokenStatus;

    @Column("virtual_card_number")
    private String virtualCardNumber;

    @Column("virtual_card_status")
    private VirtualCardStatusEnum virtualCardStatus;

    @Column("is_default_for_device")
    private Boolean isDefaultForDevice;

    @Column("is_default_for_wallet")
    private Boolean isDefaultForWallet;

    @Column("is_provisioned")
    private Boolean isProvisioned;

    @Column("provisioning_date")
    private LocalDateTime provisioningDate;

    @Column("last_used_date")
    private LocalDateTime lastUsedDate;

    @Column("creation_timestamp")
    private LocalDateTime creationTimestamp;

    @Column("update_timestamp")
    private LocalDateTime updateTimestamp;

    @Column("deactivation_date")
    private LocalDateTime deactivationDate;

    @Column("deactivation_reason")
    private String deactivationReason;

    @Column("notes")
    private String notes;
}
