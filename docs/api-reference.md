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
### Card Program Management
### Card Management
### Merchant Management
### Transaction Management
### Dispute Management

## Detailed API Documentation

