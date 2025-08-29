package com.firefly.core.banking.cards.core.services.physical.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.physical.v1.PhysicalCardMapper;
import com.firefly.core.banking.cards.interfaces.dtos.physical.v1.PhysicalCardDTO;
import com.firefly.core.banking.cards.models.entities.physical.v1.PhysicalCard;
import com.firefly.core.banking.cards.models.repositories.physical.v1.PhysicalCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class PhysicalCardServiceImpl implements PhysicalCardService {

    @Autowired
    private PhysicalCardRepository repository;

    @Autowired
    private PhysicalCardMapper mapper;

    @Override
    public Mono<PaginationResponse<PhysicalCardDTO>> listPhysicalCards(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<PhysicalCardDTO> createPhysicalCard(Long cardId, PhysicalCardDTO physicalCardDTO) {
        physicalCardDTO.setCardId(cardId);
        PhysicalCard entity = mapper.toEntity(physicalCardDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<PhysicalCardDTO> getPhysicalCard(Long cardId, Long physicalCardId) {
        return repository.findByPhysicalCardId(physicalCardId)
                .filter(physicalCard -> physicalCard.getCardId().equals(cardId))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<PhysicalCardDTO> updatePhysicalCard(Long cardId, Long physicalCardId, PhysicalCardDTO physicalCardDTO) {
        return repository.findByPhysicalCardId(physicalCardId)
                .filter(physicalCard -> physicalCard.getCardId().equals(cardId))
                .flatMap(existing -> {
                    physicalCardDTO.setPhysicalCardId(physicalCardId);
                    physicalCardDTO.setCardId(cardId);
                    PhysicalCard updatedEntity = mapper.toEntity(physicalCardDTO);
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deletePhysicalCard(Long cardId, Long physicalCardId) {
        return repository.findByPhysicalCardId(physicalCardId)
                .filter(physicalCard -> physicalCard.getCardId().equals(cardId))
                .flatMap(repository::delete);
    }
}
