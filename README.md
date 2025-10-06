Game Library is a Spring Boot–based REST API that allows users to manage a collection of games, libraries, and library users. 
It’s designed with a modular service architecture using controllers, DTOs, converters, and service interfaces to ensure scalability and maintainability.

The project follows a clean separation of concerns, providing distinct layers for controllers, business logic, and data transfer.

**Tech Stack:**
* Java language
* Spring Boot as Backend framework
* Maven as build tool.
* API Layer using Spring Rest MVC
* Data Handling using DTO objects + Service + Converter architecture
* Mockito, JUnit used for testing.
* PostgreSQL used as database.
* Frontend written using HTML + CSS + JavaScript


**Installation:**

  **Clone the repository**
  
  git clone https://github.com/<your-username>/gameLibrary.git
  
  cd gameLibrary
  

  **Build the project**
  
  ./mvnw clean install
  
  
  **Run the application**
  
  ./mvnw spring-boot:run

  The app runs on http://localhost:8080
  by default.

  Once running, the API exposes REST endpoints for:

  /games — manage game entities
  
  /libraries — manage game libraries
  
  /users — manage library users
  
  You can test these endpoints using Postman, cURL, or any REST client.
