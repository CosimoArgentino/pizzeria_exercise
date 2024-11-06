Awesome Pizzeria

Welcome to the Awesome Pizzeria project! 

This application is a simplified simulation of a scalable SaaS (Software as a Service) platform for managing pizza orders. The primary goal is to demonstrate a basic architecture that could be expanded to include features like pricing, invoicing, and persistence with a SQL database. I avoided to do something "simple" by using just a queue and save everything in memory

Prerequisites

For Running with Docker
Docker: Version 20.10 or higher
Docker Compose: Version 1.29 or higher (usually comes bundled with Docker Desktop)

For Running Locally

Java Development Kit (JDK): Version 21
Maven: Version 3.6 or higher
Redis: Version 6.0 or higher

**Running the Application**

# **Running with Docker**

To run the application using Docker, follow these steps:

Modify Application Properties

In the src/main/resources/application.properties file, comment out the following lines:

#spring.redis.host=localhost

#spring.redis.port=6379

And uncomment these lines:

spring.data.redis.host=redis

spring.data.redis.port=6379

Build and Run with Docker Compose

docker-compose up --build

Once the services are up and running, you can access the api documentation at:
http://localhost:8080/swagger-ui/index.html

# Running Locally

To run the application locally on your machine, follow these steps:

Ensure Prerequisites are Installed

Verify that Java 21, Maven, and Redis are installed and running.

Start the Redis server:

redis-server

Modify Application Properties

In the src/main/resources/application.properties file, ensure the following lines are uncommented:


spring.application.name=pizzeria

spring.redis.host=localhost

spring.redis.port=6379

And comment out these lines:

#spring.data.redis.host=redis

#spring.data.redis.port=6379

Build the Application and launch it via maven or your preferred IDE

bash
Copy code
mvn clean package
Run the Application

bash
Copy code
java -jar target/pizzeria-0.0.1-SNAPSHOT.jar
The application will start and listen on port 8080.

Access the Application

Visit:

http://localhost:8080/swagger-ui/index.html

You can use Postman or curl to interact with the API.