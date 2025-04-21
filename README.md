# Core Banking Cards Service

## Overview
The Core Banking Cards Service is a microservice component of the Firefly platform that manages banking cards and related operations. This service provides comprehensive functionality for managing both physical and virtual cards, including card issuance, configuration, limits, and integration with card providers.

## Features
- Card management (creation, update, retrieval, deletion)
- Physical card management (issuance, tracking, activation)
- Virtual card management (creation, activation, deactivation)
- Card configuration settings
- Card spending limits
- Card provider integration

## Architecture
The service follows a modular architecture with the following components:

### Modules
- **core-banking-cards-interfaces**: Contains DTOs, enums, and API interfaces
- **core-banking-cards-models**: Contains data models and repositories
- **core-banking-cards-core**: Contains business logic and service implementations
- **core-banking-cards-web**: Contains web controllers, configuration, and application entry point

### Technologies
- Java 21
- Spring Boot
- Spring WebFlux (reactive programming)
- R2DBC (reactive database connectivity)
- Maven
- Docker

## Getting Started

### Prerequisites
- JDK 21
- Maven 3.8+
- Docker (optional, for containerization)

### Building the Service
```bash
mvn clean install
```

### Running the Service
```bash
# Run with Maven
mvn spring-boot:run -pl core-banking-cards-web

# Run with Java
java -jar core-banking-cards-web/target/core-banking-cards-web.jar

# Run with Docker
docker build -t core-banking-cards .
docker run -p 8080:8080 core-banking-cards
```

### Configuration
The service can be configured through application properties. Key configuration options include:

- Database connection settings
- Logging levels
- API security settings
- External service endpoints

## API Documentation
The service provides a RESTful API with OpenAPI documentation. When the service is running, you can access the API documentation at:

```
http://localhost:8080/swagger-ui.html
```

### Key Endpoints
- `/api/v1/cards` - Card management
- `/api/v1/cards/physical` - Physical card management
- `/api/v1/cards/virtual` - Virtual card management
- `/api/v1/cards/configuration` - Card configuration
- `/api/v1/cards/limits` - Card limits
- `/api/v1/cards/providers` - Card provider integration

## Development

### Project Structure
```
core-banking-cards/
├── core-banking-cards-core/       # Business logic
├── core-banking-cards-interfaces/ # DTOs and interfaces
├── core-banking-cards-models/     # Data models
├── core-banking-cards-web/        # Web layer
├── Dockerfile                     # Docker configuration
└── pom.xml                        # Maven parent configuration
```

### Coding Standards
- Follow Java coding conventions
- Use meaningful names for classes, methods, and variables
- Write unit tests for all new functionality
- Document public APIs with Javadoc

### Testing
```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=CardServiceTest
```

## Deployment
The service can be deployed as a standalone JAR or as a Docker container. For production environments, it's recommended to use container orchestration platforms like Kubernetes.

### Docker Deployment
```bash
# Build Docker image
docker build -t core-banking-cards:latest .

# Run Docker container
docker run -p 8080:8080 core-banking-cards:latest
```

## Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request
