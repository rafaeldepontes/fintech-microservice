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

### Api Gateway

Gateway is running on the 8080 port, it's redirecting everything to the Eureka's LB, so if you want to communicate with the system... first run the eureka server and then run the gateway AND THEN you can test the application.

### Client Service

- GET - `api/v1/clients/health-check`
  - **Output:**
    `ok`

---

Register a new client

- POST - `api/v1/clients`

  - **Request Body:**:

  ```json
  {
    "name": "string",
    "age": "int",
    "cpf": "string" // the cpf field is the Brazilian ID..
  }
  ```

  - **Output:**
    `http://localhost:{port}/api/v1/clients?cpf={cpf}`

---

List a client by his Cpf

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

### Cards Service

- GET - `api/v1/cards/health-check`
  - **Output:**
    `ok`

---

Register a new card to a client

- POST - `api/v1/cards`

  - **Request Body:**:

  ```json
  {
    "name": "string",
    "brand": "string",
    "income": "decimal",
    "limit": "decimal"
  }
  ```

  - **Output:**
    `HttpStatus 201 - Created`

---

List all the cards from a client by their income

- GET - `api/v1/cards?income={...}`

  - **Output:**

    ```json
    [
      {
        "id": "long",
        "name": "string",
        "brand": "string",
        "income": "decimal",
        "limit": "decimal"
      }
    ]
    ```

---

List all the cards from a client by the client Cpf

- GET - `api/v1/cards?cpf={...}`

  - **Output:**

    ```json
    {
      "cards": [
        {
          "name": "string",
          "brand": "string",
          "limit": "decimal"
        }
      ]
    }
    ```
