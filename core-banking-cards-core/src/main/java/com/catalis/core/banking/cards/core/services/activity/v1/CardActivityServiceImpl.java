package com.catalis.core.banking.cards.core.services.activity.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.activity.v1.CardActivityMapper;
import com.catalis.core.banking.cards.interfaces.dtos.activity.v1.CardActivityDTO;
import com.catalis.core.banking.cards.models.entities.activity.v1.CardActivity;
import com.catalis.core.banking.cards.models.repositories.activity.v1.CardActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardActivityServiceImpl implements CardActivityService {

    @Autowired
    private CardActivityRepository repository;

    @Autowired
    private CardActivityMapper mapper;

    @Override
    public Mono<PaginationResponse<CardActivityDTO>> listActivities(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardActivityDTO> createActivity(Long cardId, CardActivityDTO activityDTO) {
        activityDTO.setCardId(cardId);
        CardActivity entity = mapper.toEntity(activityDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardActivityDTO> getActivity(Long cardId, Long activityId) {
        return repository.findByActivityId(activityId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardActivity not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardActivityDTO> updateActivity(Long cardId, Long activityId, CardActivityDTO activityDTO) {
        return repository.findByActivityId(activityId)
                .flatMap(existingActivity -> {
                    if (!existingActivity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardActivity not found for the specified card."));
                    }
                    
                    CardActivity updatedActivity = mapper.toEntity(activityDTO);
                    updatedActivity.setActivityId(existingActivity.getActivityId());
                    updatedActivity.setCardId(cardId);
                    
                    return repository.save(updatedActivity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteActivity(Long cardId, Long activityId) {
        return repository.findByActivityId(activityId)
                .flatMap(activity -> {
                    if (!activity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardActivity not found for the specified card."));
                    }
                    return repository.delete(activity);
                });
    }
}
