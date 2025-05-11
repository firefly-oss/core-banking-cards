package com.catalis.core.banking.cards.core.services.enrollment.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.enrollment.v1.CardEnrollmentMapper;
import com.catalis.core.banking.cards.interfaces.dtos.enrollment.v1.CardEnrollmentDTO;
import com.catalis.core.banking.cards.models.entities.enrollment.v1.CardEnrollment;
import com.catalis.core.banking.cards.models.repositories.enrollment.v1.CardEnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardEnrollmentServiceTest {

    @Mock
    private CardEnrollmentRepository repository;

    @Mock
    private CardEnrollmentMapper mapper;

    @InjectMocks
    private CardEnrollmentServiceImpl service;

    private CardEnrollmentDTO enrollmentDTO;
    private CardEnrollment enrollmentEntity;
    private final Long cardId = 1L;
    private final Long enrollmentId = 2L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        enrollmentDTO = CardEnrollmentDTO.builder()
                .enrollmentId(enrollmentId)
                .cardId(cardId)
                .programName("Rewards Program")
                .enrollmentDate(LocalDateTime.now())
                .status("ACTIVE")
                .build();

        enrollmentEntity = new CardEnrollment();
        enrollmentEntity.setEnrollmentId(enrollmentId);
        enrollmentEntity.setCardId(cardId);
        enrollmentEntity.setProgramName("Rewards Program");
        enrollmentEntity.setEnrollmentDate(LocalDateTime.now());
        enrollmentEntity.setStatus("ACTIVE");
    }

    @Test
    void listEnrollments_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<CardEnrollmentDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listEnrollments(cardId, paginationRequest))
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
    void createEnrollment_Success() {
        // Arrange
        when(mapper.toEntity(any(CardEnrollmentDTO.class))).thenReturn(enrollmentEntity);
        when(repository.save(any(CardEnrollment.class))).thenReturn(Mono.just(enrollmentEntity));
        when(mapper.toDTO(any(CardEnrollment.class))).thenReturn(enrollmentDTO);

        // Act & Assert
        StepVerifier.create(service.createEnrollment(cardId, enrollmentDTO))
                .expectNext(enrollmentDTO)
                .verifyComplete();

        verify(mapper).toEntity(enrollmentDTO);
        verify(repository).save(enrollmentEntity);
        verify(mapper).toDTO(enrollmentEntity);
    }

    @Test
    void getEnrollment_Success() {
        // Arrange
        when(repository.findByEnrollmentId(enrollmentId)).thenReturn(Mono.just(enrollmentEntity));
        when(mapper.toDTO(any(CardEnrollment.class))).thenReturn(enrollmentDTO);

        // Act & Assert
        StepVerifier.create(service.getEnrollment(cardId, enrollmentId))
                .expectNext(enrollmentDTO)
                .verifyComplete();

        verify(repository).findByEnrollmentId(enrollmentId);
        verify(mapper).toDTO(enrollmentEntity);
    }

    @Test
    void getEnrollment_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByEnrollmentId(enrollmentId)).thenReturn(Mono.just(enrollmentEntity));

        // Act & Assert
        StepVerifier.create(service.getEnrollment(wrongCardId, enrollmentId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByEnrollmentId(enrollmentId);
        verify(mapper, never()).toDTO(any(CardEnrollment.class));
    }

    @Test
    void updateEnrollment_Success() {
        // Arrange
        when(repository.findByEnrollmentId(enrollmentId)).thenReturn(Mono.just(enrollmentEntity));
        when(mapper.toEntity(any(CardEnrollmentDTO.class))).thenReturn(enrollmentEntity);
        when(repository.save(any(CardEnrollment.class))).thenReturn(Mono.just(enrollmentEntity));
        when(mapper.toDTO(any(CardEnrollment.class))).thenReturn(enrollmentDTO);

        // Act & Assert
        StepVerifier.create(service.updateEnrollment(cardId, enrollmentId, enrollmentDTO))
                .expectNext(enrollmentDTO)
                .verifyComplete();

        verify(repository).findByEnrollmentId(enrollmentId);
        verify(mapper).toEntity(enrollmentDTO);
        verify(repository).save(enrollmentEntity);
        verify(mapper).toDTO(enrollmentEntity);
    }

    @Test
    void updateEnrollment_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByEnrollmentId(enrollmentId)).thenReturn(Mono.just(enrollmentEntity));

        // Act & Assert
        StepVerifier.create(service.updateEnrollment(wrongCardId, enrollmentId, enrollmentDTO))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByEnrollmentId(enrollmentId);
        verify(repository, never()).save(any(CardEnrollment.class));
        verify(mapper, never()).toDTO(any(CardEnrollment.class));
    }

    @Test
    void deleteEnrollment_Success() {
        // Arrange
        when(repository.findByEnrollmentId(enrollmentId)).thenReturn(Mono.just(enrollmentEntity));
        when(repository.delete(enrollmentEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteEnrollment(cardId, enrollmentId))
                .verifyComplete();

        verify(repository).findByEnrollmentId(enrollmentId);
        verify(repository).delete(enrollmentEntity);
    }

    @Test
    void deleteEnrollment_WrongCardId() {
        // Arrange
        Long wrongCardId = 999L;
        when(repository.findByEnrollmentId(enrollmentId)).thenReturn(Mono.just(enrollmentEntity));

        // Act & Assert
        StepVerifier.create(service.deleteEnrollment(wrongCardId, enrollmentId))
                .expectError(RuntimeException.class)
                .verify();

        verify(repository).findByEnrollmentId(enrollmentId);
        verify(repository, never()).delete(any(CardEnrollment.class));
    }
}
