package com.catalis.core.banking.cards.models.entities.alert.v1;

import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Card Alert entity.
 * Represents an alert or notification related to a card or transaction.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_alert")
public class CardAlert extends BaseEntity {

    @Id
    @Column("alert_id")
    private Long alertId;

    @Column("card_id")
    private Long cardId;

    @Column("transaction_id")
    private Long transactionId;

    @Column("party_id")
    private Long partyId;

    @Column("account_id")
    private Long accountId;

    @Column("alert_reference")
    private String alertReference;

    @Column("alert_type")
    private String alertType;

    @Column("alert_category")
    private String alertCategory;

    @Column("alert_severity")
    private String alertSeverity;

    @Column("alert_status")
    private String alertStatus;

    @Column("alert_title")
    private String alertTitle;

    @Column("alert_message")
    private String alertMessage;

    @Column("alert_details")
    private String alertDetails;

    @Column("trigger_event")
    private String triggerEvent;

    @Column("trigger_timestamp")
    private LocalDateTime triggerTimestamp;

    @Column("trigger_amount")
    private BigDecimal triggerAmount;

    @Column("trigger_currency")
    private String triggerCurrency;

    @Column("trigger_location")
    private String triggerLocation;

    @Column("trigger_merchant")
    private String triggerMerchant;

    @Column("trigger_merchant_category")
    private String triggerMerchantCategory;

    @Column("is_party_configurable")
    private Boolean isPartyConfigurable;

    @Column("is_system_generated")
    private Boolean isSystemGenerated;

    @Column("is_read")
    private Boolean isRead;

    @Column("read_timestamp")
    private LocalDateTime readTimestamp;

    @Column("delivery_channel")
    private String deliveryChannel;

    @Column("delivery_status")
    private String deliveryStatus;

    @Column("delivery_timestamp")
    private LocalDateTime deliveryTimestamp;

    @Column("delivery_recipient")
    private String deliveryRecipient;

    @Column("delivery_details")
    private String deliveryDetails;

    @Column("expiration_timestamp")
    private LocalDateTime expirationTimestamp;

    @Column("action_required")
    private Boolean actionRequired;

    @Column("action_type")
    private String actionType;

    @Column("action_taken")
    private String actionTaken;

    @Column("action_timestamp")
    private LocalDateTime actionTimestamp;

    @Column("action_by")
    private String actionBy;

    @Column("notes")
    private String notes;
}