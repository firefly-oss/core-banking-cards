package com.catalis.core.banking.cards.core.services.bin.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.bin.v1.BINMapper;
import com.catalis.core.banking.cards.interfaces.dtos.bin.v1.BINDTO;
import com.catalis.core.banking.cards.models.entities.bin.v1.BIN;
import com.catalis.core.banking.cards.models.repositories.bin.v1.BINRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BINServiceTest {

    @Mock
    private BINRepository repository;

    @Mock
    private BINMapper mapper;

    @InjectMocks
    private BINServiceImpl service;

    private BINDTO binDTO;
    private BIN binEntity;
    private final Long binId = 1L;
    private final String binNumber = "123456";

    @BeforeEach
    void setUp() {
        // Initialize test data
        LocalDateTime now = LocalDateTime.now();

        binDTO = BINDTO.builder()
                .binId(binId)
                .binNumber(binNumber)
                .binLength(6)
                .issuerId(1L)
                .cardNetworkId(1L)
                .cardTypeId(1L)
                .countryCode("USA")
                .currencyCode("USD")
                .isActive(true)
                .description("Test BIN")
                .build();

        binEntity = new BIN();
        binEntity.setBinId(binId);
        binEntity.setBinNumber(binNumber);
        binEntity.setBinLength(6);
        binEntity.setIssuerId(1L);
        binEntity.setCardNetworkId(1L);
        binEntity.setCardTypeId(1L);
        binEntity.setCountryCode("USA");
        binEntity.setCurrencyCode("USD");
        binEntity.setIsActive(true);
        binEntity.setDescription("Test BIN");
    }

    @Test
    void listBINs_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<BINDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listBINs(paginationRequest))
                    .expectNext(expectedResponse)
                    .verifyComplete();

            // Verify that PaginationUtils.paginateQuery was called with the correct parameters
            paginationUtilsMocked.verify(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            ));
        }
    }

    @Test
    void createBIN_Success() {
        // Arrange
        when(mapper.toEntity(any(BINDTO.class))).thenReturn(binEntity);
        when(repository.save(any(BIN.class))).thenReturn(Mono.just(binEntity));
        when(mapper.toDTO(any(BIN.class))).thenReturn(binDTO);

        // Act & Assert
        StepVerifier.create(service.createBIN(binDTO))
                .expectNext(binDTO)
                .verifyComplete();

        verify(mapper).toEntity(binDTO);
        verify(repository).save(binEntity);
        verify(mapper).toDTO(binEntity);
    }

    @Test
    void getBIN_Success() {
        // Arrange
        when(repository.findByBinId(binId)).thenReturn(Mono.just(binEntity));
        when(mapper.toDTO(any(BIN.class))).thenReturn(binDTO);

        // Act & Assert
        StepVerifier.create(service.getBIN(binId))
                .expectNext(binDTO)
                .verifyComplete();

        verify(repository).findByBinId(binId);
        verify(mapper).toDTO(binEntity);
    }

    @Test
    void getBIN_NotFound() {
        // Arrange
        when(repository.findByBinId(binId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getBIN(binId))
                .verifyComplete();

        verify(repository).findByBinId(binId);
        verify(mapper, never()).toDTO(any(BIN.class));
    }

    @Test
    void getBINByNumber_Success() {
        // Arrange
        when(repository.findByBinNumber(binNumber)).thenReturn(Mono.just(binEntity));
        when(mapper.toDTO(any(BIN.class))).thenReturn(binDTO);

        // Act & Assert
        StepVerifier.create(service.getBINByNumber(binNumber))
                .expectNext(binDTO)
                .verifyComplete();

        verify(repository).findByBinNumber(binNumber);
        verify(mapper).toDTO(binEntity);
    }

    @Test
    void getBINByNumber_NotFound() {
        // Arrange
        when(repository.findByBinNumber(binNumber)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getBINByNumber(binNumber))
                .verifyComplete();

        verify(repository).findByBinNumber(binNumber);
        verify(mapper, never()).toDTO(any(BIN.class));
    }

    @Test
    void updateBIN_Success() {
        // Arrange
        when(repository.findByBinId(binId)).thenReturn(Mono.just(binEntity));
        when(mapper.toEntity(any(BINDTO.class))).thenReturn(binEntity);
        when(repository.save(any(BIN.class))).thenReturn(Mono.just(binEntity));
        when(mapper.toDTO(any(BIN.class))).thenReturn(binDTO);

        // Act & Assert
        StepVerifier.create(service.updateBIN(binId, binDTO))
                .expectNext(binDTO)
                .verifyComplete();

        verify(repository).findByBinId(binId);
        verify(mapper).toEntity(binDTO);
        verify(repository).save(binEntity);
        verify(mapper).toDTO(binEntity);
    }

    @Test
    void updateBIN_NotFound() {
        // Arrange
        when(repository.findByBinId(binId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateBIN(binId, binDTO))
                .verifyComplete();

        verify(repository).findByBinId(binId);
        verify(mapper, never()).toEntity(any(BINDTO.class));
        verify(repository, never()).save(any(BIN.class));
        verify(mapper, never()).toDTO(any(BIN.class));
    }

    @Test
    void deleteBIN_Success() {
        // Arrange
        when(repository.findByBinId(binId)).thenReturn(Mono.just(binEntity));
        when(repository.delete(binEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteBIN(binId))
                .verifyComplete();

        verify(repository).findByBinId(binId);
        verify(repository).delete(binEntity);
    }

    @Test
    void deleteBIN_NotFound() {
        // Arrange
        when(repository.findByBinId(binId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteBIN(binId))
                .verifyComplete();

        verify(repository).findByBinId(binId);
        verify(repository, never()).delete(any(BIN.class));
    }
}
