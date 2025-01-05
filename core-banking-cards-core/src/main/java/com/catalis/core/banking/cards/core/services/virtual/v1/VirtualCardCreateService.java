package com.catalis.core.banking.cards.core.services.virtual.v1;

import com.catalis.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
import com.catalis.core.banking.cards.models.entities.virtual.v1.VirtualCard;
import com.catalis.core.banking.cards.models.repositories.virtual.v1.VirtualCardRepository;
import com.catalis.core.banking.cards.core.mappers.virtual.v1.VirtualCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class VirtualCardCreateService {

    @Autowired
    private VirtualCardRepository virtualCardRepository;

    @Autowired
    private VirtualCardMapper virtualCardMapper;

    /**
     * Creates a new virtual card based on the provided VirtualCardDTO.
     *
     * @param dto a DTO containing the details for the virtual card creation
     * @return a Mono containing the created VirtualCardDTO after a successful save operation
     */
    public Mono<VirtualCardDTO> createVirtualCard(VirtualCardDTO dto) {
        VirtualCard entity = virtualCardMapper.toEntity(dto);
        return virtualCardRepository.save(entity)
                .map(virtualCardMapper::toDTO);
    }
}
