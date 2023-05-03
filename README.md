# Spring Boot Microservices

This repository contains a sample Spring Boot microservices project. The project demonstrates how to build, deploy, and manage microservices using Spring Boot and related technologies.

## Features

- Spring Boot for easy application setup and configuration
- Eureka for service discovery
- Spring Cloud Gateway for API gateway,routing and Load Balancing
- Resilience4J for fault tolerance and latency management
- Notifications using Kafka's Event Driven Architecture
- OAuth Authentication using KeyCloak
- Spring Cloud Config for centralized configuration management
- Docker for containerization and deployment

## Prerequisites

- Java 8 or higher
- Maven
- Docker (optional)

## Getting Started

1. Clone the repository:
   ```
    git clone https://github.com/AyushPaul/Springboot-Microservices.git
    cd Springboot-Microservices
   ```
2. Build the project:
   ```
    mvn clean install
   ```
3. Run the services:
   1. Start the Discovery Server (Eureka Server) 
      ```
        cd discovery-server
        mvn spring-boot:run
      ```
   2. Start the api-gateway (Spring Cloud Gateway)
      ```
        cd api-gateway
        mvn spring-boot:run
      ```
   3. Start the Microservices
      ```
        cd inventory-service
        mvn spring-boot:run
      ```
      ```
        cd order-service
        mvn spring-boot:run
      ```
      ```
        cd product-service
        mvn spring-boot:run
      ```
