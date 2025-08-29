package com.firefly.core.banking.cards.interfaces.dtos.terminal.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Card Terminal.
 * Contains the essential information about a Card Terminal that needs to be exposed through the API.
 * A Card Terminal represents physical or virtual terminals where card transactions are processed,
 * including terminal details, capabilities, and relationships with merchants and processors.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardTerminalDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long terminalId;

    private String terminalReference;

    private String terminalSerialNumber;

    private String terminalName;

    private String terminalDescription;

    private String terminalType;

    private String terminalModel;

    private String terminalManufacturer;

    private String terminalStatus;

    private Boolean isActive;

    private LocalDateTime activationDate;

    private LocalDateTime deactivationDate;

    private String deactivationReason;

    @FilterableId
    private Long merchantId;

    private String merchantName;

    private String merchantLocationId;

    private String merchantLocationName;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private String geolocation;

    private String acquirerId;

    private String acquirerName;

    private String processorId;

    private String processorName;

    private String softwareVersion;

    private String firmwareVersion;

    private String hardwareVersion;

    private LocalDateTime lastMaintenanceDate;

    private LocalDateTime nextMaintenanceDate;

    private LocalDateTime lastUpdateDate;

    private LocalDateTime installationDate;

    private Boolean isPhysical;

    private Boolean isVirtual;

    private Boolean isMobile;

    private Boolean isAttended;

    private Boolean isUnattended;

    private Boolean isContactless;

    private Boolean isChip;

    private Boolean isMagstripe;

    private Boolean isPinSupported;

    private Boolean isSignatureSupported;

    private Boolean isBiometricSupported;

    private Boolean isNfcSupported;

    private Boolean isQrSupported;

    private Boolean isEmvSupported;

    private Boolean isP2peSupported;

    private Boolean isTokenizationSupported;

    private String supportedCardNetworks;

    private String supportedPaymentMethods;

    private String supportedCurrencies;

    private String defaultCurrency;

    private String communicationType;

    private String connectionType;

    private String ipAddress;

    private String macAddress;

    private Boolean isPciCompliant;

    private LocalDateTime pciComplianceDate;

    private LocalDateTime pciComplianceExpiry;

    private String encryptionMethod;

    private String keyManagementScheme;

    private LocalDateTime lastKeyRotationDate;

    private LocalDateTime nextKeyRotationDate;

    private Boolean isFaultDetected;

    private String faultCode;

    private String faultDescription;

    private LocalDateTime faultDetectionDate;

    private LocalDateTime lastTransactionTimestamp;

    private String lastTransactionId;

    private String lastTransactionStatus;

    private String notes;
}