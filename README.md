# Spring Boot Microservices with Docker

Welcome to this **Spring Boot Microservices** project, which showcases a set of services designed for distributed architectures. This project includes **Discovery Service**, **Cloud Gateway**, **Config Server**, and **User Service** integrated with **JWT**, **Role-Based Access**, and **Refresh Token** security mechanisms.

The entire system is containerized using **Docker** and can be run both in a **local development environment** and a **hosted environment** with Docker. This repository demonstrates the power of Spring Boot, security best practices, and microservices architecture.

## 🛠️ Features

- **Discovery Service**: Centralized service registry for service discovery with Eureka.
- **Cloud Gateway**: API Gateway routing to microservices with security filters.
- **Config Server**: Centralized configuration management using Spring Cloud Config.
- **User Service**:
  - JWT Authentication
  - Role-Based Access Control
  - Refresh Token mechanism
  - Security with Spring Security
- **Dockerized**: Docker files for each service, with Docker Compose for orchestration.

## 🔧 Technologies Used

- **Spring Boot**: 3.x
- **Spring Cloud**: Eureka, Config Server, Cloud Gateway
- **Spring Security**: JWT, Role-based Authentication
- **PostgreSQL**: Database for user data
- **Docker**: Containerization of microservices
- **Docker Compose**: Simplified local setup with multi-container orchestration

## 🚀 Getting Started

These instructions will help you set up the project locally on your machine.

### Prerequisites

Ensure the following are installed on your local machine:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/)
- [Java 21 or higher](https://openjdk.java.net/)
- [Maven](https://maven.apache.org/) for building Spring Boot applications

### 1. Clone the Repository

```bash
git clone https://github.com/Thekishor/spring-boot-microservice-docker.git
cd spring-boot-microservice-docker
