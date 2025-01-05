package com.catalis.core.banking.cards.core.services.physical.v1;

import com.catalis.core.banking.cards.core.mappers.physical.v1.PhysicalCardMapper;
import com.catalis.core.banking.cards.interfaces.dtos.physical.v1.PhysicalCardDTO;
import com.catalis.core.banking.cards.models.entities.physical.v1.PhysicalCard;
import com.catalis.core.banking.cards.models.repositories.physical.v1.PhysicalCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PhysicalCardCreateService {

    @Autowired
    private PhysicalCardRepository physicalCardRepository;

    @Autowired
    private PhysicalCardMapper physicalCardMapper;

    /**
     * Creates a new physical card in the system by saving the provided physical card data.
     *
     * @param dto the PhysicalCardDTO object containing the data to create the physical card
     * @return a Mono emitting the created PhysicalCardDTO object after successful saving
     */
    public Mono<PhysicalCardDTO> createPhysicalCard(PhysicalCardDTO dto) {
        PhysicalCard entity = physicalCardMapper.toEntity(dto);
        return physicalCardRepository.save(entity)
                .map(physicalCardMapper::toDTO);
    }


}
