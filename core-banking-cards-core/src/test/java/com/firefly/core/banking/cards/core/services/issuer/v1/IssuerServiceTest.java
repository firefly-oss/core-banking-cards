package com.firefly.core.banking.cards.core.services.issuer.v1;

import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.common.core.queries.PaginationUtils;
import com.firefly.core.banking.cards.core.mappers.issuer.v1.IssuerMapper;
import com.firefly.core.banking.cards.interfaces.dtos.issuer.v1.IssuerDTO;
import com.firefly.core.banking.cards.models.entities.issuer.v1.Issuer;
import com.firefly.core.banking.cards.models.repositories.issuer.v1.IssuerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IssuerServiceTest {

    @Mock
    private IssuerRepository repository;

    @Mock
    private IssuerMapper mapper;

    @InjectMocks
    private IssuerServiceImpl service;

    private IssuerDTO issuerDTO;
    private Issuer issuerEntity;
    private final UUID issuerId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        // Initialize test data
        issuerDTO = IssuerDTO.builder()
                .issuerId(issuerId)
                .issuerName("Test Issuer")
                .issuerCode("TST")
                .description("Test Issuer Description")
                .build();

        issuerEntity = new Issuer();
        issuerEntity.setIssuerId(issuerId);
        issuerEntity.setIssuerName("Test Issuer");
        issuerEntity.setIssuerCode("TST");
        issuerEntity.setDescription("Test Issuer Description");
    }

    @Test
    void listIssuers_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<IssuerDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listIssuers(paginationRequest))
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
    void createIssuer_Success() {
        // Arrange
        when(mapper.toEntity(any(IssuerDTO.class))).thenReturn(issuerEntity);
        when(repository.save(any(Issuer.class))).thenReturn(Mono.just(issuerEntity));
        when(mapper.toDTO(any(Issuer.class))).thenReturn(issuerDTO);

        // Act & Assert
        StepVerifier.create(service.createIssuer(issuerDTO))
                .expectNext(issuerDTO)
                .verifyComplete();

        verify(mapper).toEntity(issuerDTO);
        verify(repository).save(issuerEntity);
        verify(mapper).toDTO(issuerEntity);
    }

    @Test
    void getIssuer_Success() {
        // Arrange
        when(repository.findByIssuerId(issuerId)).thenReturn(Mono.just(issuerEntity));
        when(mapper.toDTO(any(Issuer.class))).thenReturn(issuerDTO);

        // Act & Assert
        StepVerifier.create(service.getIssuer(issuerId))
                .expectNext(issuerDTO)
                .verifyComplete();

        verify(repository).findByIssuerId(issuerId);
        verify(mapper).toDTO(issuerEntity);
    }

    @Test
    void getIssuer_NotFound() {
        // Arrange
        when(repository.findByIssuerId(issuerId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getIssuer(issuerId))
                .verifyComplete();

        verify(repository).findByIssuerId(issuerId);
        verify(mapper, never()).toDTO(any(Issuer.class));
    }

    @Test
    void updateIssuer_Success() {
        // Arrange
        when(repository.findByIssuerId(issuerId)).thenReturn(Mono.just(issuerEntity));
        when(mapper.toEntity(any(IssuerDTO.class))).thenReturn(issuerEntity);
        when(repository.save(any(Issuer.class))).thenReturn(Mono.just(issuerEntity));
        when(mapper.toDTO(any(Issuer.class))).thenReturn(issuerDTO);

        // Act & Assert
        StepVerifier.create(service.updateIssuer(issuerId, issuerDTO))
                .expectNext(issuerDTO)
                .verifyComplete();

        verify(repository).findByIssuerId(issuerId);
        verify(mapper).toEntity(issuerDTO);
        verify(repository).save(issuerEntity);
        verify(mapper).toDTO(issuerEntity);
    }

    @Test
    void updateIssuer_NotFound() {
        // Arrange
        when(repository.findByIssuerId(issuerId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateIssuer(issuerId, issuerDTO))
                .verifyComplete();

        verify(repository).findByIssuerId(issuerId);
        verify(repository, never()).save(any(Issuer.class));
        verify(mapper, never()).toDTO(any(Issuer.class));
    }

    @Test
    void deleteIssuer_Success() {
        // Arrange
        when(repository.findByIssuerId(issuerId)).thenReturn(Mono.just(issuerEntity));
        when(repository.delete(issuerEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteIssuer(issuerId))
                .verifyComplete();

        verify(repository).findByIssuerId(issuerId);
        verify(repository).delete(issuerEntity);
    }

    @Test
    void deleteIssuer_NotFound() {
        // Arrange
        when(repository.findByIssuerId(issuerId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteIssuer(issuerId))
                .verifyComplete();

        verify(repository).findByIssuerId(issuerId);
        verify(repository, never()).delete(any(Issuer.class));
    }
}
