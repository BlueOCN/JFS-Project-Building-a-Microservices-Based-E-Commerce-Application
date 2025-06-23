# JFS-Project-Building-a-Microservices-Based-E-Commerce-Application
Simple E-Commerce app using microservices: Product (catalog), Order (manage orders), Payment (mock processing), and User (auth &amp; profiles). Each service runs independently, communicates with others, and registers with Eureka for discovery.

## Description
You are tasked with building a simple E-Commerce Application using a microservices architecture. The application will consist of the following microservices:

1. Product Service
    - Handles product catalog (add, update, delete, and list products).

2. Order Service
    - Handles customer orders (place an order, view orders, update order status).

3. Payment Service
    - Processes payments for orders (mock payment processing).

4. User Service
    - Manages user registration, authentication, and profiles.

Each service will operate independently and communicate with other services as needed. The services will be deployed on separate ports and registered with a service discovery server (Eureka).

## Learning Outcomes
By completing this project, you will:

- Understand the basics of microservices architecture and its components.
- Learn how to design, develop, and test microservices with Spring Boot.
- Gain experience with inter-service communication and service discovery using Eureka.
- Implement centralized configuration management with Spring Cloud Config Server.
- Learn fault tolerance techniques using Spring Cloud Circuit Breaker.

## Instructions

### Step 1: Set Up the Project
1. Create a Spring Boot project for each service using Spring InitializerLinks to an external site..
2. For each service, include the necessary dependencies:
    - Spring Web
    - Spring Data JPA
    - MySQL Driver (or H2 for simplicity)
    - Spring Cloud Eureka Client
    - Spring Boot Actuator (optional for monitoring).

### Step 2: Implement Each Microservice

#### Product Service
- Manages product information like ID, name, description, price, and stock.
- Provides REST APIs for CRUD operations (Create, Read, Update, Delete).
- Stores product data in a database.

---

#### Order Service
- Manages orders and maintains their status (e.g., pending, shipped, delivered).
- Communicates with the Product Service to validate product availability.
- Provides REST APIs for creating and viewing orders.

---

#### Payment Service
- Simulates payment processing for orders.
- Provides a REST endpoint to process payments based on an order ID.
- Returns a success or failure response after "processing" payments.

---

#### User Service
- Handles user registration and authentication.
- Uses JWT for secure user authentication.
- Manages user profiles and roles (e.g., admin, customer).

### Step 3: Set Up Service Discovery with Eureka
1. Create a separate Eureka Server project using Spring Boot.
2. Include the Spring Cloud Eureka Server dependency.
3. Configure the Eureka Server in application.properties and run it.
4. Register all microservices with the Eureka Server for service discovery.

### Step 4: Centralized Configuration
1. Create a Spring Cloud Config Server to manage configuration for all services.
2. Store configuration files in a Git repository and link them to the Config Server.
3. Configure each microservice to fetch its configuration from the Config Server.

### Step 5: Enable Inter-Service Communication
1. Use RestTemplate or Feign Client to enable communication between services.
2. Example: The Order Service communicates with the Product Service to check stock availability before placing an order.

### Step 6: Implement Fault Tolerance
1. Use Spring Cloud Circuit Breaker (Resilience4J) to handle failures gracefully.
2. Example: If the Payment Service is down, the Order Service should return a fallback response.

### Step 7: Test the Microservices
1. Start all microservices and the Eureka Server.
2. Use tools like Postman or cURL to test individual services and their communication.
3. Example Workflow:
    - Add products via the Product Service.
    - Place an order via the Order Service.
    - Process payment via the Payment Service.

### Additional Challenges

- Add a Gateway Service to act as a single entry point for all microservices.
- Use RabbitMQ or Kafka for asynchronous communication between services.
- Implement distributed tracing using Spring Cloud Sleuth and Zipkin.
- Use Docker to containerize and deploy each service.

## Final Deliverable

- Upload the completed project to a GitHub repository.
- Include a README.md file with instructions on:
    - How to set up and run the services.
    - API endpoints and example workflows.
- Ensure the project is well-structured and easy to follow.
