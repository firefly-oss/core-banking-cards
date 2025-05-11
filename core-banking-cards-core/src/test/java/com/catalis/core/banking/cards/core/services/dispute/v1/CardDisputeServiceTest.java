package com.catalis.core.banking.cards.core.services.dispute.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.dispute.v1.CardDisputeMapper;
import com.catalis.core.banking.cards.interfaces.dtos.dispute.v1.CardDisputeDTO;
import com.catalis.core.banking.cards.models.entities.dispute.v1.CardDispute;
import com.catalis.core.banking.cards.models.repositories.dispute.v1.CardDisputeRepository;
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
public class CardDisputeServiceTest {

    @Mock
    private CardDisputeRepository repository;

    @Mock
    private CardDisputeMapper mapper;

    @InjectMocks
    private CardDisputeServiceImpl service;

    private CardDisputeDTO disputeDTO;
    private CardDispute disputeEntity;
    private final Long cardId = 1L;
    private final Long disputeId = 2L;
    private final Long transactionId = 3L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        disputeDTO = CardDisputeDTO.builder()
                .disputeId(disputeId)
                .cardId(cardId)
                .transactionId(transactionId)
                .disputeReason("UNAUTHORIZED_TRANSACTION")
                .disputeAmount(new BigDecimal("125.50"))
                .disputeDate(LocalDateTime.now())
                .status("PENDING")
                .build();

        disputeEntity = new CardDispute();
        disputeEntity.setDisputeId(disputeId);
        disputeEntity.setCardId(cardId);
        disputeEntity.setTransactionId(transactionId);
        disputeEntity.setDisputeReason("UNAUTHORIZED_TRANSACTION");
        disputeEntity.setDisputeAmount(new BigDecimal("125.50"));
        disputeEntity.setDisputeDate(LocalDateTime.now());
        disputeEntity.setStatus("PENDING");
    }

    @Test
    void listDisputes_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<CardDisputeDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listDisputes(cardId, paginationRequest))
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
    void createDispute_Success() {
        // Arrange
        when(mapper.toEntity(any(CardDisputeDTO.class))).thenReturn(disputeEntity);
        when(repository.save(any(CardDispute.class))).thenReturn(Mono.just(disputeEntity));
        when(mapper.toDTO(any(CardDispute.class))).thenReturn(disputeDTO);

        // Act & Assert
        StepVerifier.create(service.createDispute(cardId, disputeDTO))
                .expectNext(disputeDTO)
                .verifyComplete();

        verify(mapper).toEntity(disputeDTO);
        verify(repository).save(disputeEntity);
        verify(mapper).toDTO(disputeEntity);
    }

    @Test
    void getDispute_Success() {
        // Arrange
        when(repository.findByDisputeId(disputeId)).thenReturn(Mono.just(disputeEntity));
        when(mapper.toDTO(any(CardDispute.class))).thenReturn(disputeDTO);

        // Act & Assert
        StepVerifier.create(service.getDispute(cardId, disputeId))
                .expectNext(disputeDTO)
                .verifyComplete();

        verify(repository).findByDisputeId(disputeId);
        verify(mapper).toDTO(disputeEntity);
    }

    @Test
    void getDispute_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByDisputeId(disputeId)).thenReturn(Mono.just(disputeEntity));

        // Act & Assert
        StepVerifier.create(service.getDispute(wrongCardId, disputeId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByDisputeId(disputeId);
        verify(mapper, never()).toDTO(any(CardDispute.class));
    }

    @Test
    void updateDispute_Success() {
        // Arrange
        when(repository.findByDisputeId(disputeId)).thenReturn(Mono.just(disputeEntity));
        when(mapper.toEntity(any(CardDisputeDTO.class))).thenReturn(disputeEntity);
        when(repository.save(any(CardDispute.class))).thenReturn(Mono.just(disputeEntity));
        when(mapper.toDTO(any(CardDispute.class))).thenReturn(disputeDTO);

        // Act & Assert
        StepVerifier.create(service.updateDispute(cardId, disputeId, disputeDTO))
                .expectNext(disputeDTO)
                .verifyComplete();

        verify(repository).findByDisputeId(disputeId);
        verify(mapper).toEntity(disputeDTO);
        verify(repository).save(disputeEntity);
        verify(mapper).toDTO(disputeEntity);
    }

    @Test
    void updateDispute_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByDisputeId(disputeId)).thenReturn(Mono.just(disputeEntity));

        // Act & Assert
        StepVerifier.create(service.updateDispute(wrongCardId, disputeId, disputeDTO))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByDisputeId(disputeId);
        verify(repository, never()).save(any(CardDispute.class));
        verify(mapper, never()).toDTO(any(CardDispute.class));
    }

    @Test
    void deleteDispute_Success() {
        // Arrange
        when(repository.findByDisputeId(disputeId)).thenReturn(Mono.just(disputeEntity));
        when(repository.delete(disputeEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteDispute(cardId, disputeId))
                .verifyComplete();

        verify(repository).findByDisputeId(disputeId);
        verify(repository).delete(disputeEntity);
    }

    @Test
    void deleteDispute_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByDisputeId(disputeId)).thenReturn(Mono.just(disputeEntity));

        // Act & Assert
        StepVerifier.create(service.deleteDispute(wrongCardId, disputeId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByDisputeId(disputeId);
        verify(repository, never()).delete(any(CardDispute.class));
    }
}
