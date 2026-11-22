# Team Assistant

A small educational project for practicing AI-assisted and agentic software development.

## Project idea

The project keeps the name **Team Assistant**, but its application domain is now a simple one-page online store. The goal is to evolve the existing Spring Boot application step by step into a small backend for a storefront: visitors will be able to browse a product catalog, add products to a shopping cart, and eventually place orders.

The first version is intentionally minimal. We will build the backend incrementally through GitHub issues, with an AI coding agent implementing each issue and opening a pull request for review. A frontend and more advanced store features can be introduced in later iterations.

### Planned capabilities

- Product catalog: retrieve and view available products.
- Shopping cart: add, update, and remove products.
- Orders: create an order from the cart and retrieve order details.
- Storefront integration: provide the API needed by a simple single-page shop.

These are evolutionary goals, not a claim that all features are already implemented. The current application still contains the original greeting endpoint.

## Current API

### `GET /hello`

Returns a greeting. Supports an optional `name` query parameter.

**Response:** `200 OK`

```text
Привет
```

With the optional `name` parameter:

```http
GET /hello?name=Иван
```

```text
Привет, Иван!
```

Without the parameter, the response contains only the greeting: `Привет`.

### `GET /api/products`

Returns the product catalog for the storefront. Returns a JSON array of sample products from a small deterministic in-memory collection; no database or external services are used.

**Response:** `200 OK`

```json
[
  {
    "id": 1,
    "name": "Ноутбук Air",
    "description": "Надёжный ноутбук для дома и работы",
    "price": 54990,
    "currency": "RUB",
    "imageUrl": "https://placehold.co/800x450?text=NocTIP",
    "available": true
  }
]
```

The catalog contains several sample products and is deterministic: it does not change between requests.

## Requirements

- Java 21
- Maven 3.9+

## Build

```bash
mvn clean package
```

The executable jar is created at `target/greeting-service-0.0.1-SNAPSHOT.jar`.

## Run

```bash
java -jar target/greeting-service-0.0.1-SNAPSHOT.jar
```

Alternatively:

```bash
mvn spring-boot:run
```

The service starts on port `8080`.

## Tests

```bash
mvn test
```

## Tech Stack

- Java 21
- Spring Boot 3.5
- Maven
