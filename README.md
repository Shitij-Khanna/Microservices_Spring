# Microservices_Spring

This project consists of some microservices developed using Spring boot.

Users Microservice : 
This contains the functionality for user registration and login.

Login api also implements Spring security features for generating a JWT token whenever a user has logged in successfully,
which would be used by the further requests to interact with the APIs, as all apis are secured.

Technology Stack Used :

Spring Boot 
(Spring Eureka Server, Spring Cloud Config Server, Spring Zuul, Spring, Security and core features, Zipkin and Sleuth, Feign/Rest Template for communication between microservices) ,
JUnit ,Mockito, 
Swagger, 
Mongo DB,
Rabbit MQ to queue messages for Zipkin (For distributed tracing), and also for messaging between microservices
Docker (for containerization of microservices)


