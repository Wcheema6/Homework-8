# Homework-8
Homework 8 for CISC.
# Campus Task Board API - HW8

## Project Overview

This project is a Spring Boot REST API for managing campus tasks.  
The application allows users to create, update, retrieve, and delete tasks while demonstrating backend development concepts such as API versioning, validation, exception handling, security, logging, Swagger documentation, and monitoring.

---

# Features

## CRUD Operations
- Create tasks
- Retrieve all tasks
- Retrieve task by ID
- Update tasks
- Delete tasks

## API Versioning
All HW8 endpoints are versioned using:

```text
/api/v1/tasks
Validation

Task input validation includes:

Required title
Description validation
Priority validation
Completed status validation
Global Exception Handling

Custom exceptions are handled using:

TaskNotFoundException
InvalidTaskDataException
GlobalExceptionHandler
DTO Support

DTOs were added to separate API request/response models from database entities.

Soft Delete

Tasks are soft deleted instead of permanently removed from the database.

Logging

Custom request logging tracks:

Request type
Endpoint
Status code
Request duration
Swagger/OpenAPI Documentation

Swagger UI was integrated for API testing and documentation.

Swagger URL:

http://localhost:8080/swagger-ui/index.html
Spring Boot Actuator

Health monitoring endpoint:

http://localhost:8080/actuator/health
Spring Security

Spring Security was configured to secure the application while allowing access to:

Swagger UI
API documentation
Actuator endpoints
H2 console
Technologies Used
Java 17
Spring Boot
Spring Web
Spring Data JPA
Spring Security
Spring Boot Actuator
Swagger / OpenAPI
H2 Database
Maven
Lombok
Project Structure
src/main/java/edu/brooklyn/cisc3130/taskboard
│
├── config
│   ├── OpenApiConfig.java
│   ├── SecurityConfig.java
│   └── LoggingFilter.java
│
├── controller
│   ├── TaskController.java
│   └── v1
│       └── TaskControllerV1.java
│
├── dto
│   ├── TaskRequest.java
│   ├── TaskResponse.java
│   └── ErrorResponse.java
│
├── exception
│   ├── GlobalExceptionHandler.java
│   ├── TaskNotFoundException.java
│   └── InvalidTaskDataException.java
│
├── model
│   └── Task.java
│
├── repository
│   └── TaskRepository.java
│
├── service
│   └── TaskService.java
│
└── CampusTaskboardApplication.java
Running the Application
Clone/Open Project

Open the project folder in VS Code or Cursor.

Run the Application

In the terminal:

./mvnw spring-boot:run

Application runs at:

http://localhost:8080
API Endpoints
Base Endpoint
/api/v1/tasks
Available Endpoints
Method	Endpoint	Description
GET	/api/v1/tasks	Get all tasks
GET	/api/v1/tasks/{id}	Get task by ID
POST	/api/v1/tasks	Create task
PUT	/api/v1/tasks/{id}	Update task
DELETE	/api/v1/tasks/{id}	Delete task
Example Request
POST Request
{
  "title": "Complete HW8",
  "description": "Finish Swagger and Security setup",
  "completed": false,
  "priority": "HIGH"
}
Swagger Documentation

Swagger UI:

http://localhost:8080/swagger-ui/index.html

Swagger allows:

Viewing all endpoints
Testing API requests
Viewing request/response schemas
Health Monitoring

Spring Boot Actuator Health Endpoint:

http://localhost:8080/actuator/health

Example response:

{
  "status": "UP"
}
Challenges Faced

During development, several issues occurred including:

Swagger API definition loading errors
Maven build failures
Lombok version conflicts
Spring Security configuration issues
Incorrect endpoint mappings
Validation and DTO conversion problems

These issues were resolved by:

Updating dependencies
Correcting security configuration
Fixing endpoint mappings
Updating Lombok compatibility
Testing endpoints with Swagger and Postman
What I Learned

From this assignment, I learned:

How to version REST APIs
How Swagger/OpenAPI generates documentation
How Spring Security protects endpoints
How Actuator monitors application health
How DTOs improve API design
How global exception handling improves backend reliability
How to debug Maven and Spring Boot configuration problems
