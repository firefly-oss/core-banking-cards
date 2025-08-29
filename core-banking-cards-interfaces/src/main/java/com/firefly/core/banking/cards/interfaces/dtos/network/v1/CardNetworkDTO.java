package com.firefly.core.banking.cards.interfaces.dtos.network.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Data Transfer Object for Card Network.
 * Contains the essential information about a Card Network that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardNetworkDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardNetworkId;

    private String networkName;

    private String networkCode;

    private String networkLogoUrl;

    private String supportContact;

    private String apiEndpoint;

    private String apiKey;

    private String apiSecret;

    private Boolean isActive;

    private String description;
}