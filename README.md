# Spring Boot Microservices
[![Springboot](https://img.shields.io/badge/Springboot-v3.0-green)](https://docs.spring.io/spring-boot/docs/current/reference/html/)
[![Kafka](https://img.shields.io/badge/Kafka-v7.3.2-lightblue)](https://developer.confluent.io/)
[![Docker](https://img.shields.io/badge/Docker-v23.0.5-blue)](https://docs.docker.com/get-started/)
[![Springboot](https://img.shields.io/badge/KeyCloak-v21.1.1-lightgrey)](https://www.keycloak.org/getting-started/getting-started-docker)
[![Resilience4J](https://img.shields.io/badge/Resilience4J-v2.0.0-white)](https://resilience4j.readme.io/docs/getting-started)





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

## Architecture Diagram

![](https://github.com/AyushPaul/Springboot-Microservices/blob/main/Architecture.png)

## Prerequisites

- Java 8 or higher
- Maven
- Docker (optional)

## Setting up the Databases

- ### Setting up MongoDB
  1. Install MongoDB on your system. You can download the installer from the official [MongoDB](https://www.mongodb.com/try/download/community) website.
  2. Start the MongoDB server by running the following command in a terminal or command prompt:
      ```
        mongod
      ```
  3. If you are having problems starting the MongoDB Server, go through [this](https://stackoverflow.com/questions/59822025/how-to-start-and-stop-mongodb-server-on-windows)  and [this](https://stackoverflow.com/questions/20796714/how-do-i-start-mongo-db-from-windows) StackOverflow answers to start the server for Windows Devices.
  4. After starting the MongoDB Server, open MongoDB Compass Application and create a database named `product-service` with all the default settings and copy the URI String.
  5. Paste the URI String in the `application.properties` file of the `product-service` module as follows :
     ```
       spring.data.mongodb.uri=<YOUR_URI_STRING>
     ```
  6. Spring Data JPA will take care of making the collection and inputting dummy data into it.

- ### Setting up MySQL

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

## Configuring KeyCloak

-

## Demo


https://github.com/AyushPaul/Springboot-Microservices/assets/67481937/295dcf95-4b64-4413-b7c8-49d33ffc653d


