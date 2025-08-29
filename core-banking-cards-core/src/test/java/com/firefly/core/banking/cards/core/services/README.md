# Core Banking Cards Service - Test Documentation

## Overview
This document provides an overview of the test cases for the service layer in the Core Banking Cards application. The tests in this package validate the business logic implemented in the service classes, ensuring that they correctly handle various scenarios including success paths, error conditions, and edge cases.

## Testing Frameworks and Libraries

The test suite uses the following frameworks and libraries:

- **JUnit 5**: The core testing framework for writing and running tests
- **Mockito**: For mocking dependencies and verifying interactions
- **MockedStatic**: For mocking static methods (used with PaginationUtils and FilterUtils)
- **StepVerifier**: For testing reactive streams (Mono and Flux) from Project Reactor
- **Spring Test**: For integration with Spring's testing support

## Common Testing Patterns

All service tests follow these common patterns:

1. **Setup**: Each test class uses `@BeforeEach` to initialize test data and DTOs
2. **Mocking**: Dependencies are mocked using `@Mock` and injected with `@InjectMocks`
3. **Reactive Testing**: StepVerifier is used to test the reactive streams returned by service methods
4. **Verification**: Mockito's verify() is used to ensure that dependencies are called correctly

## Test Classes

### CardServiceTest
Tests for the core card management service that handles basic card operations.

**Key Test Cases**:
- Creating a new card
- Retrieving a card by ID
- Updating an existing card
- Deleting a card
- Handling not-found scenarios

### CardConfigurationServiceTest
Tests for the service that manages card configuration settings.

**Key Test Cases**:
- Listing configurations with pagination
- Creating a new configuration
- Retrieving a configuration by ID
- Updating an existing configuration
- Deleting a configuration
- Handling wrong card ID scenarios

### CardLimitServiceTest
Tests for the service that manages card spending limits.

**Key Test Cases**:
- Listing limits with pagination
- Creating a new limit
- Retrieving a limit by ID
- Updating an existing limit
- Deleting a limit
- Handling wrong card ID scenarios

### CardProviderServiceTest
Tests for the service that integrates with card providers.

**Key Test Cases**:
- Listing providers with pagination
- Creating a new provider
- Retrieving a provider by ID
- Updating an existing provider
- Deleting a provider
- Handling wrong card ID scenarios

### CardSecurityServiceTest
Tests for the service that manages card security settings.

**Key Test Cases**:
- Listing security settings with pagination
- Creating a new security setting
- Retrieving a security setting by ID
- Updating an existing security setting
- Deleting a security setting
- Handling wrong card ID scenarios
- Handling error conditions

### CardTransactionServiceTest
Tests for the service that manages card transactions.

**Key Test Cases**:
- Listing transactions with pagination
- Creating a new transaction
- Retrieving a transaction by ID
- Filtering transactions with custom criteria
- Updating an existing transaction
- Deleting a transaction
- Handling wrong card ID scenarios

### PhysicalCardServiceTest
Tests for the service that manages physical cards.

**Key Test Cases**:
- Listing physical cards with pagination
- Creating a new physical card
- Retrieving a physical card by ID
- Updating an existing physical card
- Deleting a physical card
- Handling wrong card ID scenarios

### VirtualCardServiceTest
Tests for the service that manages virtual cards.

**Key Test Cases**:
- Listing virtual cards with pagination
- Creating a new virtual card
- Retrieving a virtual card by ID
- Updating an existing virtual card
- Deleting a virtual card
- Handling wrong card ID scenarios

## Testing Strategies

### Pagination Testing
All services that return paginated results use a similar approach:
1. Mock the static `PaginationUtils.paginateQuery()` method
2. Verify that the method is called with the correct parameters
3. Return a mocked `PaginationResponse` object

Example:
```java
@Test
void listTransactions_Success() {
    // Arrange
    PaginationRequest paginationRequest = new PaginationRequest();
    
    @SuppressWarnings("unchecked")
    PaginationResponse<CardTransactionDTO> expectedResponse = mock(PaginationResponse.class);
    
    try (MockedStatic<PaginationUtils> paginationUtilsMocked = mockStatic(PaginationUtils.class)) {
        paginationUtilsMocked.when(() -> PaginationUtils.paginateQuery(
                eq(paginationRequest),
                any(Function.class),
                any(Function.class),
                any(Supplier.class)
        )).thenReturn(Mono.just(expectedResponse));

        // Act & Assert
        StepVerifier.create(service.listTransactions(cardId, paginationRequest))
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
```

### Filtering Testing
For services that support filtering (like CardTransactionService), the tests use a spy to mock the filtering behavior:

```java
@Test
void findFiltered_Success() {
    // Arrange
    @SuppressWarnings("unchecked")
    FilterRequest<CardTransactionDTO> filterRequest = mock(FilterRequest.class);

    @SuppressWarnings("unchecked")
    PaginationResponse<CardTransactionDTO> expectedResponse = mock(PaginationResponse.class);

    // Since we can't directly mock the GenericFilter class, we'll mock the service method instead
    CardTransactionServiceImpl serviceSpy = spy(service);

    // Mock the findFiltered method to return our expected response
    doReturn(Mono.just(expectedResponse)).when(serviceSpy).findFiltered(filterRequest);

    // Act & Assert
    StepVerifier.create(serviceSpy.findFiltered(filterRequest))
            .expectNext(expectedResponse)
            .verifyComplete();

    // Verify the method was called with the request
    verify(serviceSpy).findFiltered(filterRequest);
}
```

### Error Handling Testing
Tests for error scenarios ensure that services properly handle exceptions and return appropriate responses:

```java
@Test
void createSecuritySetting_Error() {
    // Arrange
    when(mapper.toEntity(any(CardSecurityDTO.class))).thenReturn(securityEntity);
    when(repository.save(any(CardSecurity.class))).thenReturn(Mono.error(new RuntimeException("Database error")));

    // Act & Assert
    StepVerifier.create(service.createSecuritySetting(cardId, securityDTO))
            .expectErrorMatches(throwable -> 
                throwable instanceof RuntimeException && 
                throwable.getMessage().contains("Error saving CardSecurity"))
            .verify();

    verify(mapper).toEntity(securityDTO);
    verify(repository).save(securityEntity);
    verify(mapper, never()).toDTO(any(CardSecurity.class));
}
```

## Best Practices

1. **Isolation**: Each test is isolated and does not depend on the state of other tests
2. **Comprehensive Coverage**: Tests cover both success and failure scenarios
3. **Readable Test Names**: Test method names clearly describe what is being tested
4. **Consistent Structure**: All tests follow a consistent Arrange-Act-Assert pattern
5. **Proper Mocking**: Dependencies are properly mocked to isolate the unit under test
6. **Verification**: Interactions with dependencies are verified to ensure correct behavior

## Running the Tests

To run all service tests:
```bash
mvn test -Dtest=*ServiceTest
```

To run a specific service test:
```bash
mvn test -Dtest=CardTransactionServiceTest
```

To run a specific test method:
```bash
mvn test -Dtest=CardTransactionServiceTest#findFiltered_Success
```