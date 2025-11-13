# Spring Data JDBC + jOOQ Sample

Small Spring Boot 3.3 demo that mixes Spring Data JDBC infrastructure with handcrafted jOOQ queries. It starts an in-memory H2 database, bootstraps the `orders` table via `schema.sql`, and demonstrates a minimal repository that writes/reads `Order` records using the jOOQ `DSLContext`.

## Features

- Spring Boot auto-configuration plus a `CommandLineRunner` that populates and logs demo orders on startup.
- Spring Data JDBC domain model implemented as a Java 17 `record` (`Order`) for concise immutable aggregates.
- jOOQ-powered `OrderRepository` showcasing inserts with `returning` and type-safe mapping for reads.
- In-memory H2 datasource configured in `application.properties`; no external database required.
- Schema lifecycle managed through `schema.sql`, making the sample self-contained for workshops or labs.

## Getting Started

### Prerequisites

- JDK 17+
- Maven 3.9+ (or the Maven Wrapper if you add it)

### Run the application

```bash
mvn spring-boot:run
```

Maven downloads the dependencies, starts the embedded H2 instance, recreates the `orders` table, and executes the `CommandLineRunner`. You should see console output similar to:

```
Order 1 for Alice
Order 2 for Bob
```

### Project layout

- `src/main/java/com/example/jooqsample/Application.java` – Spring Boot entry point plus the demo runner.
- `src/main/java/com/example/jooqsample/domain/Order.java` – Aggregate definition used by both JDBC and jOOQ layers.
- `src/main/java/com/example/jooqsample/repository/OrderRepository.java` – Custom repository built on `DSLContext`.
- `src/main/resources/schema.sql` – Creates the `orders` table each time the app starts.
- `src/main/resources/application.properties` – Datasource + logging configuration.

## Extending the sample

- Add more columns or tables to `schema.sql`, then extend the `Order` record and repository mappings.
- Replace the in-memory H2 URL with a persistent database (PostgreSQL, MySQL, etc.) and bring your own driver.
- Wrap the repository behind Spring Data JDBC repositories if you want CRUD abstractions while keeping jOOQ for complex queries.

Feel free to adapt this project as a teaching aid or a starting point for experimenting with Spring Data JDBC and jOOQ side by side.
