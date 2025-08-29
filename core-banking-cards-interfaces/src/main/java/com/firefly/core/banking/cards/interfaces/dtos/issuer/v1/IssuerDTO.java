package com.firefly.core.banking.cards.interfaces.dtos.issuer.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Data Transfer Object for Issuer.
 * Contains the essential information about an Issuer that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class IssuerDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long issuerId;
    
    private String issuerName;
    
    private String issuerCode;
    
    private String countryCode;
    
    private String contactEmail;
    
    private String contactPhone;
    
    private Boolean isActive;
    
    private String supportUrl;
    
    private String logoUrl;
    
    private String description;
}