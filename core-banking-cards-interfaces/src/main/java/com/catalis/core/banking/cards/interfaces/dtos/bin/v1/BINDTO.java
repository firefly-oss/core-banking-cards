package com.catalis.core.banking.cards.interfaces.dtos.bin.v1;

import com.catalis.core.banking.cards.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Data Transfer Object for Bank Identification Number (BIN).
 * Contains the essential information about a BIN that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class BINDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long binId;
    
    private String binNumber;
    
    private Integer binLength;
    
    @FilterableId
    private Long issuerId;
    
    @FilterableId
    private Long cardNetworkId;
    
    @FilterableId
    private Long cardTypeId;
    
    private String countryCode;
    
    private String currencyCode;
    
    private Boolean isActive;
    
    private String description;
}