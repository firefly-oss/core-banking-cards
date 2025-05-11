package com.catalis.core.banking.cards.core.services.customer.v1;

import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.common.core.queries.PaginationUtils;
import com.catalis.core.banking.cards.core.mappers.customer.v1.CardCustomerMapper;
import com.catalis.core.banking.cards.interfaces.dtos.customer.v1.CardCustomerDTO;
import com.catalis.core.banking.cards.models.entities.customer.v1.CardCustomer;
import com.catalis.core.banking.cards.models.repositories.customer.v1.CardCustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CardCustomerServiceTest {

    @Mock
    private CardCustomerRepository repository;

    @Mock
    private CardCustomerMapper mapper;

    @InjectMocks
    private CardCustomerServiceImpl service;

    private CardCustomerDTO customerDTO;
    private CardCustomer customerEntity;
    private final Long customerId = 1L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        customerDTO = CardCustomerDTO.builder()
                .customerId(customerId)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        customerEntity = new CardCustomer();
        customerEntity.setCustomerId(customerId);
        customerEntity.setFirstName("John");
        customerEntity.setLastName("Doe");
        customerEntity.setEmail("john.doe@example.com");
        customerEntity.setPhone("1234567890");
        customerEntity.setDateOfBirth(LocalDate.of(1990, 1, 1));
    }

    @Test
    void listCustomers_Success() {
        // Arrange
        PaginationRequest paginationRequest = new PaginationRequest();

        @SuppressWarnings("unchecked")
        PaginationResponse<CardCustomerDTO> expectedResponse = mock(PaginationResponse.class);

        try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
            paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                    eq(paginationRequest),
                    any(Function.class),
                    any(Function.class),
                    any(Supplier.class)
            )).thenReturn(Mono.just(expectedResponse));

            // Act & Assert
            StepVerifier.create(service.listCustomers(paginationRequest))
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
    void createCustomer_Success() {
        // Arrange
        when(mapper.toEntity(any(CardCustomerDTO.class))).thenReturn(customerEntity);
        when(repository.save(any(CardCustomer.class))).thenReturn(Mono.just(customerEntity));
        when(mapper.toDTO(any(CardCustomer.class))).thenReturn(customerDTO);

        // Act & Assert
        StepVerifier.create(service.createCustomer(customerDTO))
                .expectNext(customerDTO)
                .verifyComplete();

        verify(mapper).toEntity(customerDTO);
        verify(repository).save(customerEntity);
        verify(mapper).toDTO(customerEntity);
    }

    @Test
    void getCustomer_Success() {
        // Arrange
        when(repository.findByCustomerId(customerId)).thenReturn(Mono.just(customerEntity));
        when(mapper.toDTO(any(CardCustomer.class))).thenReturn(customerDTO);

        // Act & Assert
        StepVerifier.create(service.getCustomer(customerId))
                .expectNext(customerDTO)
                .verifyComplete();

        verify(repository).findByCustomerId(customerId);
        verify(mapper).toDTO(customerEntity);
    }

    @Test
    void getCustomer_NotFound() {
        // Arrange
        when(repository.findByCustomerId(customerId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.getCustomer(customerId))
                .verifyComplete();

        verify(repository).findByCustomerId(customerId);
        verify(mapper, never()).toDTO(any(CardCustomer.class));
    }

    @Test
    void updateCustomer_Success() {
        // Arrange
        when(repository.findByCustomerId(customerId)).thenReturn(Mono.just(customerEntity));
        when(mapper.toEntity(any(CardCustomerDTO.class))).thenReturn(customerEntity);
        when(repository.save(any(CardCustomer.class))).thenReturn(Mono.just(customerEntity));
        when(mapper.toDTO(any(CardCustomer.class))).thenReturn(customerDTO);

        // Act & Assert
        StepVerifier.create(service.updateCustomer(customerId, customerDTO))
                .expectNext(customerDTO)
                .verifyComplete();

        verify(repository).findByCustomerId(customerId);
        verify(mapper).toEntity(customerDTO);
        verify(repository).save(customerEntity);
        verify(mapper).toDTO(customerEntity);
    }

    @Test
    void updateCustomer_NotFound() {
        // Arrange
        when(repository.findByCustomerId(customerId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.updateCustomer(customerId, customerDTO))
                .verifyComplete();

        verify(repository).findByCustomerId(customerId);
        verify(repository, never()).save(any(CardCustomer.class));
        verify(mapper, never()).toDTO(any(CardCustomer.class));
    }

    @Test
    void deleteCustomer_Success() {
        // Arrange
        when(repository.findByCustomerId(customerId)).thenReturn(Mono.just(customerEntity));
        when(repository.delete(customerEntity)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteCustomer(customerId))
                .verifyComplete();

        verify(repository).findByCustomerId(customerId);
        verify(repository).delete(customerEntity);
    }

    @Test
    void deleteCustomer_NotFound() {
        // Arrange
        when(repository.findByCustomerId(customerId)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.deleteCustomer(customerId))
                .verifyComplete();

        verify(repository).findByCustomerId(customerId);
        verify(repository, never()).delete(any(CardCustomer.class));
    }
}
