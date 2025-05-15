# 🛍️ Product Management System - Quarkus Reactive

A simple CRUD-based Product Management REST API built using the **Quarkus Reactive** framework. This application demonstrates:

- Reactive programming using **Mutiny (Uni)**
- Persistence using **Hibernate Reactive with Panache**
- REST endpoints with **Jakarta REST (JAX-RS)**
- Unit testing with **Mockito + JUnit 5**
- Proper **logging** and **exception handling**

---

## 🚀 Tech Stack

- **Quarkus**
- **Java 21**
- **Hibernate Reactive with Panache**
- **RESTEasy Reactive**
- **JUnit 5**
- **Gradle (Kotlin DSL - `build.gradle.kts`)**

---

## 📦 Features

- Create, Read, Update, Delete Products
- Check product stock by quantity
- Sort products by price (ascending)

---

## 🧪 Running Tests

To run the unit tests from IntelliJ:

1. Right-click on the test folder or a specific test class (like ProductServiceTest) and select "Run Tests".


## 📦 In IntelliJ (Recommended)
- Open the project in IntelliJ IDEA (Community or Ultimate Edition).
- Make sure the Gradle tool window is visible (View -> Tool Windows -> Gradle).
- Run the application by running the quarkusDev gradle task (quarkus-product-app -> Tasks -> quarkus -> quarkusDev)

## 🚀 Endpoints
POST http://localhost:8080/products

GET http://localhost:8080/products

GET http://localhost:8080/products/651

PUT http://localhost:8080/products/651

DELETE http://localhost:8080/products/651

GET http://localhost:8080/products/101/stock?count=1000

GET http://localhost:8080/products/sorted

