package com.firefly.core.banking.cards.core.services.payment.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.payment.v1.CardPaymentMapper;
import com.firefly.core.banking.cards.interfaces.dtos.payment.v1.CardPaymentDTO;
import com.firefly.core.banking.cards.models.entities.payment.v1.CardPayment;
import com.firefly.core.banking.cards.models.repositories.payment.v1.CardPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class CardPaymentServiceImpl implements CardPaymentService {

    @Autowired
    private CardPaymentRepository repository;

    @Autowired
    private CardPaymentMapper mapper;

    @Override
    public Mono<PaginationResponse<CardPaymentDTO>> listPayments(Long cardId, PaginationRequest paginationRequest) {
        return PaginationUtils.paginateQuery(
                paginationRequest,
                mapper::toDTO,
                pageable -> repository.findByCardId(cardId, pageable),
                () -> repository.countByCardId(cardId)
        );
    }

    @Override
    public Mono<CardPaymentDTO> createPayment(Long cardId, CardPaymentDTO paymentDTO) {
        paymentDTO.setCardId(cardId);
        CardPayment entity = mapper.toEntity(paymentDTO);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CardPaymentDTO> getPayment(Long cardId, Long paymentId) {
        return repository.findByPaymentId(paymentId)
                .flatMap(entity -> {
                    if (!entity.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardPayment not found for the specified card."));
                    }
                    return Mono.just(mapper.toDTO(entity));
                });
    }

    @Override
    public Mono<CardPaymentDTO> updatePayment(Long cardId, Long paymentId, CardPaymentDTO paymentDTO) {
        return repository.findByPaymentId(paymentId)
                .flatMap(existingPayment -> {
                    if (!existingPayment.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardPayment not found for the specified card."));
                    }
                    
                    CardPayment updatedPayment = mapper.toEntity(paymentDTO);
                    updatedPayment.setPaymentId(existingPayment.getPaymentId());
                    updatedPayment.setCardId(cardId);
                    
                    return repository.save(updatedPayment);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> deletePayment(Long cardId, Long paymentId) {
        return repository.findByPaymentId(paymentId)
                .flatMap(payment -> {
                    if (!payment.getCardId().equals(cardId)) {
                        return Mono.error(new RuntimeException("CardPayment not found for the specified card."));
                    }
                    return repository.delete(payment);
                });
    }
}
