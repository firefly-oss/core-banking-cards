# Core Banking Cards API Design

This document outlines the API design principles and patterns used in the Core Banking Cards service.

## API Design Principles

The Core Banking Cards API follows these design principles:

1. **RESTful Design**: The API follows REST principles, using standard HTTP methods (GET, POST, PUT, DELETE) for CRUD operations.
2. **Resource-Oriented**: The API is organized around resources, with URLs representing resource collections and individual resources.
3. **Consistent Naming**: Resources are named using plural nouns, and endpoints follow a consistent pattern.
4. **Versioning**: All endpoints are versioned using the `/api/v1/` prefix.
5. **Query Parameters for Filtering**: Filtering is done using query parameters rather than path segments.
6. **Nested Resources**: Resources that are strongly associated with a parent resource are represented as nested resources.

## API Structure

### Top-level Resources

Top-level resources represent the main entities in the system:

| Resource | Endpoint | Description |
|----------|----------|-------------|
| BINs | `/api/v1/bins` | Bank Identification Number records |
| Programs | `/api/v1/programs` | Card program records |
| Networks | `/api/v1/networks` | Card network records |
| Gateways | `/api/v1/gateways` | Payment gateway records |
| Terminals | `/api/v1/terminals` | Card terminal records |
| Issuers | `/api/v1/issuers` | Card issuer records |
| Merchants | `/api/v1/merchants` | Merchant records |
| Acquirers | `/api/v1/acquirers` | Card acquirer records |
| Cards | `/api/v1/cards` | Card records |

### Nested Resources

Nested resources represent entities that are strongly associated with a parent resource:

| Resource | Endpoint | Description |
|----------|----------|-------------|
| Card Balances | `/api/v1/cards/{cardId}/balances` | Balance records for a specific card |
| Card Disputes | `/api/v1/cards/{cardId}/disputes` | Dispute records for a specific card |
| Card Activities | `/api/v1/cards/{cardId}/activities` | Activity records for a specific card |
| Card Limits | `/api/v1/cards/{cardId}/limits` | Limit records for a specific card |
| Card Interests | `/api/v1/cards/{cardId}/interests` | Interest records for a specific card |
| Card Transactions | `/api/v1/cards/{cardId}/transactions` | Transaction records for a specific card |
| Card Payments | `/api/v1/cards/{cardId}/payments` | Payment records for a specific card |
| Virtual Cards | `/api/v1/cards/{cardId}/virtual-cards` | Virtual card records for a specific card |

### Query Parameters for Filtering

For special lookups and filtering, query parameters are used instead of path segments:

| Resource | Endpoint | Description |
|----------|----------|-------------|
| BIN by Number | `/api/v1/bins?number={binNumber}` | Lookup a BIN by its number |
| Filtered Transactions | `/api/v1/cards/{cardId}/transactions?filter=true` | Filter transactions with custom criteria |

## HTTP Methods

The API uses standard HTTP methods for CRUD operations:

| Method | Description |
|--------|-------------|
| GET | Retrieve a resource or collection of resources |
| POST | Create a new resource |
| PUT | Update an existing resource |
| DELETE | Delete a resource |

## Pagination

All collection endpoints support pagination using the following query parameters:

| Parameter | Description |
|-----------|-------------|
| page | Page number (zero-based) |
| size | Number of items per page |
| sort | Sort field and direction (e.g., `name,asc`) |

Example: `/api/v1/cards?page=0&size=20&sort=cardHolderName,asc`

## Filtering

Filtering is supported using query parameters:

1. **Simple Filtering**: Using query parameters directly.
   Example: `/api/v1/bins?number=123456`

2. **Advanced Filtering**: Using a filter object for complex criteria.
   Example: `/api/v1/cards/{cardId}/transactions?filter=true`

## Response Format

All responses follow a consistent format:

1. **Single Resource**: Returns the resource object directly.
2. **Collection**: Returns a paginated response with the following structure:
   ```json
   {
     "content": [...],
     "pageable": {
       "pageNumber": 0,
       "pageSize": 20,
       "sort": {
         "sorted": true,
         "unsorted": false
       }
     },
     "totalElements": 100,
     "totalPages": 5,
     "last": false,
     "first": true,
     "empty": false
   }
   ```

## Error Handling

The API uses standard HTTP status codes to indicate the success or failure of a request:

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

Error responses include a detailed error message and, when applicable, validation errors:

```json
{
  "timestamp": "2023-06-15T10:30:45Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "path": "/api/v1/cards",
  "errors": [
    {
      "field": "cardHolderName",
      "message": "must not be blank"
    }
  ]
}
```
