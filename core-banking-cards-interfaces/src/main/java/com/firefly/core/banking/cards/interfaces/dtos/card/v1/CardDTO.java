package com.firefly.core.banking.cards.interfaces.dtos.card.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.banking.cards.interfaces.enums.card.v1.CardStatusEnum;
import com.firefly.core.utils.annotations.FilterableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.*;
import com.firefly.core.banking.cards.interfaces.validation.CardNumber;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID cardId;

    @NotBlank(message = "Card number is required")
    @CardNumber
    private String cardNumber;

    @Pattern(regexp = "^[0-9*]{13,19}$", message = "Masked card number must be 13-19 characters with digits and asterisks")
    private String maskedCardNumber;

    @Pattern(regexp = "^[0-9]{1,3}$", message = "Card sequence number must be 1-3 digits")
    private String cardSequenceNumber;

    @FilterableId
    @NotNull(message = "BIN ID is required")
    private UUID binId;

    @FilterableId
    @NotNull(message = "Card type ID is required")
    private UUID cardTypeId;

    @FilterableId
    @NotNull(message = "Card network ID is required")
    private UUID cardNetworkId;

    @FilterableId
    @NotNull(message = "Issuer ID is required")
    private UUID issuerId;

    @FilterableId
    private UUID contractId;

    @FilterableId
    @NotNull(message = "Account ID is required")
    private UUID accountId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private UUID partyId;

    @NotNull(message = "Card status is required")
    private CardStatusEnum cardStatus;

    @NotBlank(message = "Card holder name is required")
    @Size(min = 2, max = 100, message = "Card holder name must be between 2 and 100 characters")
    private String cardHolderName;

    @NotBlank(message = "Card holder ID is required")
    @Size(min = 5, max = 50, message = "Card holder ID must be between 5 and 50 characters")
    private String cardHolderId;

    @NotNull(message = "Expiration month is required")
    @Min(value = 1, message = "Expiration month must be between 1 and 12")
    @Max(value = 12, message = "Expiration month must be between 1 and 12")
    private Integer expirationMonth;

    @NotNull(message = "Expiration year is required")
    @Min(value = 2024, message = "Expiration year must be current year or later")
    @Max(value = 2050, message = "Expiration year cannot exceed 2050")
    private Integer expirationYear;

    @Pattern(regexp = "^[0-9]{3,4}$", message = "CVV must be 3 or 4 digits")
    private String cvv;

    @Pattern(regexp = "^[0-9]{4,8}$", message = "PIN must be 4-8 digits")
    private String pin;

    @PastOrPresent(message = "Activation date cannot be in the future")
    private LocalDateTime activationDate;

    @PastOrPresent(message = "Issuance date cannot be in the future")
    private LocalDateTime issuanceDate;

    @Future(message = "Expiration date must be in the future")
    private LocalDateTime expirationDate;

    @PastOrPresent(message = "Last used date cannot be in the future")
    private LocalDateTime lastUsedDate;

    @NotNull(message = "Physical card flag is required")
    private Boolean isPhysical;

    @NotNull(message = "Virtual card flag is required")
    private Boolean isVirtual;

    @NotNull(message = "Primary card flag is required")
    private Boolean isPrimary;

    @NotNull(message = "Active flag is required")
    private Boolean isActive;

    @NotNull(message = "Locked flag is required")
    private Boolean isLocked;

    @Size(max = 255, message = "Lock reason cannot exceed 255 characters")
    private String lockReason;

    @DecimalMin(value = "0.0", inclusive = false, message = "Daily limit must be greater than 0")
    @DecimalMax(value = "999999.99", message = "Daily limit cannot exceed 999,999.99")
    private Double dailyLimit;

    @DecimalMin(value = "0.0", inclusive = false, message = "Monthly limit must be greater than 0")
    @DecimalMax(value = "9999999.99", message = "Monthly limit cannot exceed 9,999,999.99")
    private Double monthlyLimit;

    @DecimalMin(value = "0.0", inclusive = false, message = "Credit limit must be greater than 0")
    @DecimalMax(value = "99999999.99", message = "Credit limit cannot exceed 99,999,999.99")
    private Double creditLimit;

    @DecimalMin(value = "0.0", message = "Available balance cannot be negative")
    private Double availableBalance;

    @NotBlank(message = "Currency code is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency code must be a 3-letter ISO code")
    private String currencyCode;

    @FilterableId
    private UUID designId;

    @Size(max = 1000, message = "Notes cannot exceed 1000 characters")
    private String notes;
}
