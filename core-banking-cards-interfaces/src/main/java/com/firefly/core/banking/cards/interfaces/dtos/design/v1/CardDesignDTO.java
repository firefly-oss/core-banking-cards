package com.firefly.core.banking.cards.interfaces.dtos.design.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Data Transfer Object for Card Design.
 * Contains the essential information about a Card Design that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardDesignDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long designId;
    
    private String designName;
    
    private String designCode;
    
    private String frontImageUrl;
    
    private String backImageUrl;
    
    @FilterableId
    private Long cardTypeId;
    
    @FilterableId
    private Long issuerId;
    
    @FilterableId
    private Long cardNetworkId;
    
    private Boolean isCustomizable;
    
    private Boolean isDefault;
    
    private Boolean isActive;
    
    private String description;
}