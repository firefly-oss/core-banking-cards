package com.firefly.core.banking.cards.interfaces.dtos.card.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.banking.cards.interfaces.enums.card.v1.CardStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardId;

    private String cardNumber;

    private String maskedCardNumber;

    private String cardSequenceNumber;

    @FilterableId
    private Long binId;

    @FilterableId
    private Long cardTypeId;

    @FilterableId
    private Long cardNetworkId;

    @FilterableId
    private Long issuerId;

    @FilterableId
    private Long contractId;

    @FilterableId
    private Long accountId;

    @FilterableId
    private Long partyId;

    private CardStatusEnum cardStatus;

    private String cardHolderName;

    private String cardHolderId;

    private Integer expirationMonth;

    private Integer expirationYear;

    private String cvv;

    private String pin;

    private LocalDateTime activationDate;

    private LocalDateTime issuanceDate;

    private LocalDateTime expirationDate;

    private LocalDateTime lastUsedDate;

    private Boolean isPhysical;

    private Boolean isVirtual;

    private Boolean isPrimary;

    private Boolean isActive;

    private Boolean isLocked;

    private String lockReason;

    private Double dailyLimit;

    private Double monthlyLimit;

    private Double creditLimit;

    private Double availableBalance;

    private String currencyCode;

    @FilterableId
    private Long designId;

    private String notes;
}
