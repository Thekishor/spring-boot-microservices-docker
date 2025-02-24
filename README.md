Spring Boot Microservices with Docker

Welcome to the Spring Boot Microservices with Docker project! This repository contains a collection of microservices built using Spring Boot, Docker, and Docker Compose. The project includes services like Discovery Service, Cloud Gateway, Config Server, User Service, and more. The User Service is enhanced with JWT (JSON Web Tokens), Spring Security, Role-Based Access Control, and Refresh Token functionality. The project is designed to run both on localhost and host.docker.internal environments.

🚀 Project Overview
This project demonstrates a microservices architecture using Spring Boot. Each microservice is containerized using Docker, and Docker Compose is used to manage the multi-container setup. The project includes:

Discovery Service: Service registration and discovery using Netflix Eureka.
Cloud Gateway: API gateway for routing requests to the appropriate microservices.
Config Server: Centralized configuration management for all microservices.
User Service: Handles user authentication, JWT token generation, role-based access, and refresh tokens.
✨ Features
Service Discovery: Netflix Eureka for service registration and discovery.
API Gateway: Spring Cloud Gateway for routing and load balancing.
Centralized Configuration: Spring Cloud Config Server for managing configurations.
JWT Authentication: Secure authentication using JSON Web Tokens.
Role-Based Access Control: Restrict access to endpoints based on user roles.
Refresh Tokens: Implement refresh tokens for seamless user sessions.
Dockerized: All services are containerized for easy deployment.
Multi-Environment Support: Runs on both localhost and host.docker.internal.
🛠 Technologies Used
Spring Boot: For building microservices.
Spring Cloud: For service discovery, API gateway, and config server.
Spring Security: For securing the application.
JWT: For token-based authentication.
Docker: For containerization.
Docker Compose: For managing multi-container setups.
Netflix Eureka: For service discovery.
Maven: For dependency management.
📝 Prerequisites
Before you begin, ensure you have the following installed:

Java JDK 21
Maven 3.x
Docker
Docker Compose
Git
🚀 Setup and Running the Project
Clone the Repository

bash
Copy
Edit
git clone https://github.com/your-username/spring-boot-microservice-docker.git
cd spring-boot-microservice-docker
Build the Project

bash
Copy
Edit
mvn clean install
Run with Docker Compose

bash
Copy
Edit
docker-compose up --build
This will build and start all the microservices in Docker containers.

Access the Services
Eureka Discovery Service: http://localhost:8761
API/CLOUD Gateway: http://localhost:8060
Config Server: http://localhost:8888
User Service: http://localhost:8089

🌐 API Endpoints
User Service
Register User: POST /user/register
Login: POST /user/login
Refresh Token: POST /user/refresh-token
Get User Details: GET /user/{id} (Requires ROLE_USER or ROLE_ADMIN)
Admin Endpoint: GET /user/admin (Requires ROLE_ADMIN)

📜 License
This project is licensed under the MIT License.

💡 Conclusion
This project is a great example of how to build and deploy microservices using Spring Boot, Docker, and Docker Compose. It includes advanced features like JWT authentication, role-based access control, and refresh tokens. Feel free to explore, modify, and use this project as a template for your own microservices architecture.

Happy Coding! 🚀

Made with ❤️ by [Kishor Pandey]
GitHub: https://github.com/Thekishor/
