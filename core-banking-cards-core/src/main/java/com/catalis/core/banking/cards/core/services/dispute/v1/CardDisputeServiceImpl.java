package com.catalis.core.banking.cards.core.services.dispute.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.dispute.v1.CardDisputeMapper;
import com.catalis.core.banking.cards.interfaces.dtos.dispute.v1.CardDisputeDTO;
import com.catalis.core.banking.cards.models.entities.dispute.v1.CardDispute;
import com.catalis.core.banking.cards.models.repositories.dispute.v1.CardDisputeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardDisputeServiceImpl implements CardDisputeService {

    @Autowired
    private CardDisputeRepository repository;

    @Autowired
    private CardDisputeMapper mapper;

    @Override
    public Mono<PaginationResponse<CardDisputeDTO>> listDisputes(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardDisputeDTO> createDispute(Long cardId, CardDisputeDTO disputeDTO) {
        disputeDTO.setCardId(cardId);
        CardDispute entity = mapper.toEntity(disputeDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardDisputeDTO> getDispute(Long cardId, Long disputeId) {
        return repository.findByDisputeId(disputeId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardDispute not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardDisputeDTO> updateDispute(Long cardId, Long disputeId, CardDisputeDTO disputeDTO) {
        return repository.findByDisputeId(disputeId)
                .flatMap(existingDispute -> {
                    if (!existingDispute.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardDispute not found for the specified card."));
                    }
                    
                    CardDispute updatedDispute = mapper.toEntity(disputeDTO);
                    updatedDispute.setDisputeId(existingDispute.getDisputeId());
                    updatedDispute.setCardId(cardId);
                    
                    return repository.save(updatedDispute);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteDispute(Long cardId, Long disputeId) {
        return repository.findByDisputeId(disputeId)
                .flatMap(dispute -> {
                    if (!dispute.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardDispute not found for the specified card."));
                    }
                    return repository.delete(dispute);
                });
    }
}
