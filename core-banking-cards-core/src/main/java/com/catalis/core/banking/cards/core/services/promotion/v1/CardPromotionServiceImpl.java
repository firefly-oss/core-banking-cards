package com.catalis.core.banking.cards.core.services.promotion.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.promotion.v1.CardPromotionMapper;
import com.catalis.core.banking.cards.interfaces.dtos.promotion.v1.CardPromotionDTO;
import com.catalis.core.banking.cards.models.entities.promotion.v1.CardPromotion;
import com.catalis.core.banking.cards.models.repositories.promotion.v1.CardPromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardPromotionServiceImpl implements CardPromotionService {

    @Autowired
    private CardPromotionRepository repository;

    @Autowired
    private CardPromotionMapper mapper;

    @Override
    public Mono<PaginationResponse<CardPromotionDTO>> listPromotions(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardPromotionDTO> createPromotion(Long cardId, CardPromotionDTO promotionDTO) {
        promotionDTO.setCardId(cardId);
        CardPromotion entity = mapper.toEntity(promotionDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardPromotionDTO> getPromotion(Long cardId, Long promotionId) {
        return repository.findByPromotionId(promotionId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardPromotion not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardPromotionDTO> updatePromotion(Long cardId, Long promotionId, CardPromotionDTO promotionDTO) {
        return repository.findByPromotionId(promotionId)
                .flatMap(existingPromotion -> {
                    if (!existingPromotion.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardPromotion not found for the specified card."));
                    }
                    
                    CardPromotion updatedPromotion = mapper.toEntity(promotionDTO);
                    updatedPromotion.setPromotionId(existingPromotion.getPromotionId());
                    updatedPromotion.setCardId(cardId);
                    
                    return repository.save(updatedPromotion);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deletePromotion(Long cardId, Long promotionId) {
        return repository.findByPromotionId(promotionId)
                .flatMap(promotion -> {
                    if (!promotion.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardPromotion not found for the specified card."));
                    }
                    return repository.delete(promotion);
                });
    }
}
