# Microservices_Spring

This project consists of some microservices developed using Spring boot.

Users Microservice : 
This contains the functionality for user registration and login.

Login api also implements Spring security features for generating a JWT token whenever a user has logged in successfully,
which would be used by the further requests to interact with the APIs, as all apis are secured.

Books Microservice : 
This is a microservice to maintain records for books, books maintained by a user, apis to store books in Mongo DB, read all books, fetch a book by its id and delete a book.
Basically, this is just a sample microservice for integration of spring with Mongo DB, and also to communicate with other microservices.

Technology Stack Used :

Spring Boot 
(Spring Eureka Server, Spring Cloud Config Server, Spring Zuul, Spring, Security and core features, Zipkin and Sleuth, Feign/Rest Template for communication between microservices) ,
JUnit ,Mockito, 
Swagger, 
Mongo DB,
Rabbit MQ to queue messages for Zipkin (For distributed tracing), and also for messaging between microservices
Docker (for containerization of microservices)


