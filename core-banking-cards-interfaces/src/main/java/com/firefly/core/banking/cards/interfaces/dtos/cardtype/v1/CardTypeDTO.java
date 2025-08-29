package com.firefly.core.banking.cards.interfaces.dtos.cardtype.v1;

import com.firefly.core.banking.cards.interfaces.dtos.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Data Transfer Object for Card Type.
 * Contains the essential information about a Card Type that needs to be exposed through the API.
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class CardTypeDTO extends BaseDTO {
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long cardTypeId;
    
    private String typeName;
    
    private String typeCode;
    
    private Boolean isCredit;
    
    private Boolean isDebit;
    
    private Boolean isPrepaid;
    
    private Boolean isVirtual;
    
    private Boolean isCommercial;
    
    private Boolean isGift;
    
    private Double defaultCreditLimit;
    
    private Double defaultDailyLimit;
    
    private Double defaultMonthlyLimit;
    
    private Boolean isActive;
    
    private String description;
}