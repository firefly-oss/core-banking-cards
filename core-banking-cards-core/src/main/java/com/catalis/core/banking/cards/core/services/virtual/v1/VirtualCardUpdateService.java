package com.catalis.core.banking.cards.core.services.virtual.v1;

import com.catalis.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
import com.catalis.core.banking.cards.models.repositories.virtual.v1.VirtualCardRepository;
import com.catalis.core.banking.cards.core.mappers.virtual.v1.VirtualCardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class VirtualCardUpdateService {

    @Autowired
    private VirtualCardRepository virtualCardRepository;

    @Autowired
    private VirtualCardMapper virtualCardMapper;

    /**
     * Updates the details of an existing virtual card based on the provided VirtualCardDTO.
     *
     * @param virtualCardId the unique identifier of the virtual card to be updated
     * @param dto a DTO containing the updated virtual card information
     * @return a Mono containing the updated VirtualCardDTO after successful save operation
     */
    public Mono<VirtualCardDTO> updateVirtualCard(Long virtualCardId, VirtualCardDTO dto) {
        return virtualCardRepository.findById(virtualCardId)
                .flatMap(existing -> {
                    existing.setDeviceId(dto.getDeviceId());
                    existing.setVirtualCardNumber(dto.getVirtualCardNumber());
                    existing.setVirtualCardStatus(dto.getVirtualCardStatus());
                    existing.setCreationTimestamp(dto.getCreationTimestamp());
                    existing.setUpdateTimestamp(dto.getUpdateTimestamp());
                    return virtualCardRepository.save(existing);
                })
                .map(virtualCardMapper::toDTO);
    }
}
