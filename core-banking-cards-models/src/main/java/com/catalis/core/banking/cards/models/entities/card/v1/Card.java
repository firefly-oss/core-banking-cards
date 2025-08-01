package com.catalis.core.banking.cards.models.entities.card.v1;

import com.catalis.core.banking.cards.interfaces.enums.card.v1.CardStatusEnum;
import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Card entity.
 * Represents a payment card with its characteristics and relationships.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card")
public class Card extends BaseEntity {

    @Id
    @Column("card_id")
    private Long cardId;

    @Column("card_number")
    private String cardNumber;

    @Column("masked_card_number")
    private String maskedCardNumber;

    @Column("card_sequence_number")
    private String cardSequenceNumber;

    @Column("bin_id")
    private Long binId;

    @Column("card_type_id")
    private Long cardTypeId;

    @Column("card_network_id")
    private Long cardNetworkId;

    @Column("issuer_id")
    private Long issuerId;

    @Column("contract_id")
    private Long contractId;

    @Column("account_id")
    private Long accountId;

    @Column("party_id")
    private Long partyId;

    @Column("card_status")
    private CardStatusEnum cardStatus;

    @Column("card_holder_name")
    private String cardHolderName;

    @Column("card_holder_id")
    private String cardHolderId;

    @Column("expiration_month")
    private Integer expirationMonth;

    @Column("expiration_year")
    private Integer expirationYear;

    @Column("cvv")
    private String cvv;

    @Column("pin")
    private String pin;

    @Column("activation_date")
    private LocalDateTime activationDate;

    @Column("issuance_date")
    private LocalDateTime issuanceDate;

    @Column("expiration_date")
    private LocalDateTime expirationDate;

    @Column("last_used_date")
    private LocalDateTime lastUsedDate;

    @Column("is_physical")
    private Boolean isPhysical;

    @Column("is_virtual")
    private Boolean isVirtual;

    @Column("is_primary")
    private Boolean isPrimary;

    @Column("is_active")
    private Boolean isActive;

    @Column("is_locked")
    private Boolean isLocked;

    @Column("lock_reason")
    private String lockReason;

    @Column("daily_limit")
    private Double dailyLimit;

    @Column("monthly_limit")
    private Double monthlyLimit;

    @Column("credit_limit")
    private Double creditLimit;

    @Column("available_balance")
    private Double availableBalance;

    @Column("currency_code")
    private String currencyCode;

    @Column("design_id")
    private Long designId;

    @Column("notes")
    private String notes;
}
