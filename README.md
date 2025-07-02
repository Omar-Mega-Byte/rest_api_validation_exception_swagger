# Todo API - Spring Boot with Validators, Exceptions, and Swagger UI

## Overview

This repository contains a Spring Boot application for a RESTful Todo API, featuring custom validators, exception handling, and Swagger UI documentation. The API is defined with an OpenAPI 3.1.0 specification (`swagger.json`) for interactive testing.

## Purpose

The Todo API enables CRUD operations on todo items with data validation and clear error handling, while Swagger UI provides an interactive interface for exploration.

## Features

- RESTful API for todo management.
- Validators in the `validator` package for data consistency.
- Custom exceptions and global handling in the `exception` package.
- Swagger UI at `/swagger-ui.html` for API testing.
- Spring Boot for a scalable backend.

## API Endpoints

- **GET /api/todos**: List all todo items.
- **POST /api/todos**: Create a todo with validation.
- **GET /api/todos/{id}**: Retrieve a todo by ID.
- **PUT /api/todos/{id}**: Update a todo with validation.
- **DELETE /api/todos/{id}**: Delete a todo by ID.

### Todo Schema

- `id`: Integer (64-bit).
- `title`: String.
- `description`: String.
- `completed`: Boolean.

## Getting Started

### Prerequisites

- Java 17+.
- Maven (use `./mvnw` or `mvnw.cmd`).
- Web browser.
- (Optional) IDE or testing tools like Postman.

### Installation

1. Clone the repo:

   ```bash
   git clone https://github.com/Omar-Mega-Byte/rest_api_validation_exception_swagger.git
   cd rest_api_validation_exception_swagger
   ```
2. Build:

   ```bash
   ./mvnw clean install
   ```
3. Run:

   ```bash
   ./mvnw spring-boot:run
   ```
4. Access Swagger UI:
   - Open `http://localhost:8080/swagger-ui.html`.

### Configuration

- **Swagger**: Configured via `application.properties`:

  ```properties
  springdoc.api-docs.path=/v3/api-docs
  springdoc.swagger-ui.path=/swagger-ui.html
  ```
- **Validation/Exceptions**: Defined in `validator` and `exception` packages.
- **Database**: H2 in-memory (configurable in `application.properties`):

  ```properties
  spring.datasource.url=jdbc:h2:mem:tododb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  ```

## Usage

- Explore at `http://localhost:8080/swagger-ui.html`.
- Test with "Try it out" (e.g., POST `/api/todos` with:

  ```json
  {"title": "Task", "description": "Do it", "completed": false}
  ```
- Errors: `400` (validation), `404` (not found).

## Project Structure

```
todo-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/todo_api/
│   │   │       ├── controller/
│   │   │       │   └── TodoController.java           # REST endpoints with Swagger annotations
│   │   │       ├── exception/
│   │   │       │   ├── GlobalExceptionHandler.java   # @ControllerAdvice for centralized exception handling
│   │   │       │   ├── TodoNotFoundException.java    # Custom 404 exception
│   │   │       │   └── TodoBadRequestException.java  # Custom 400 exception
│   │   │       ├── model/
│   │   │       │   ├── Todo.java                     # JPA entity with Lombok annotations
│   │   │       │   └── ApiError.java                 # Error response model for consistent API responses
│   │   │       ├── repository/
│   │   │       │   └── TodoRepository.java           # JpaRepository interface for data access
│   │   │       ├── utils/
│   │   │       │   └── StringUtils.java              # Utility class for string validation
│   │   │       ├── validator/
│   │   │       │   ├── ITodoValidator.java           # Validator interface
│   │   │       │   └── TodoValidator.java            # Implementation with business validation rules
│   │   │       ├── TodoApiApplication.java           # Main Spring Boot application entry point
│   │   │       └── Main.java                         # Alternative main class (consider removing)
│   │   ├── resources/
│   │   │   ├── application.properties                # Spring Boot configuration (H2, JPA, SpringDoc)
│   │   │   ├── static/                              # Static web resources
│   │   │   └── templates/                           # Template files
│   ├── test/
│   │   └── java/
│   │       └── com/example/todo_api/
│   │           └── TodoApiApplicationTests.java     # Unit tests
├── target/                                          # Compiled classes and build artifacts
├── pom.xml                                         # Maven dependencies
├── mvnw & mvnw.cmd                                 # Maven wrapper scripts
└── HELP.md                                         # Spring Boot generated help file
```

## Dependencies

```xml
<<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.8.9</version> <!-- or latest v2.x -->
		</dependency>
		<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.apache.velocity</groupId>
			<artifactId>velocity</artifactId>
			<version>1.7</version>
		</dependency>
	</dependencies>
```

## Troubleshooting

- Swagger UI not loading? Check `http://localhost:8080/v3/api-docs`.
- Validation errors? Review `validator` package.
- Exceptions not handled? Check `exception` package and `GlobalExceptionHandler.java`.
- Server unreachable? Verify port in `application.properties`.

## Contributing

1. Fork the repo.
2. Create a branch (`git checkout -b feature/your-feature`).
3. Commit changes (`git commit -m "Add your feature"`).
4. Push (`git push origin feature/your-feature`).
5. Open a pull request.

Report issues via the issue tracker.

## License

MIT License. See LICENSE file for details.

## Contact

Open an issue or contact omar.tolis2004@gmail.com