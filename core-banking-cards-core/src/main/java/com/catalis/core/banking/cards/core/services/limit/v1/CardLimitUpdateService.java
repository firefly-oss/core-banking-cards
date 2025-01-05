package com.catalis.core.banking.cards.core.services.limit.v1;

import com.catalis.core.banking.cards.core.mappers.limit.v1.CardLimitMapper;
import com.catalis.core.banking.cards.interfaces.dtos.limit.v1.CardLimitDTO;
import com.catalis.core.banking.cards.models.repositories.limit.v1.CardLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardLimitUpdateService {

    @Autowired
    private CardLimitRepository cardLimitRepository;

    @Autowired
    private CardLimitMapper cardLimitMapper;

    /**
     * Updates an existing card limit with the given data and returns the updated CardLimitDTO.
     * This method retrieves the card limit by its ID, applies the updates from the provided DTO,
     * saves the updated entity in the repository, and converts it to a DTO before returning.
     *
     * @param cardLimitId the ID of the card limit to be updated
     * @param dto the CardLimitDTO containing the updated card limit details
     * @return a Mono emitting the updated CardLimitDTO
     */
    public Mono<CardLimitDTO> updateCardLimit(Long cardLimitId, CardLimitDTO dto) {
        return cardLimitRepository.findById(cardLimitId)
                .flatMap(existing -> {
                    existing.setLimitType(dto.getLimitType());
                    existing.setLimitAmount(dto.getLimitAmount());
                    existing.setCurrentUsage(dto.getCurrentUsage());
                    existing.setResetPeriod(dto.getResetPeriod());
                    existing.setCardId(dto.getCardId());
                    return cardLimitRepository.save(existing);
                })
                .map(cardLimitMapper::toDTO);
    }

}
