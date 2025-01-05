package com.catalis.core.banking.cards.core.services.transaction.v1;

import com.catalis.core.banking.cards.core.mappers.transaction.v1.CardTransactionMapper;
import com.catalis.core.banking.cards.interfaces.dtos.transaction.v1.CardTransactionDTO;
import com.catalis.core.banking.cards.models.repositories.transaction.v1.CardTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardTransactionUpdateService {

    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Autowired
    private CardTransactionMapper cardTransactionMapper;

    /**
     * Updates an existing card transaction with the provided details.
     * The updates are performed based on the transaction ID and the new properties
     * provided in the CardTransactionDTO object.
     *
     * @param transactionId the ID of the transaction to be updated
     * @param dto the CardTransactionDTO object containing the new properties to update
     * @return a Mono containing the updated CardTransactionDTO object
     */
    public Mono<CardTransactionDTO> updateTransaction(Long transactionId, CardTransactionDTO dto) {
        return cardTransactionRepository.findById(transactionId)
                .flatMap(existing -> {
                    existing.setTransactionType(dto.getTransactionType());
                    existing.setTransactionStatus(dto.getTransactionStatus());
                    existing.setCardAuthCode(dto.getCardAuthCode());
                    existing.setCardMerchantCategoryCode(dto.getCardMerchantCategoryCode());
                    existing.setCardMerchantName(dto.getCardMerchantName());
                    existing.setCardPosEntryMode(dto.getCardPosEntryMode());
                    existing.setCardTransactionReference(dto.getCardTransactionReference());
                    existing.setCardTerminalId(dto.getCardTerminalId());
                    existing.setCardHolderCountry(dto.getCardHolderCountry());
                    existing.setCardPresentFlag(dto.isCardPresentFlag());
                    existing.setCardTransactionTimestamp(dto.getCardTransactionTimestamp());
                    existing.setCardFraudFlag(dto.isCardFraudFlag());
                    existing.setCardCurrencyConversionRate(dto.getCardCurrencyConversionRate());
                    existing.setCardFeeAmount(dto.getCardFeeAmount());
                    existing.setCardFeeCurrency(dto.getCardFeeCurrency());
                    existing.setCardInstallmentPlan(dto.getCardInstallmentPlan());
                    return cardTransactionRepository.save(existing);
                })
                .map(cardTransactionMapper::toDTO);
    }

}
