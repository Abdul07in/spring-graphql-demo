# Spring Boot GraphQL Demo

A sample Spring Boot application demonstrating the integration of **Spring GraphQL** for managing a roster of cricket players. This project provides a robust API for CRUD operations with a focus on clean architecture and comprehensive test coverage.

## 🚀 Features

- **GraphQL API**: Fully functional GraphQL endpoint with support for Queries and Mutations.
- **In-Memory Storage**: Simple and efficient player management (can be extended to any database).
- **Interactive UI**: Built-in GraphiQL interface for exploring and testing the API.
- **Strong Typing**: Use of Enums for team selection (IPL teams like CSK, MI, RCB, etc.).
- **High Test Coverage**: Extensive unit and integration tests using JUnit 5 and JaCoCo.

## 🛠️ Technologies Used

- **Spring Boot 3.4.x**
- **Spring GraphQL**
- **Java 17**
- **Maven**
- **JUnit 5**
- **JaCoCo** (Test Coverage)

## 📋 Prerequisites

- **Java 17** or higher
- **Maven 3.8+**

## 🏃 Getting Started

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd spring-graphql-demo
   ```

2. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access GraphiQL:**
   Once the application is running, open your browser and navigate to:
   [http://localhost:8080/graphiql](http://localhost:8080/graphiql)

## 📡 GraphQL Operations

### Queries

**Find All Players:**
```graphql
query {
  findAllPlayers {
    id
    name
    age
    team
  }
}
```

**Find Player by ID:**
```graphql
query {
  findPlayerById(id: 1) {
    name
    team
  }
}
```

### Mutations

**Create a Player:**
```graphql
mutation {
  createPlayer(name: "MS Dhoni", age: 42, team: CSK) {
    id
    name
    team
  }
}
```

**Update a Player:**
```graphql
mutation {
  updatePlayer(id: 1, name: "Mahi", age: 43, team: CSK) {
    id
    name
  }
}
```

**Delete a Player:**
```graphql
mutation {
  deletePlayer(id: 1) {
    id
    name
  }
}
```

## 🧪 Testing

The project includes a comprehensive suite of tests.

### Run Tests
```bash
./mvnw test
```

### Check Coverage
After running the tests, you can view the JaCoCo coverage report at:
`target/site/jacoco/index.html`

## 📂 Project Structure

- `src/main/java`: Application source code.
- `src/main/resources/graphql`: GraphQL schema definitions (`schema.graphqls`).
- `src/test/java`: Unit and integration tests.
