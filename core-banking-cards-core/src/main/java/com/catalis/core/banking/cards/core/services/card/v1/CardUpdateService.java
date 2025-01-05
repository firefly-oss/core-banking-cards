package com.catalis.core.banking.cards.core.services.card.v1;

import com.catalis.core.banking.cards.interfaces.dtos.card.v1.CardDTO;
import com.catalis.core.banking.cards.models.repositories.card.v1.CardRepository;
import com.catalis.core.banking.cards.core.mappers.card.v1.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardUpdateService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardMapper cardMapper;

    /**
     * Updates an existing card in the repository with the fields provided in the given CardDTO.
     * The method fetches the card by its ID, updates its fields, saves the updated entity,
     * and then maps the result back to a CardDTO.
     *
     * @param cardId The ID of the card to be updated.
     * @param cardDTO The CardDTO containing the updated fields for the card.
     * @return A Mono of CardDTO representing the updated card.
     */
    public Mono<CardDTO> updateCard(Long cardId, CardDTO cardDTO) {
        return cardRepository.findById(cardId)
                .flatMap(existing -> {
                    existing.setAccountId(cardDTO.getAccountId());
                    existing.setContractId(cardDTO.getContractId());
                    existing.setCardType(cardDTO.getCardType());
                    existing.setCardStatus(cardDTO.getCardStatus());
                    existing.setIssuingBank(cardDTO.getIssuingBank());
                    existing.setIssuanceDate(cardDTO.getIssuanceDate());
                    existing.setExpirationDate(cardDTO.getExpirationDate());
                    existing.setPhysicalFlag(cardDTO.isPhysicalFlag());
                    return cardRepository.save(existing);
                })
                .map(cardMapper::toDTO);
    }
}
