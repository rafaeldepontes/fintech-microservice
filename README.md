# Fake-Fintech-Microservice

Using Keycloak, RabbitMQ, Eureka Server, and a few other technologies, I plan to build a fully functional FINTECH application! I hope you like what you see!

## System Design

<img src="images/System Design.png" style="width: 50%">

## Technologies

    - Java 21
    - Spring Boot 3.5.7
    - Swagger
    - Eureka Server
    - RabbitMQ
    - Api Gateway
    - Load Balancer (eurekas...)

## Services

### Client Service

- POST - `api/v1/clients

  - **Request Body:**:

  ```json
  {
    "name": "string",
    "age": "int",
    "cpf": "string" // the cpf field is the Brazilian ID..
  }
  ```

- GET - `api/v1/clients?cpf={...}`

  - **Output:**

    ```json
    {
      "id": "long",
      "name": "string",
      "age": "int",
      "cpf": "string"
    }
    ```
