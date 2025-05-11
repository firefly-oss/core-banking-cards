# Core Banking Cards Service API Reference

## Overview

This document provides detailed information about the RESTful APIs exposed by the Core Banking Cards Service. The service follows REST principles and uses standard HTTP methods for all operations.

## Authentication

All API endpoints require authentication. The service uses JWT-based authentication, and a valid token must be included in the `Authorization` header of each request.

## Common Response Codes

| Status Code | Description |
|-------------|-------------|
| 200 | OK - The request was successful |
| 201 | Created - A new resource was successfully created |
| 204 | No Content - The request was successful, but there is no content to return |
| 400 | Bad Request - The request was invalid or cannot be served |
| 401 | Unauthorized - Authentication is required and has failed or has not been provided |
| 403 | Forbidden - The authenticated user does not have access to the requested resource |
| 404 | Not Found - The requested resource could not be found |
| 500 | Internal Server Error - An error occurred on the server |

## API Endpoints

The Core Banking Cards Service exposes the following API endpoints:

### BIN Management

Bank Identification Number (BIN) APIs allow management of BIN records, which are the first 6 to 8 digits of a payment card number that identify the card issuer, card type, and other key attributes.

#### List BINs

```http
GET /api/v1/bins
```

Retrieve a paginated list of all BIN records.

**Query Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| page | integer | Page number (zero-based) |
| size | integer | Number of items per page |
| sort | string | Sort field and direction (e.g., `binNumber,asc`) |

**Response:**

```json
{
  "content": [
    {
      "binId": 1,
      "binNumber": "123456",
      "binLength": 6,
      "issuerId": 1,
      "cardNetworkId": 1,
      "cardTypeId": 1,
      "countryCode": "US",
      "currencyCode": "USD",
      "isActive": true,
      "description": "Sample BIN"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "sorted": true,
      "unsorted": false
    }
  },
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "first": true,
  "empty": false
}
```

#### Create BIN

```http
POST /api/v1/bins
Content-Type: application/json
```

Create a new BIN record.

**Request Body:**

```json
{
  "binNumber": "123456",
  "binLength": 6,
  "issuerId": 1,
  "cardNetworkId": 1,
  "cardTypeId": 1,
  "countryCode": "US",
  "currencyCode": "USD",
  "isActive": true,
  "description": "Sample BIN"
}
```

**Response:**

```json
{
  "binId": 1,
  "binNumber": "123456",
  "binLength": 6,
  "issuerId": 1,
  "cardNetworkId": 1,
  "cardTypeId": 1,
  "countryCode": "US",
  "currencyCode": "USD",
  "isActive": true,
  "description": "Sample BIN"
}
```

#### Get BIN by ID

```http
GET /api/v1/bins/{binId}
```

Retrieve a specific BIN record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| binId | integer | Unique identifier of the BIN |

**Response:**

```json
{
  "binId": 1,
  "binNumber": "123456",
  "binLength": 6,
  "issuerId": 1,
  "cardNetworkId": 1,
  "cardTypeId": 1,
  "countryCode": "US",
  "currencyCode": "USD",
  "isActive": true,
  "description": "Sample BIN"
}
```

#### Get BIN by Number

```http
GET /api/v1/bins/by-number/{binNumber}
```

Retrieve a specific BIN record by its BIN number.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| binNumber | string | BIN number (first 6-8 digits of a card) |

**Response:**

```json
{
  "binId": 1,
  "binNumber": "123456",
  "binLength": 6,
  "issuerId": 1,
  "cardNetworkId": 1,
  "cardTypeId": 1,
  "countryCode": "US",
  "currencyCode": "USD",
  "isActive": true,
  "description": "Sample BIN"
}
```

#### Update BIN

```http
PUT /api/v1/bins/{binId}
Content-Type: application/json
```

Update an existing BIN record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| binId | integer | Unique identifier of the BIN to update |

**Request Body:**

```json
{
  "binNumber": "123456",
  "binLength": 6,
  "issuerId": 1,
  "cardNetworkId": 1,
  "cardTypeId": 1,
  "countryCode": "US",
  "currencyCode": "USD",
  "isActive": true,
  "description": "Updated BIN description"
}
```

**Response:**

```json
{
  "binId": 1,
  "binNumber": "123456",
  "binLength": 6,
  "issuerId": 1,
  "cardNetworkId": 1,
  "cardTypeId": 1,
  "countryCode": "US",
  "currencyCode": "USD",
  "isActive": true,
  "description": "Updated BIN description"
}
```

#### Delete BIN

```http
DELETE /api/v1/bins/{binId}
```

Delete a BIN record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| binId | integer | Unique identifier of the BIN to delete |

**Response:**

No content (204) if successful.
### Card Program Management

Card Program APIs allow management of card program records, which define the rules, configurations, and features for a group of cards.

#### List Card Programs

```http
GET /api/v1/programs
```

Retrieve a paginated list of all card program records.

**Query Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| page | integer | Page number (zero-based) |
| size | integer | Number of items per page |
| sort | string | Sort field and direction (e.g., `programName,asc`) |

**Response:**

```json
{
  "content": [
    {
      "programId": 1,
      "programName": "Premium Credit Card",
      "programCode": "PCC-001",
      "issuerId": 1,
      "binId": 1,
      "cardTypeId": 1,
      "cardNetworkId": 1,
      "defaultDesignId": 1,
      "startDate": "2023-01-01T00:00:00Z",
      "endDate": "2028-12-31T23:59:59Z",
      "isActive": true,
      "maxCardsPerCustomer": 3,
      "defaultDailyLimit": 5000.00,
      "defaultMonthlyLimit": 20000.00,
      "defaultCreditLimit": 10000.00,
      "defaultCardValidityYears": 5,
      "supportsPhysicalCards": true,
      "supportsVirtualCards": true,
      "supportsContactless": true,
      "supportsInternational": true,
      "supportsAtmWithdrawal": true,
      "supportsOnlineTransactions": true,
      "supportsRecurringPayments": true,
      "supportsApplePay": true,
      "supportsGooglePay": true,
      "supportsSamsungPay": true,
      "requiresPin": true,
      "requiresActivation": true,
      "currencyCode": "USD"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "sorted": true,
      "unsorted": false
    }
  },
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "first": true,
  "empty": false
}
```

#### Create Card Program

```http
POST /api/v1/programs
Content-Type: application/json
```

Create a new card program record.

**Request Body:**

```json
{
  "programName": "Premium Credit Card",
  "programCode": "PCC-001",
  "issuerId": 1,
  "binId": 1,
  "cardTypeId": 1,
  "cardNetworkId": 1,
  "defaultDesignId": 1,
  "startDate": "2023-01-01T00:00:00Z",
  "endDate": "2028-12-31T23:59:59Z",
  "isActive": true,
  "maxCardsPerCustomer": 3,
  "defaultDailyLimit": 5000.00,
  "defaultMonthlyLimit": 20000.00,
  "defaultCreditLimit": 10000.00,
  "defaultCardValidityYears": 5,
  "supportsPhysicalCards": true,
  "supportsVirtualCards": true,
  "supportsContactless": true,
  "supportsInternational": true,
  "supportsAtmWithdrawal": true,
  "supportsOnlineTransactions": true,
  "supportsRecurringPayments": true,
  "supportsApplePay": true,
  "supportsGooglePay": true,
  "supportsSamsungPay": true,
  "requiresPin": true,
  "requiresActivation": true,
  "currencyCode": "USD"
}
```

**Response:**

```json
{
  "programId": 1,
  "programName": "Premium Credit Card",
  "programCode": "PCC-001",
  "issuerId": 1,
  "binId": 1,
  "cardTypeId": 1,
  "cardNetworkId": 1,
  "defaultDesignId": 1,
  "startDate": "2023-01-01T00:00:00Z",
  "endDate": "2028-12-31T23:59:59Z",
  "isActive": true,
  "maxCardsPerCustomer": 3,
  "defaultDailyLimit": 5000.00,
  "defaultMonthlyLimit": 20000.00,
  "defaultCreditLimit": 10000.00,
  "defaultCardValidityYears": 5,
  "supportsPhysicalCards": true,
  "supportsVirtualCards": true,
  "supportsContactless": true,
  "supportsInternational": true,
  "supportsAtmWithdrawal": true,
  "supportsOnlineTransactions": true,
  "supportsRecurringPayments": true,
  "supportsApplePay": true,
  "supportsGooglePay": true,
  "supportsSamsungPay": true,
  "requiresPin": true,
  "requiresActivation": true,
  "currencyCode": "USD"
}
```

#### Get Card Program by ID

```http
GET /api/v1/programs/{programId}
```

Retrieve a specific card program record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| programId | integer | Unique identifier of the card program |

**Response:**

```json
{
  "programId": 1,
  "programName": "Premium Credit Card",
  "programCode": "PCC-001",
  "issuerId": 1,
  "binId": 1,
  "cardTypeId": 1,
  "cardNetworkId": 1,
  "defaultDesignId": 1,
  "startDate": "2023-01-01T00:00:00Z",
  "endDate": "2028-12-31T23:59:59Z",
  "isActive": true,
  "maxCardsPerCustomer": 3,
  "defaultDailyLimit": 5000.00,
  "defaultMonthlyLimit": 20000.00,
  "defaultCreditLimit": 10000.00,
  "defaultCardValidityYears": 5,
  "supportsPhysicalCards": true,
  "supportsVirtualCards": true,
  "supportsContactless": true,
  "supportsInternational": true,
  "supportsAtmWithdrawal": true,
  "supportsOnlineTransactions": true,
  "supportsRecurringPayments": true,
  "supportsApplePay": true,
  "supportsGooglePay": true,
  "supportsSamsungPay": true,
  "requiresPin": true,
  "requiresActivation": true,
  "currencyCode": "USD"
}
```

#### Update Card Program

```http
PUT /api/v1/programs/{programId}
Content-Type: application/json
```

Update an existing card program record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| programId | integer | Unique identifier of the card program to update |

**Request Body:**

```json
{
  "programName": "Premium Credit Card Plus",
  "programCode": "PCC-001",
  "issuerId": 1,
  "binId": 1,
  "cardTypeId": 1,
  "cardNetworkId": 1,
  "defaultDesignId": 1,
  "startDate": "2023-01-01T00:00:00Z",
  "endDate": "2028-12-31T23:59:59Z",
  "isActive": true,
  "maxCardsPerCustomer": 5,
  "defaultDailyLimit": 10000.00,
  "defaultMonthlyLimit": 30000.00,
  "defaultCreditLimit": 15000.00,
  "defaultCardValidityYears": 5,
  "supportsPhysicalCards": true,
  "supportsVirtualCards": true,
  "supportsContactless": true,
  "supportsInternational": true,
  "supportsAtmWithdrawal": true,
  "supportsOnlineTransactions": true,
  "supportsRecurringPayments": true,
  "supportsApplePay": true,
  "supportsGooglePay": true,
  "supportsSamsungPay": true,
  "requiresPin": true,
  "requiresActivation": true,
  "currencyCode": "USD"
}
```

**Response:**

```json
{
  "programId": 1,
  "programName": "Premium Credit Card Plus",
  "programCode": "PCC-001",
  "issuerId": 1,
  "binId": 1,
  "cardTypeId": 1,
  "cardNetworkId": 1,
  "defaultDesignId": 1,
  "startDate": "2023-01-01T00:00:00Z",
  "endDate": "2028-12-31T23:59:59Z",
  "isActive": true,
  "maxCardsPerCustomer": 5,
  "defaultDailyLimit": 10000.00,
  "defaultMonthlyLimit": 30000.00,
  "defaultCreditLimit": 15000.00,
  "defaultCardValidityYears": 5,
  "supportsPhysicalCards": true,
  "supportsVirtualCards": true,
  "supportsContactless": true,
  "supportsInternational": true,
  "supportsAtmWithdrawal": true,
  "supportsOnlineTransactions": true,
  "supportsRecurringPayments": true,
  "supportsApplePay": true,
  "supportsGooglePay": true,
  "supportsSamsungPay": true,
  "requiresPin": true,
  "requiresActivation": true,
  "currencyCode": "USD"
}
```

#### Delete Card Program

```http
DELETE /api/v1/programs/{programId}
```

Delete a card program record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| programId | integer | Unique identifier of the card program to delete |

**Response:**

No content (204) if successful.
### Card Management

Card APIs allow management of card records, which are the central entity in the system and are associated with specific card programs, issuers, networks, and account holders.

#### Create Card

```http
POST /api/v1/cards
Content-Type: application/json
```

Create a new card record in the banking system.

**Request Body:**

```json
{
  "cardNumber": "4111111111111111",
  "maskedCardNumber": "411111******1111",
  "cardSequenceNumber": "001",
  "binId": 1,
  "cardTypeId": 1,
  "cardNetworkId": 1,
  "issuerId": 1,
  "contractId": 12345,
  "accountId": 67890,
  "partyId": 54321,
  "cardStatus": "ACTIVE",
  "cardHolderName": "John Doe",
  "cardHolderId": "ID12345",
  "expirationMonth": 12,
  "expirationYear": 2028,
  "cvv": "123",
  "pin": "1234",
  "activationDate": "2023-01-15T10:30:00Z",
  "issuanceDate": "2023-01-15T10:30:00Z",
  "expirationDate": "2028-12-31T23:59:59Z",
  "isPhysical": true,
  "isVirtual": false,
  "isPrimary": true,
  "isActive": true,
  "isLocked": false,
  "dailyLimit": 5000.00,
  "monthlyLimit": 20000.00,
  "creditLimit": 10000.00,
  "availableBalance": 10000.00,
  "currencyCode": "USD",
  "designId": 1
}
```

**Response:**

```json
{
  "cardId": 1,
  "maskedCardNumber": "411111******1111",
  "cardSequenceNumber": "001",
  "binId": 1,
  "cardTypeId": 1,
  "cardNetworkId": 1,
  "issuerId": 1,
  "contractId": 12345,
  "accountId": 67890,
  "partyId": 54321,
  "cardStatus": "ACTIVE",
  "cardHolderName": "John Doe",
  "cardHolderId": "ID12345",
  "expirationMonth": 12,
  "expirationYear": 2028,
  "activationDate": "2023-01-15T10:30:00Z",
  "issuanceDate": "2023-01-15T10:30:00Z",
  "expirationDate": "2028-12-31T23:59:59Z",
  "isPhysical": true,
  "isVirtual": false,
  "isPrimary": true,
  "isActive": true,
  "isLocked": false,
  "dailyLimit": 5000.00,
  "monthlyLimit": 20000.00,
  "creditLimit": 10000.00,
  "availableBalance": 10000.00,
  "currencyCode": "USD",
  "designId": 1
}
```

> Note: Sensitive information like the full card number, CVV, and PIN are not returned in the response for security reasons.

#### Get Card by ID

```http
GET /api/v1/cards/{cardId}
```

Retrieve an existing card record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| cardId | integer | Unique identifier of the card |

**Response:**

```json
{
  "cardId": 1,
  "maskedCardNumber": "411111******1111",
  "cardSequenceNumber": "001",
  "binId": 1,
  "cardTypeId": 1,
  "cardNetworkId": 1,
  "issuerId": 1,
  "contractId": 12345,
  "accountId": 67890,
  "partyId": 54321,
  "cardStatus": "ACTIVE",
  "cardHolderName": "John Doe",
  "cardHolderId": "ID12345",
  "expirationMonth": 12,
  "expirationYear": 2028,
  "activationDate": "2023-01-15T10:30:00Z",
  "issuanceDate": "2023-01-15T10:30:00Z",
  "expirationDate": "2028-12-31T23:59:59Z",
  "isPhysical": true,
  "isVirtual": false,
  "isPrimary": true,
  "isActive": true,
  "isLocked": false,
  "dailyLimit": 5000.00,
  "monthlyLimit": 20000.00,
  "creditLimit": 10000.00,
  "availableBalance": 10000.00,
  "currencyCode": "USD",
  "designId": 1
}
```

#### Update Card

```http
PUT /api/v1/cards/{cardId}
Content-Type: application/json
```

Update an existing card record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| cardId | integer | Unique identifier of the card to update |

**Request Body:**

```json
{
  "cardStatus": "ACTIVE",
  "cardHolderName": "John A. Doe",
  "isActive": true,
  "isLocked": false,
  "dailyLimit": 7500.00,
  "monthlyLimit": 25000.00,
  "creditLimit": 12000.00
}
```

**Response:**

```json
{
  "cardId": 1,
  "maskedCardNumber": "411111******1111",
  "cardSequenceNumber": "001",
  "binId": 1,
  "cardTypeId": 1,
  "cardNetworkId": 1,
  "issuerId": 1,
  "contractId": 12345,
  "accountId": 67890,
  "partyId": 54321,
  "cardStatus": "ACTIVE",
  "cardHolderName": "John A. Doe",
  "cardHolderId": "ID12345",
  "expirationMonth": 12,
  "expirationYear": 2028,
  "activationDate": "2023-01-15T10:30:00Z",
  "issuanceDate": "2023-01-15T10:30:00Z",
  "expirationDate": "2028-12-31T23:59:59Z",
  "isPhysical": true,
  "isVirtual": false,
  "isPrimary": true,
  "isActive": true,
  "isLocked": false,
  "dailyLimit": 7500.00,
  "monthlyLimit": 25000.00,
  "creditLimit": 12000.00,
  "availableBalance": 10000.00,
  "currencyCode": "USD",
  "designId": 1
}
```

#### Delete Card

```http
DELETE /api/v1/cards/{cardId}
```

Delete a card record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| cardId | integer | Unique identifier of the card to delete |

**Response:**

No content (204) if successful.

> Note: In most production scenarios, cards should be deactivated or blocked rather than deleted to preserve transaction history and audit trails.
### Merchant Management

Merchant APIs allow management of merchant records, which represent businesses that accept card payments.

#### List Merchants

```http
GET /api/v1/merchants
```

Retrieve a paginated list of all merchant records.

**Query Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| page | integer | Page number (zero-based) |
| size | integer | Number of items per page |
| sort | string | Sort field and direction (e.g., `merchantName,asc`) |

**Response:**

```json
{
  "content": [
    {
      "merchantId": 1,
      "merchantReference": "MERCH-001",
      "merchantName": "Example Store",
      "merchantLegalName": "Example Store Inc.",
      "merchantDisplayName": "Example Store",
      "merchantDescription": "Retail store selling electronics",
      "merchantCategoryCode": "5732",
      "merchantCategoryName": "Electronics Stores",
      "merchantType": "RETAIL",
      "merchantStatus": "ACTIVE",
      "isActive": true,
      "activationDate": "2023-01-01T00:00:00Z",
      "taxId": "123456789",
      "registrationNumber": "REG-12345",
      "websiteUrl": "https://www.examplestore.com",
      "logoUrl": "https://www.examplestore.com/logo.png",
      "addressLine1": "123 Main St",
      "addressLine2": "Suite 100",
      "city": "New York",
      "state": "NY",
      "postalCode": "10001",
      "country": "US",
      "phone": "+1-212-555-1234",
      "email": "contact@examplestore.com",
      "contactPersonName": "Jane Smith",
      "contactPersonTitle": "Manager",
      "contactPersonPhone": "+1-212-555-5678",
      "contactPersonEmail": "jane.smith@examplestore.com",
      "isOnline": true,
      "isPhysical": true,
      "isMobile": false,
      "isInternational": false,
      "supportedCurrencies": "USD,EUR,GBP",
      "defaultCurrency": "USD",
      "supportedCardNetworks": "VISA,MASTERCARD,AMEX",
      "supportedPaymentMethods": "CARD,MOBILE,WALLET",
      "isHighRisk": false,
      "riskRating": "LOW",
      "riskScore": 20,
      "riskAssessmentDate": "2023-01-01T00:00:00Z",
      "isFraudSuspected": false,
      "isBlacklisted": false,
      "isSettlementEnabled": true,
      "settlementFrequency": "DAILY",
      "settlementDay": 1,
      "settlementBankName": "Example Bank",
      "settlementAccountNumber": "************1234",
      "settlementAccountName": "Example Store Inc.",
      "settlementBankCode": "EXBKUS",
      "settlementCurrency": "USD",
      "acquirerId": "ACQ-001",
      "acquirerName": "Example Acquirer",
      "processorId": "PROC-001",
      "processorName": "Example Processor",
      "terminalIds": "TERM-001,TERM-002,TERM-003",
      "notes": "Example merchant notes"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "sorted": true,
      "unsorted": false
    }
  },
  "totalElements": 1,
  "totalPages": 1,
  "last": true,
  "first": true,
  "empty": false
}
```

#### Create Merchant

```http
POST /api/v1/merchants
Content-Type: application/json
```

Create a new merchant record.

**Request Body:**

```json
{
  "merchantReference": "MERCH-001",
  "merchantName": "Example Store",
  "merchantLegalName": "Example Store Inc.",
  "merchantDisplayName": "Example Store",
  "merchantDescription": "Retail store selling electronics",
  "merchantCategoryCode": "5732",
  "merchantCategoryName": "Electronics Stores",
  "merchantType": "RETAIL",
  "merchantStatus": "ACTIVE",
  "isActive": true,
  "activationDate": "2023-01-01T00:00:00Z",
  "taxId": "123456789",
  "registrationNumber": "REG-12345",
  "websiteUrl": "https://www.examplestore.com",
  "logoUrl": "https://www.examplestore.com/logo.png",
  "addressLine1": "123 Main St",
  "addressLine2": "Suite 100",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001",
  "country": "US",
  "phone": "+1-212-555-1234",
  "email": "contact@examplestore.com",
  "contactPersonName": "Jane Smith",
  "contactPersonTitle": "Manager",
  "contactPersonPhone": "+1-212-555-5678",
  "contactPersonEmail": "jane.smith@examplestore.com",
  "isOnline": true,
  "isPhysical": true,
  "isMobile": false,
  "isInternational": false,
  "supportedCurrencies": "USD,EUR,GBP",
  "defaultCurrency": "USD",
  "supportedCardNetworks": "VISA,MASTERCARD,AMEX",
  "supportedPaymentMethods": "CARD,MOBILE,WALLET",
  "isHighRisk": false,
  "riskRating": "LOW",
  "riskScore": 20,
  "riskAssessmentDate": "2023-01-01T00:00:00Z",
  "isFraudSuspected": false,
  "isBlacklisted": false,
  "isSettlementEnabled": true,
  "settlementFrequency": "DAILY",
  "settlementDay": 1,
  "settlementBankName": "Example Bank",
  "settlementAccountNumber": "************1234",
  "settlementAccountName": "Example Store Inc.",
  "settlementBankCode": "EXBKUS",
  "settlementCurrency": "USD",
  "acquirerId": "ACQ-001",
  "acquirerName": "Example Acquirer",
  "processorId": "PROC-001",
  "processorName": "Example Processor",
  "terminalIds": "TERM-001,TERM-002,TERM-003",
  "notes": "Example merchant notes"
}
```

**Response:**

```json
{
  "merchantId": 1,
  "merchantReference": "MERCH-001",
  "merchantName": "Example Store",
  "merchantLegalName": "Example Store Inc.",
  "merchantDisplayName": "Example Store",
  "merchantDescription": "Retail store selling electronics",
  "merchantCategoryCode": "5732",
  "merchantCategoryName": "Electronics Stores",
  "merchantType": "RETAIL",
  "merchantStatus": "ACTIVE",
  "isActive": true,
  "activationDate": "2023-01-01T00:00:00Z",
  "taxId": "123456789",
  "registrationNumber": "REG-12345",
  "websiteUrl": "https://www.examplestore.com",
  "logoUrl": "https://www.examplestore.com/logo.png",
  "addressLine1": "123 Main St",
  "addressLine2": "Suite 100",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001",
  "country": "US",
  "phone": "+1-212-555-1234",
  "email": "contact@examplestore.com",
  "contactPersonName": "Jane Smith",
  "contactPersonTitle": "Manager",
  "contactPersonPhone": "+1-212-555-5678",
  "contactPersonEmail": "jane.smith@examplestore.com",
  "isOnline": true,
  "isPhysical": true,
  "isMobile": false,
  "isInternational": false,
  "supportedCurrencies": "USD,EUR,GBP",
  "defaultCurrency": "USD",
  "supportedCardNetworks": "VISA,MASTERCARD,AMEX",
  "supportedPaymentMethods": "CARD,MOBILE,WALLET",
  "isHighRisk": false,
  "riskRating": "LOW",
  "riskScore": 20,
  "riskAssessmentDate": "2023-01-01T00:00:00Z",
  "isFraudSuspected": false,
  "isBlacklisted": false,
  "isSettlementEnabled": true,
  "settlementFrequency": "DAILY",
  "settlementDay": 1,
  "settlementBankName": "Example Bank",
  "settlementAccountNumber": "************1234",
  "settlementAccountName": "Example Store Inc.",
  "settlementBankCode": "EXBKUS",
  "settlementCurrency": "USD",
  "acquirerId": "ACQ-001",
  "acquirerName": "Example Acquirer",
  "processorId": "PROC-001",
  "processorName": "Example Processor",
  "terminalIds": "TERM-001,TERM-002,TERM-003",
  "notes": "Example merchant notes"
}
```

#### Get Merchant by ID

```http
GET /api/v1/merchants/{merchantId}
```

Retrieve a specific merchant record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| merchantId | integer | Unique identifier of the merchant |

**Response:**

```json
{
  "merchantId": 1,
  "merchantReference": "MERCH-001",
  "merchantName": "Example Store",
  "merchantLegalName": "Example Store Inc.",
  "merchantDisplayName": "Example Store",
  "merchantDescription": "Retail store selling electronics",
  "merchantCategoryCode": "5732",
  "merchantCategoryName": "Electronics Stores",
  "merchantType": "RETAIL",
  "merchantStatus": "ACTIVE",
  "isActive": true,
  "activationDate": "2023-01-01T00:00:00Z",
  "taxId": "123456789",
  "registrationNumber": "REG-12345",
  "websiteUrl": "https://www.examplestore.com",
  "logoUrl": "https://www.examplestore.com/logo.png",
  "addressLine1": "123 Main St",
  "addressLine2": "Suite 100",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001",
  "country": "US",
  "phone": "+1-212-555-1234",
  "email": "contact@examplestore.com",
  "contactPersonName": "Jane Smith",
  "contactPersonTitle": "Manager",
  "contactPersonPhone": "+1-212-555-5678",
  "contactPersonEmail": "jane.smith@examplestore.com",
  "isOnline": true,
  "isPhysical": true,
  "isMobile": false,
  "isInternational": false,
  "supportedCurrencies": "USD,EUR,GBP",
  "defaultCurrency": "USD",
  "supportedCardNetworks": "VISA,MASTERCARD,AMEX",
  "supportedPaymentMethods": "CARD,MOBILE,WALLET",
  "isHighRisk": false,
  "riskRating": "LOW",
  "riskScore": 20,
  "riskAssessmentDate": "2023-01-01T00:00:00Z",
  "isFraudSuspected": false,
  "isBlacklisted": false,
  "isSettlementEnabled": true,
  "settlementFrequency": "DAILY",
  "settlementDay": 1,
  "settlementBankName": "Example Bank",
  "settlementAccountNumber": "************1234",
  "settlementAccountName": "Example Store Inc.",
  "settlementBankCode": "EXBKUS",
  "settlementCurrency": "USD",
  "acquirerId": "ACQ-001",
  "acquirerName": "Example Acquirer",
  "processorId": "PROC-001",
  "processorName": "Example Processor",
  "terminalIds": "TERM-001,TERM-002,TERM-003",
  "notes": "Example merchant notes"
}
```

#### Update Merchant

```http
PUT /api/v1/merchants/{merchantId}
Content-Type: application/json
```

Update an existing merchant record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| merchantId | integer | Unique identifier of the merchant to update |

**Request Body:**

```json
{
  "merchantName": "Example Store Updated",
  "merchantDisplayName": "Example Store Updated",
  "merchantDescription": "Updated retail store selling electronics and appliances",
  "isActive": true,
  "phone": "+1-212-555-9876",
  "email": "updated-contact@examplestore.com",
  "contactPersonName": "John Doe",
  "contactPersonTitle": "Director",
  "contactPersonPhone": "+1-212-555-4321",
  "contactPersonEmail": "john.doe@examplestore.com",
  "isHighRisk": false,
  "riskRating": "LOW",
  "riskScore": 15
}
```

**Response:**

```json
{
  "merchantId": 1,
  "merchantReference": "MERCH-001",
  "merchantName": "Example Store Updated",
  "merchantLegalName": "Example Store Inc.",
  "merchantDisplayName": "Example Store Updated",
  "merchantDescription": "Updated retail store selling electronics and appliances",
  "merchantCategoryCode": "5732",
  "merchantCategoryName": "Electronics Stores",
  "merchantType": "RETAIL",
  "merchantStatus": "ACTIVE",
  "isActive": true,
  "activationDate": "2023-01-01T00:00:00Z",
  "taxId": "123456789",
  "registrationNumber": "REG-12345",
  "websiteUrl": "https://www.examplestore.com",
  "logoUrl": "https://www.examplestore.com/logo.png",
  "addressLine1": "123 Main St",
  "addressLine2": "Suite 100",
  "city": "New York",
  "state": "NY",
  "postalCode": "10001",
  "country": "US",
  "phone": "+1-212-555-9876",
  "email": "updated-contact@examplestore.com",
  "contactPersonName": "John Doe",
  "contactPersonTitle": "Director",
  "contactPersonPhone": "+1-212-555-4321",
  "contactPersonEmail": "john.doe@examplestore.com",
  "isOnline": true,
  "isPhysical": true,
  "isMobile": false,
  "isInternational": false,
  "supportedCurrencies": "USD,EUR,GBP",
  "defaultCurrency": "USD",
  "supportedCardNetworks": "VISA,MASTERCARD,AMEX",
  "supportedPaymentMethods": "CARD,MOBILE,WALLET",
  "isHighRisk": false,
  "riskRating": "LOW",
  "riskScore": 15,
  "riskAssessmentDate": "2023-01-01T00:00:00Z",
  "isFraudSuspected": false,
  "isBlacklisted": false,
  "isSettlementEnabled": true,
  "settlementFrequency": "DAILY",
  "settlementDay": 1,
  "settlementBankName": "Example Bank",
  "settlementAccountNumber": "************1234",
  "settlementAccountName": "Example Store Inc.",
  "settlementBankCode": "EXBKUS",
  "settlementCurrency": "USD",
  "acquirerId": "ACQ-001",
  "acquirerName": "Example Acquirer",
  "processorId": "PROC-001",
  "processorName": "Example Processor",
  "terminalIds": "TERM-001,TERM-002,TERM-003",
  "notes": "Example merchant notes"
}
```

#### Delete Merchant

```http
DELETE /api/v1/merchants/{merchantId}
```

Delete a merchant record by its unique identifier.

**Path Parameters:**

| Parameter | Type | Description |
|-----------|------|-------------|
| merchantId | integer | Unique identifier of the merchant to delete |

**Response:**

No content (204) if successful.

> Note: Consider deactivating the merchant instead of deleting it if it has transaction history.
### Transaction Management
### Dispute Management

## Detailed API Documentation

