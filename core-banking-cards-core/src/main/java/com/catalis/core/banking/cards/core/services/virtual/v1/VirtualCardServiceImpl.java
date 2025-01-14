package com.catalis.core.banking.cards.core.services.virtual.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.virtual.v1.VirtualCardMapper;
import com.catalis.core.banking.cards.interfaces.dtos.virtual.v1.VirtualCardDTO;
import com.catalis.core.banking.cards.models.entities.virtual.v1.VirtualCard;
import com.catalis.core.banking.cards.models.repositories.virtual.v1.VirtualCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class VirtualCardServiceImpl implements VirtualCardService {

    @Autowired
    private VirtualCardRepository repository;

    @Autowired
    private VirtualCardMapper mapper;

    @Override
    public Mono<PaginationResponse<VirtualCardDTO>> listVirtualCards(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<VirtualCardDTO> createVirtualCard(Long cardId, VirtualCardDTO virtualCardDTO) {
        virtualCardDTO.setCardId(cardId);
        VirtualCard virtualCard = mapper.toEntity(virtualCardDTO);
        return repository.save(virtualCard)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<VirtualCardDTO> getVirtualCard(Long cardId, Long virtualCardId) {
        return repository.findByVirtualCardId(virtualCardId)
                .filter(virtualCard -> virtualCard.getCardId().equals(cardId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<VirtualCardDTO> updateVirtualCard(Long cardId, Long virtualCardId, VirtualCardDTO virtualCardDTO) {
        return repository.findByVirtualCardId(virtualCardId)
                .filter(existingCard -> existingCard.getCardId().equals(cardId))
                .flatMap(existingCard -> {
                    virtualCardDTO.setVirtualCardId(existingCard.getVirtualCardId());
                    virtualCardDTO.setCardId(cardId);
                    VirtualCard updatedEntity = mapper.toEntity(virtualCardDTO);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteVirtualCard(Long cardId, Long virtualCardId) {
        return repository.findByVirtualCardId(virtualCardId)
                .filter(virtualCard -> virtualCard.getCardId().equals(cardId))
                .flatMap(repository::delete);
    }
}
