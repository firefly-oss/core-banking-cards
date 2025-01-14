package com.catalis.core.banking.cards.core.services.security.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.security.v1.CardSecurityMapper;
import com.catalis.core.banking.cards.interfaces.dtos.security.v1.CardSecurityDTO;
import com.catalis.core.banking.cards.models.entities.security.v1.CardSecurity;
import com.catalis.core.banking.cards.models.repositories.security.v1.CardSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardSecurityServiceImpl implements CardSecurityService {

    @Autowired
    private CardSecurityRepository repository;

    @Autowired
    private CardSecurityMapper mapper;

    @Override
    public Mono<PaginationResponse<CardSecurityDTO>> listSecuritySettings(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardSecurityDTO> createSecuritySetting(Long cardId, CardSecurityDTO securityDTO) {
        CardSecurity entity = mapper.toEntity(securityDTO);
        entity.setCardId(cardId);

        return repository.save(entity)
                .map(mapper::toDTO)
                .onErrorResume(throwable -> Mono.error(new RuntimeException("Error saving CardSecurity: " + throwable.getMessage())));
    }

    @Override
    public Mono<CardSecurityDTO> getSecuritySetting(Long cardId, Long securityId) {
        return repository.findByCardSecurityId(securityId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardSecurity not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardSecurityDTO> updateSecuritySetting(Long cardId, Long securityId, CardSecurityDTO securityDTO) {
        return repository.findByCardSecurityId(securityId)
                .flatMap(existingEntity -> {
                    if (!existingEntity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardSecurity not found for the specified card."));
                    }

                    CardSecurity updatedEntity = mapper.toEntity(securityDTO);
                    updatedEntity.setCardSecurityId(securityId);
                    updatedEntity.setCardId(cardId);

                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteSecuritySetting(Long cardId, Long securityId) {
        return repository.findByCardSecurityId(securityId)
                .flatMap(existingEntity -> {
                    if (!existingEntity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardSecurity not found for the specified card."));
                    }

                    return repository.deleteById(securityId);
                });
    }
}
