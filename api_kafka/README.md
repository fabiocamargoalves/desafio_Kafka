# springboot-productms 0.1

Spring Boot CRUD is demonstrating how to implement simple CRUD operations with a `Orders` entity.

## What's inside 
This project is based on the [Spring Boot](http://projects.spring.io/spring-boot/)

## Swagger
 
http://localhost:9999/swagger-ui.html
 

## Installation 
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

## Database configuration 

server.port=9999
topic.name=order

spring.kafka.bootstrap-servers=localhost:29092


# datasource
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:C:/bd_teste;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;AUTO_SERVER=TRUE
spring.datasource.username=sa
spring.datasource.password=

# jpa
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true

# h2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

#docs
springdoc.api-docs.path=/api-docs

## Usage 
Run the project through the IDE and head out to [http://localhost:9999](http://localhost:9999)

Console H2 - http://localhost:9999/h2-console
