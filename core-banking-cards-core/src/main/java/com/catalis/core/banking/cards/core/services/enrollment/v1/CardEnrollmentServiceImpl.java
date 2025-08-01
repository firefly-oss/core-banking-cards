package com.catalis.core.banking.cards.core.services.enrollment.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.enrollment.v1.CardEnrollmentMapper;
import com.catalis.core.banking.cards.interfaces.dtos.enrollment.v1.CardEnrollmentDTO;
import com.catalis.core.banking.cards.models.entities.enrollment.v1.CardEnrollment;
import com.catalis.core.banking.cards.models.repositories.enrollment.v1.CardEnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardEnrollmentServiceImpl implements CardEnrollmentService {

    @Autowired
    private CardEnrollmentRepository repository;

    @Autowired
    private CardEnrollmentMapper mapper;

    @Override
    public Mono<PaginationResponse<CardEnrollmentDTO>> listEnrollments(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardEnrollmentDTO> createEnrollment(Long cardId, CardEnrollmentDTO enrollmentDTO) {
        enrollmentDTO.setCardId(cardId);
        CardEnrollment entity = mapper.toEntity(enrollmentDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardEnrollmentDTO> getEnrollment(Long cardId, Long enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardEnrollment not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardEnrollmentDTO> updateEnrollment(Long cardId, Long enrollmentId, CardEnrollmentDTO enrollmentDTO) {
        return repository.findByEnrollmentId(enrollmentId)
                .flatMap(existingEnrollment -> {
                    if (!existingEnrollment.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardEnrollment not found for the specified card."));
                    }
                    
                    CardEnrollment updatedEnrollment = mapper.toEntity(enrollmentDTO);
                    updatedEnrollment.setEnrollmentId(existingEnrollment.getEnrollmentId());
                    updatedEnrollment.setCardId(cardId);
                    
                    return repository.save(updatedEnrollment);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deleteEnrollment(Long cardId, Long enrollmentId) {
        return repository.findByEnrollmentId(enrollmentId)
                .flatMap(enrollment -> {
                    if (!enrollment.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardEnrollment not found for the specified card."));
                    }
                    return repository.delete(enrollment);
                });
    }
}
