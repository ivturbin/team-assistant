# Greeting Service

Simple Spring Boot service that exposes a single greeting endpoint.

## About

This is my first project that I wrote with the assistance of the AI coding agent **Flint** (Flint 40B Free), which acted as the coding agent in this session.

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

## API

### `GET /hello`

Returns a greeting.

**Response:** `200 OK`

```
Привет
```

## Tests

```bash
mvn test
```

## Tech Stack

- Java 21
- Spring Boot 3.5
- Maven
