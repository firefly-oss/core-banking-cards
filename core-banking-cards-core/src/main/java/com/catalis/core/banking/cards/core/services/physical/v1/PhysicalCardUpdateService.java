package com.catalis.core.banking.cards.core.services.physical.v1;

import com.catalis.core.banking.cards.core.mappers.physical.v1.PhysicalCardMapper;
import com.catalis.core.banking.cards.interfaces.dtos.physical.v1.PhysicalCardDTO;
import com.catalis.core.banking.cards.models.repositories.physical.v1.PhysicalCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PhysicalCardUpdateService {

    @Autowired
    private PhysicalCardRepository physicalCardRepository;

    @Autowired
    private PhysicalCardMapper physicalCardMapper;

    /**
     * Updates an existing physical card with new details provided in the given PhysicalCardDTO.
     *
     * @param physicalCardId the ID of the physical card to be updated
     * @param dto the PhysicalCardDTO object containing the new values for the physical card
     * @return a Mono emitting the updated PhysicalCardDTO object after successful update
     */
    public Mono<PhysicalCardDTO> updatePhysicalCard(Long physicalCardId, PhysicalCardDTO dto) {
        return physicalCardRepository.findById(physicalCardId)
                .flatMap(existing -> {
                    existing.setCardNumber(dto.getCardNumber());
                    existing.setCardCvv(dto.getCardCvv());
                    existing.setCardHolderName(dto.getCardHolderName());
                    existing.setCardNetwork(dto.getCardNetwork());
                    existing.setCardDesign(dto.getCardDesign());
                    existing.setShipmentDate(dto.getShipmentDate());
                    existing.setDeliveryDate(dto.getDeliveryDate());
                    return physicalCardRepository.save(existing);
                })
                .map(physicalCardMapper::toDTO);
    }

}
