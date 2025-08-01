package com.catalis.core.banking.cards.core.services.payment.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.payment.v1.CardPaymentMapper;
import com.catalis.core.banking.cards.interfaces.dtos.payment.v1.CardPaymentDTO;
import com.catalis.core.banking.cards.models.entities.payment.v1.CardPayment;
import com.catalis.core.banking.cards.models.repositories.payment.v1.CardPaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardPaymentServiceTest {

    @Mock
    private CardPaymentRepository repository;

    @Mock
    private CardPaymentMapper mapper;

    @InjectMocks
    private CardPaymentServiceImpl service;

    private CardPaymentDTO paymentDTO;
    private CardPayment paymentEntity;
    private final Long cardId = 1L;
    private final Long paymentId = 2L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        LocalDateTime now = LocalDateTime.now();
        paymentDTO = CardPaymentDTO.builder()
                .paymentId(paymentId)
                .cardId(cardId)
                .paymentAmount(new BigDecimal("250.00"))
                .paymentTimestamp(now)
                .paymentMethod("BANK_TRANSFER")
                .paymentStatus("COMPLETED")
                .build();

        paymentEntity = new CardPayment();
        paymentEntity.setPaymentId(paymentId);
        paymentEntity.setCardId(cardId);
        paymentEntity.setPaymentAmount(new BigDecimal("250.00"));
        paymentEntity.setPaymentTimestamp(now);
        paymentEntity.setPaymentMethod("BANK_TRANSFER");
        paymentEntity.setPaymentStatus("COMPLETED");
    }

    @Test
    void listPayments_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<CardPaymentDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listPayments(cardId, paginationRequest))
                    .expectNext(expectedResponse)
                    .verifyComplete();

            paginationUtilsMocked.verify(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            ));
        }
    }

    @Test
    void createPayment_Success() {
        // Arrange
        when(mapper.toEntity(any(CardPaymentDTO.class))).thenReturn(paymentEntity);
        when(repository.save(any(CardPayment.class))).thenReturn(Mono.just(paymentEntity));
        when(mapper.toDTO(any(CardPayment.class))).thenReturn(paymentDTO);

        // Act & Assert
        StepVerifier.create(service.createPayment(cardId, paymentDTO))
                .expectNext(paymentDTO)
                .verifyComplete();

        verify(mapper).toEntity(paymentDTO);
        verify(repository).save(paymentEntity);
        verify(mapper).toDTO(paymentEntity);
    }

    @Test
    void getPayment_Success() {
        // Arrange
        when(repository.findByPaymentId(paymentId)).thenReturn(Mono.just(paymentEntity));
        when(mapper.toDTO(any(CardPayment.class))).thenReturn(paymentDTO);

        // Act & Assert
        StepVerifier.create(service.getPayment(cardId, paymentId))
                .expectNext(paymentDTO)
                .verifyComplete();

        verify(repository).findByPaymentId(paymentId);
        verify(mapper).toDTO(paymentEntity);
    }

    @Test
    void getPayment_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByPaymentId(paymentId)).thenReturn(Mono.just(paymentEntity));

        // Act & Assert
        StepVerifier.create(service.getPayment(wrongCardId, paymentId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByPaymentId(paymentId);
        verify(mapper, never()).toDTO(any(CardPayment.class));
    }

    @Test
    void updatePayment_Success() {
        // Arrange
        when(repository.findByPaymentId(paymentId)).thenReturn(Mono.just(paymentEntity));
        when(mapper.toEntity(any(CardPaymentDTO.class))).thenReturn(paymentEntity);
        when(repository.save(any(CardPayment.class))).thenReturn(Mono.just(paymentEntity));
        when(mapper.toDTO(any(CardPayment.class))).thenReturn(paymentDTO);

        // Act & Assert
        StepVerifier.create(service.updatePayment(cardId, paymentId, paymentDTO))
                .expectNext(paymentDTO)
                .verifyComplete();

        verify(repository).findByPaymentId(paymentId);
        verify(mapper).toEntity(paymentDTO);
        verify(repository).save(paymentEntity);
        verify(mapper).toDTO(paymentEntity);
    }

    @Test
    void updatePayment_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByPaymentId(paymentId)).thenReturn(Mono.just(paymentEntity));

        // Act & Assert
        StepVerifier.create(service.updatePayment(wrongCardId, paymentId, paymentDTO))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByPaymentId(paymentId);
        verify(repository, never()).save(any(CardPayment.class));
        verify(mapper, never()).toDTO(any(CardPayment.class));
    }

    @Test
    void deletePayment_Success() {
        // Arrange
        when(repository.findByPaymentId(paymentId)).thenReturn(Mono.just(paymentEntity));
        when(repository.delete(paymentEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deletePayment(cardId, paymentId))
                .verifyComplete();

        verify(repository).findByPaymentId(paymentId);
        verify(repository).delete(paymentEntity);
    }

    @Test
    void deletePayment_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByPaymentId(paymentId)).thenReturn(Mono.just(paymentEntity));

        // Act & Assert
        StepVerifier.create(service.deletePayment(wrongCardId, paymentId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByPaymentId(paymentId);
        verify(repository, never()).delete(any(CardPayment.class));
    }
}
