package com.catalis.core.banking.cards.models.entities.transaction.v1;

import com.catalis.core.banking.cards.interfaces.enums.transaction.v1.TransactionStatusEnum;
import com.catalis.core.banking.cards.interfaces.enums.transaction.v1.TransactionTypeEnum;
import com.catalis.core.banking.cards.models.entities.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("card_transaction")
public class CardTransaction extends BaseEntity {

    @Id
    @Column("card_transaction_id")
    private Long cardTransactionId;

    @Column("card_id")
    private Long cardId;

    @Column("transaction_type")
    private TransactionTypeEnum transactionType;

    @Column("merchant_info")
    private String merchantInfo;

    @Column("transaction_status")
    private TransactionStatusEnum transactionStatus;

    @Column("card_auth_code")
    private String cardAuthCode;

    @Column("card_merchant_category_code")
    private String cardMerchantCategoryCode;

    @Column("card_merchant_name")
    private String cardMerchantName;

    @Column("card_pos_entry_mode")
    private String cardPosEntryMode;

    @Column("card_transaction_reference")
    private String cardTransactionReference;

    @Column("card_terminal_id")
    private String cardTerminalId;

    @Column("card_holder_country")
    private String cardHolderCountry;  // ISO-2

    @Column("card_present_flag")
    private boolean cardPresentFlag;

    @Column("card_transaction_timestamp")
    private LocalDateTime cardTransactionTimestamp;

    @Column("card_fraud_flag")
    private boolean cardFraudFlag;

    @Column("card_currency_conversion_rate")
    private BigDecimal cardCurrencyConversionRate;

    @Column("card_fee_amount")
    private BigDecimal cardFeeAmount;

    @Column("card_fee_currency")
    private String cardFeeCurrency;  // ISO-3

    @Column("card_installment_plan")
    private String cardInstallmentPlan;
}
